package com.davydov;


import com.davydov.database.Peripheral;
import com.davydov.telebot.SimpleBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@SpringBootApplication
public class Test {

    public static void main (String[] args) {
        SpringApplication app = new SpringApplication(Test.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.setLogStartupInfo(false);

        ApplicationContext context = app.run(args);
        RunBotApplication bean = context.getBean(RunBotApplication.class);
        bean.run();

    }

    @Component
    public class RunBotApplication {
        @Autowired
        private Peripheral peripheral;

        public void run() {
            ApiContextInitializer.init();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            try {
                telegramBotsApi.registerBot(new SimpleBot(peripheral));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
