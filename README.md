# Система заказов для интернет-магазина

## Описание предметной области

Система позволяет создавать, просматривать и управлять статусами заказа.

- Клиент:
    - просмотр информации
    - создание
    - удаление
- Заказ:
    - просматр информации
    - создание
    - удаление
    - обновление

## Классы

**Client** - модель клиента

- `id: Long` - id клиента
- `email: String` - email клиента

**Order** - модель заказа

- `id: Long` - id заказа
- `clientId: Long` - id клиента, которому принадлежит заказ
- `description: String` - краткое описание заказа
- `orderStatus: OrderStatus` - статус Заказа (OPENED | IN_PROGRESS | DELIVERED)

## Требования

Перед началом работы убедитесь, что у вас установлены следующие компоненты:

- Java Development Kit (JDK) версии 17
- Docker

## Установка и запуск

1. Склонируйте репозиторий проекта с помощью команды:

   ```bash
   git clone https://github.com/mikolaaaaaaa/E-mall.git
   ```

2. Перейдите в директорию проекта:

   ```bash
   cd E-mall
   ```

3. Установите зависимости проекта с помощью Maven Wrapper (mvnw):

   ```bash
   ./mvnw install
   ```

4. Создайте Docker-образ базы данных PostgreSQL для приложения, используя Dockerfile, находящийся в
   директории `docker/postgres`:

   ```bash
   docker build -t e-mall_db ./docker/postgresql/
   ```
5. Создайте Docker-образ client-сервиса для приложения, используя Dockerfile, находящийся в
    директории `client/`:

   ```bash
   docker build -t client-service-image ./client/
   ```

6. Создайте Docker-образ order-сервиса для приложения, используя Dockerfile, находящийся в
   директории `order/`:

   ```bash
   docker build -t order-service-image ./order/
   ```

7. Создайте Docker-образ Eureka Server для приложения, используя Dockerfile, находящийся в
   директории `eureka-server/`:

   ```bash
   docker build -t eureka-server-image ./eureka-server/
   ```

5. Запустите приложение с помощью Docker Compose командой:

   ```bash
   docker-compose up
   ```

- Eureka server доступен по URL - `http://localhost:8761/`
- Swagger UI client-сервиса доступен по URL - `http://localhost:8081/client/api/swagger-ui/index.html`
- Swagger UI order-сервиса доступен по URL - `http://localhost:8082/order/api/swagger-ui/index.html`