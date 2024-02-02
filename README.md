### Запуск

-docker-compose up

#### API

- localhost:8080/advice - добавить новый совет, для удобства
создана страница (уходит пост запрос на /help-service/v1/support)
- localhost:8080/help-service/v1/support - получить случайный text-plain совет.