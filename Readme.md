# Интернет-банк 

Тестирование авторизации в системе интернет-банка

## Начало работы

Скопировать через GIT на свой ПК проект командой `git clone https://github.com/nmoraru/aqa_7.1_SQL`

### Prerequisites

На ПК должны быть установлены:

1. Git

2. IntelliJ IDEA

3. OpenJDK 11

4. Docker 

5. Google Chrome

### Установка и запуск

Все команды выполняются в терминале проекта в IntelliJ IDEA

1. Запустить docker-контейнер командой 
`docker-compose up`

2. Подключить БД командой 
`docker-compose exec mysql mysql -u app -p app -v`
Ввести пароль (указан в файле docker-compose.yml)

3. Запустить SUT
Для запуска SUT выполнить команду 
`java -jar ./artifacts/app-deadline.jar`

4. Запустить тесты командой `gradlew clean test`
 
5. Для повторного использования тестов необходимо перезапустить SUT 

## Лицензия

Copyright [Альфа-Банк] 
Лицензия Альфа-Банка на разработку информационных систем:
https://alfabank.ru/f/3/about/licence_and_certificate/lic.pdf