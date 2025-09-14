package com.crpt.api;

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;

/**
 * Пример использования CrptApi
 */
public class Example {
    public static void main(String[] args) {
        try {
            // Создание экземпляра API с ограничением: 10 запросов в минуту
            CrptApi api = new CrptApi(TimeUnit.MINUTES, 10);
            
            // Создание документа
            CrptApi.Document document = new CrptApi.Document();
            document.setDescription("Тестовый документ");
            document.setDocId("test-doc-001");
            document.setDocStatus("DRAFT");
            document.setDocType("LP_INTRODUCE_GOODS");
            document.setImportRequest(false);
            document.setOwnerInn("1234567890");
            document.setParticipantInn("0987654321");
            document.setProducerInn("1122334455");
            document.setProductionDate("2024-01-15");
            document.setProductionType("OWN_PRODUCTION");
            document.setRegDate("2024-01-15");
            document.setRegNumber("REG-001");
            
            // Создание продукта
            CrptApi.Product product = new CrptApi.Product();
            product.setCertificateDocument("CERT-001");
            product.setCertificateDocumentDate("2024-01-15");
            product.setCertificateDocumentNumber("CERT-NUM-001");
            product.setOwnerInn("1234567890");
            product.setProducerInn("1122334455");
            product.setProductionDate("2024-01-15");
            product.setTnvedCode("1234567890");
            product.setUitCode("UIT-001");
            product.setUituCode("UITU-001");
            
            // Добавление продукта в документ
            List<CrptApi.Product> products = new ArrayList<>();
            products.add(product);
            document.setProducts(products);
            
            // Создание документа через API
            String signature = "your-signature-here";
            api.createDocument(document, signature);
            
            System.out.println("Документ успешно создан!");
            
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
