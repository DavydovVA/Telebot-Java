package com.davydov;

import com.davydov.database.CityRepository;
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
public class Application {

    public static void main (String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.setLogStartupInfo(false);

        ApplicationContext context = app.run(args);
        RunBotApplication bean = context.getBean(RunBotApplication.class);
        bean.run();

    }

    @Component
    public static class RunBotApplication {
        @Autowired
        private CityRepository repository;

        public void run() {
            ApiContextInitializer.init();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            try {
                telegramBotsApi.registerBot(new SimpleBot(repository));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}