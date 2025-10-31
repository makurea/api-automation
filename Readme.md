# API Automation Project

Проект для автоматизации тестирования REST API с использованием подхода **SOLID**, JUnit 5, Rest Assured и Allure.

---

## Структура проекта

```

api-automation/  
│  
├─ .github/workflows/             ← CI/CD-настройки  
├─ .idea/                         ← IDE-файлы (IntelliJ IDEA)  
├─ gradle/wrapper/                ← wrapper Gradle  
├─ logs/                          ← лог-каталог  
├─ src/  
│    ├─ main/  
│    │    └─ java/  
│    │        └─ com/makurea/api/  
│    │             ├─ client/       ← клиенты API  
│    │             ├─ models/       ← DTO / модели JSON  
│    │             ├─ services/     ← логика работы (используя clients)  
│    │             └─ utils/        ← утилиты (валидация JSON, генерация данных и др.)  
│    └─ test/  
│         └─ java/  
│              └─ com/example/api/tests/  
│                    └─ UserTests.java  ← тесты  
├─ .gitignore  
├─ README.md  
├─ build.gradle                    ← сборка проекта  
├─ gradlew  
├─ gradlew.bat  
└─ settings.gradle  
  

````

---

## Используемый стек технологий

- **Java 17**
- **Gradle** — сборка проекта и управление зависимостями
- **JUnit 5** — фреймворк для написания тестов
- **Rest Assured** — работа с REST API
- **Jackson** — десериализация/сериализация JSON
- **Log4j2** — логирование
- **Allure** — отчёты по тестам
- **GitHub Actions** — CI/CD пайплайн для запуска тестов и генерации Allure отчёта

---

## Особенности

- Используется **структура по принципам SOLID**: разделение на client, service, model, utils.
- Тесты легко масштабируются и расширяются под новые API.
- Встроенный Allure отчёт с историей тестов.
- Поддержка GitHub Pages для публикации отчётов.

---

## Запуск тестов локально

```bash
# Клонировать репозиторий
git clone https://github.com/yourusername/api-automation.git
cd api-automation

# Запустить тесты и сгенерировать Allure отчёт
./gradlew clean test
./gradlew allureReport
````

Открыть отчёт:  

```bash
open build/reports/allure-report/index.html
```

---

## CI/CD

* GitHub Actions автоматически запускает тесты при пуше в `main`.
* Allure отчёт публикуется на GitHub Pages (`gh-pages` ветка).

---

## Примечания 

```bash
chcp 65001
```


