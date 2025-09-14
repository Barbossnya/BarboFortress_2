package com.crpt.api;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class CrptApi {

    private final TimeUnit timeUnit;
    private final int requestLimit;
    private final Semaphore semaphore;
    private final ConcurrentHashMap<Long, AtomicLong> requestCounts;
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private static final String API_URL = "https://ismp.crpt.ru/api/v3/lk/documents/create";

    /**
     * Исключение, выбрасываемое при превышении лимита запросов
     */
    public static class RequestLimitExceededException extends RuntimeException {
        public RequestLimitExceededException(String message) {
            super(message);
        }
    }

    // Внутренний класс для представления документа
    public static class Document {
        private String description;
        private String docId;
        private String docStatus;
        private String docType;
        private boolean importRequest;
        private String ownerInn;
        private String participantInn;
        private String producerInn;
        private String productionDate;
        private String productionType;
        private List<Product> products;
        private String regDate;
        private String regNumber;

        // Конструкторы
        public Document() {
            this.products = new ArrayList<>();
        }

        public Document(String description, String docId, String docStatus, String docType,
                boolean importRequest, String ownerInn, String participantInn,
                String producerInn, String productionDate, String productionType,
                String regDate, String regNumber) {
            this.description = description;
            this.docId = docId;
            this.docStatus = docStatus;
            this.docType = docType;
            this.importRequest = importRequest;
            this.ownerInn = ownerInn;
            this.participantInn = participantInn;
            this.producerInn = producerInn;
            this.productionDate = productionDate;
            this.productionType = productionType;
            this.regDate = regDate;
            this.regNumber = regNumber;
            this.products = new ArrayList<>();
        }

        // Геттеры и сеттеры
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDocId() {
            return docId;
        }

        public void setDocId(String docId) {
            this.docId = docId;
        }

        public String getDocStatus() {
            return docStatus;
        }

        public void setDocStatus(String docStatus) {
            this.docStatus = docStatus;
        }

        public String getDocType() {
            return docType;
        }

        public void setDocType(String docType) {
            this.docType = docType;
        }

        public boolean isImportRequest() {
            return importRequest;
        }

        public void setImportRequest(boolean importRequest) {
            this.importRequest = importRequest;
        }

        public String getOwnerInn() {
            return ownerInn;
        }

        public void setOwnerInn(String ownerInn) {
            this.ownerInn = ownerInn;
        }

        public String getParticipantInn() {
            return participantInn;
        }

        public void setParticipantInn(String participantInn) {
            this.participantInn = participantInn;
        }

        public String getProducerInn() {
            return producerInn;
        }

        public void setProducerInn(String producerInn) {
            this.producerInn = producerInn;
        }

        public String getProductionDate() {
            return productionDate;
        }

        public void setProductionDate(String productionDate) {
            this.productionDate = productionDate;
        }

        public String getProductionType() {
            return productionType;
        }

        public void setProductionType(String productionType) {
            this.productionType = productionType;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }

        public String getRegDate() {
            return regDate;
        }

        public void setRegDate(String regDate) {
            this.regDate = regDate;
        }

        public String getRegNumber() {
            return regNumber;
        }

        public void setRegNumber(String regNumber) {
            this.regNumber = regNumber;
        }
    }

    // Внутренний класс для представления продукта
    public static class Product {
        private String certificateDocument;
        private String certificateDocumentDate;
        private String certificateDocumentNumber;
        private String ownerInn;
        private String producerInn;
        private String productionDate;
        private String tnvedCode;
        private String uitCode;
        private String uituCode;

        // Конструкторы
        public Product() {
        }

        public Product(String certificateDocument, String certificateDocumentDate,
                String certificateDocumentNumber, String ownerInn, String producerInn,
                String productionDate, String tnvedCode, String uitCode, String uituCode) {
            this.certificateDocument = certificateDocument;
            this.certificateDocumentDate = certificateDocumentDate;
            this.certificateDocumentNumber = certificateDocumentNumber;
            this.ownerInn = ownerInn;
            this.producerInn = producerInn;
            this.productionDate = productionDate;
            this.tnvedCode = tnvedCode;
            this.uitCode = uitCode;
            this.uituCode = uituCode;
        }

        // Геттеры и сеттеры
        public String getCertificateDocument() {
            return certificateDocument;
        }

        public void setCertificateDocument(String certificateDocument) {
            this.certificateDocument = certificateDocument;
        }

        public String getCertificateDocumentDate() {
            return certificateDocumentDate;
        }

        public void setCertificateDocumentDate(String certificateDocumentDate) {
            this.certificateDocumentDate = certificateDocumentDate;
        }

        public String getCertificateDocumentNumber() {
            return certificateDocumentNumber;
        }

        public void setCertificateDocumentNumber(String certificateDocumentNumber) {
            this.certificateDocumentNumber = certificateDocumentNumber;
        }

        public String getOwnerInn() {
            return ownerInn;
        }

        public void setOwnerInn(String ownerInn) {
            this.ownerInn = ownerInn;
        }

        public String getProducerInn() {
            return producerInn;
        }

        public void setProducerInn(String producerInn) {
            this.producerInn = producerInn;
        }

        public String getProductionDate() {
            return productionDate;
        }

        public void setProductionDate(String productionDate) {
            this.productionDate = productionDate;
        }

        public String getTnvedCode() {
            return tnvedCode;
        }

        public void setTnvedCode(String tnvedCode) {
            this.tnvedCode = tnvedCode;
        }

        public String getUitCode() {
            return uitCode;
        }

        public void setUitCode(String uitCode) {
            this.uitCode = uitCode;
        }

        public String getUituCode() {
            return uituCode;
        }

        public void setUituCode(String uituCode) {
            this.uituCode = uituCode;
        }
    }

    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        if (requestLimit <= 0) {
            throw new IllegalArgumentException("Request limit must be positive");
        }
        this.timeUnit = timeUnit;
        this.requestLimit = requestLimit;
        this.semaphore = new Semaphore(requestLimit);
        this.requestCounts = new ConcurrentHashMap<>();
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Создание документа для ввода в оборот товара, произведенного в РФ
     * 
     * @param document  Java объект документа
     * @param signature подпись документа
     * @throws InterruptedException если поток был прерван во время ожидания
     * @throws IOException          если произошла ошибка при выполнении HTTP
     *                              запроса
     */
    public void createDocument(Document document, String signature) throws InterruptedException, IOException {
        if (document == null) {
            throw new IllegalArgumentException("Document cannot be null");
        }
        if (signature == null || signature.trim().isEmpty()) {
            throw new IllegalArgumentException("Signature cannot be null or empty");
        }

        try {
            // Ожидание разрешения на выполнение запроса
            semaphore.acquire();

            // Проверка лимита запросов
            checkAndUpdateRequestLimit();

            // Сериализация документа в JSON
            String documentJson = objectMapper.writeValueAsString(document);

            // Создание HTTP запроса
            RequestBody body = RequestBody.create(
                    documentJson,
                    MediaType.parse("application/json"));

            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + signature)
                    .build();

            // Выполнение запроса
            try (Response response = httpClient.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    String responseBody = response.body() != null ? response.body().string() : "";
                    // В реальном приложении здесь должен быть логгер
                    System.out.println("Document created successfully: " + responseBody);
                } else {
                    String errorBody = response.body() != null ? response.body().string() : "";
                    throw new IOException("Failed to create document: " + response.code() + " " +
                            response.message() + " - " + errorBody);
                }
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw e;
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IOException("Unexpected error while creating document: " + e.getMessage(), e);
        } finally {
            semaphore.release();
        }
    }

    /**
     * Проверяет и обновляет счетчик запросов для текущего временного окна
     * Thread-safe реализация ограничения запросов
     */
    private void checkAndUpdateRequestLimit() {
        long currentTime = System.currentTimeMillis();
        long windowStart = currentTime - timeUnit.toMillis(1);

        // Очистка старых записей (старше текущего окна)
        requestCounts.entrySet().removeIf(entry -> entry.getKey() < windowStart);

        // Получение или создание счетчика для текущего временного окна
        AtomicLong currentCount = requestCounts.computeIfAbsent(currentTime, k -> new AtomicLong(0));

        // Проверка лимита
        long count = currentCount.incrementAndGet();
        if (count > requestLimit) {
            throw new RequestLimitExceededException("Request limit exceeded: " + count + " requests in " +
                    timeUnit.name().toLowerCase() + " (limit: " + requestLimit + ")");
        }
    }
}
