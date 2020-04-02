Bot: 
-
- Имя бота: test_dva_pvsm_bot
- Токен бота: 1115004291:AAGlnBaydiN6BAQshXphYXqb_W1R8lIIehM

Конфигурация бота в SimpleBot.java (в коде все прописано, в отедльный файл не выносил).


Что необходимо для запуска:
-

- Интернет-соединение
- запущенная локально PostgresQL

Add PostgresQL config file (/src/main/resources/application.properties):

    spring.datasource.url=jdbc:postgresql://localhost:5432/{Database name}
    spring.datasource.username={Username}
    spring.datasource.password={Password}
    spring.jpa.show-sql=true
    
    ## Hibernate Properties
    spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
    
    # Hibernate ddl auto (create, create-drop, validate, update)
    spring.jpa.hibernate.ddl-auto = update


run Application.java.

Web Service:
-
через консоль (curl): curl localhost:8080/{command}

commands: 
- cities/list - вывести все записи бд.
- cities/{Город} - вывести описание города.
- cities/add/{Город} -d "description={Описание}" - добавить новую запись в таблицу бд.
- cities/update/{Город} -d "description={Новое описание}" - изменить запись в таблице бд.
- cities/remove/{Город} - удалить запись из таблицы бд.
