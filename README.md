### Разработка Системы Управления Задачами

#### Описание задачи:
Вам необходимо разработать простую систему управления задачами (Task Management System) с использованием Java, Spring.

Система должна обеспечивать создание, редактирование, удаление и просмотр задач. Каждая задача должна содержать заголовок, описание, статус (например, "в ожидании", "в процессе", "завершено"), приоритет (например, "высокий", "средний", "низкий") и комментарии, а также автора задачи и исполнителя.

Реализовать необходимо только API.

#### Требования:
1. Сервис должен поддерживать аутентификацию и авторизацию пользователей по email и паролю.
2. Доступ к API должен быть аутентифицирован с помощью JWT токена.
3. Создать ролевую систему администратора и пользователей.
4. Администратор может управлять всеми задачами: создавать новые, редактировать существующие, просматривать и удалять, менять статус и приоритет, назначать исполнителей задачи, оставлять комментарии.
5. Пользователи могут управлять своими задачами, если указаны как исполнитель: менять статус, оставлять комментарии.
6. API должно позволять получать задачи конкретного автора или исполнителя, а также все комментарии к ним. Необходимо обеспечить фильтрацию и пагинацию вывода.
7. Сервис должен корректно обрабатывать ошибки и возвращать понятные сообщения, а также валидировать входящие данные.
8. Сервис должен быть хорошо задокументирован. API должен быть описан с помощью Open API и Swagger. В сервисе должен быть настроен Swagger UI. Необходимо написать README с инструкциями для локального запуска проекта. 
9. Напишите несколько базовых тестов для проверки основных функций вашей системы.
10. Используйте для реализации системы язык Java 17+, Spring, Spring Boot. В качестве БД можно использовать PostgreSQL или MySQL. Для реализации аутентификации и авторизации нужно использовать Spring Security. Можно использовать дополнительные инструменты, если в этом есть необходимость (например кэш).

#### Стек технологий:
Java 17, Spring Boot 3, PostgreSQL 17.1, Gradle 8.10.2, Docker.

#### Для запуска проекта на локальном компьютере:
1. Клонировать на локальный компьютер репозиторий GitHub:

    #### `git clone https://github.com/NewAge1979/TaskManagementSystem.git`

2. Перейти в каталог "TaskManagementSystem":

    #### `cd TaskManagementSystem`
3. Перед запуском проекта его надо собрать, для этого надо выполнить команду:
    #### `./gradlew clean build -x test`
4. Для запуска в Docker необходимо выполнить команду:

    #### `docker compose up -d`
5. После запуска Swagger будет доступен по адресу:

    #### [http://localhost:8888/swagger-ui/index.html](http://localhost:8888/swagger-ui/index.html)
#### Проверка работоспособности:
1. Для тестирования созданы пользователи: admin@email.com, user@email.com, user2@email.com, user3@email.com, user4@email.com с паролем: p@$$w0rd
2. Так же для тестирования созданы 7 задач.
