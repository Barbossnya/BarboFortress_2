# Установка Maven

## Требования
- Java 11 или выше (у вас установлена Java 21)
- Maven 3.6.3 или выше

## Способы установки Maven

### 1. Через Chocolatey (рекомендуется для Windows)
```powershell
# Установка Chocolatey (если не установлен)
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))

# Установка Maven
choco install maven
```

### 2. Ручная установка
1. Скачайте Maven с официального сайта: https://maven.apache.org/download.cgi
2. Распакуйте архив в папку (например, `C:\Program Files\Apache\maven`)
3. Добавьте путь к Maven в переменную PATH:
   - Откройте "Система" → "Дополнительные параметры системы" → "Переменные среды"
   - В разделе "Переменные среды пользователя" найдите PATH
   - Добавьте путь к папке bin Maven (например, `C:\Program Files\Apache\maven\bin`)

### 3. Через SDKMAN (если используете WSL)
```bash
# Установка SDKMAN
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Установка Maven
sdk install maven
```

## Проверка установки
После установки выполните:
```bash
mvn -version
```

Вы должны увидеть что-то вроде:
```
Apache Maven 3.9.5 (57804ffe001d7215b5e7bcb53102683f38f6cfd6)
Maven home: C:\Program Files\Apache\maven
Java version: 21.0.6, vendor: Eclipse Adoptium, runtime: C:\Program Files\Eclipse Adoptium\jdk-21.0.6+7-hotspot
```

## Альтернативные способы запуска проекта

Если Maven не установлен, вы можете:

### 1. Использовать IDE
- **IntelliJ IDEA**: откройте проект как Maven проект
- **Eclipse**: импортируйте как Maven проект
- **VS Code**: установите расширение "Maven for Java"

### 2. Использовать Docker
```bash
# Создайте Dockerfile
docker run --rm -v ${PWD}:/app -w /app maven:3.9.5-openjdk-21 mvn clean compile
```

### 3. Скачать Maven Wrapper
Если у вас есть доступ к интернету, можно добавить Maven Wrapper в проект:
```bash
# После установки Maven
mvn wrapper:wrapper
```

Затем использовать:
```bash
./mvnw clean compile
``` 