api-automation/
│
├─ src/main/java/
│   └─ com/makurea/api/
│       ├─ client/                # Клиенты для каждого API (Single Responsibility)
│       │   ├─ ReqresClient.java
│       │   
│       │
│       ├─ models/                # DTO / модели для JSON
│       │   ├─ User.java
│       │   
│       │
│       ├─ services/              # Логика работы с API (использует clients)
│       │   ├─ UserService.java
│       │   
│       │
│       └─ utils/                 # Утилиты: генерация данных, проверка схем JSON и т.д.
│           └─ JsonValidator.java
│
├─ src/test/java/
│   └─ com/example/api/tests/
│       ├─ UserTests.java         # CRUD тесты для Reqres
│      
│
├─ build.gradle                        # Gradle зависимости (Rest Assured, TestNG/JUnit, Allure)
└─ README.md
