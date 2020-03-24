package com.telebot;

import com.database.MongoDBPoperations;
import com.database.config.MongoConfig;
import com.database.model.City;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class SimpleBot extends TelegramLongPollingBot {
	// variables for Database
	private ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
	private MongoOperations mongoOp = (MongoOperations) ctx.getBean("mongoTemplate");
	private MongoDBPoperations ops = new MongoDBPoperations();


	private Commands c = new Commands();

	public void onUpdateReceived(Update update) {
		try {
			String message = update.getMessage().getText();

			if (message.charAt(0) != '/') {
				City kek = ops.searchCity(mongoOp, "cityName", message);

				if (kek != null) {
					execute(new SendMessage(update.getMessage().getChatId(), kek.getDescription()));
				} else {
					execute(new SendMessage(update.getMessage().getChatId(), "No info about ".concat(message)));
				}

			} else if (message.charAt(0) == '/') {
				execute(new SendMessage(update.getMessage().getChatId(), c.getDescription(message, ops, mongoOp)));
			}

		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public String getBotUsername() {
		return "test_dva_pvsm_bot";
	}

	@Override
	public String getBotToken() {
		return "1115004291:AAGlnBaydiN6BAQshXphYXqb_W1R8lIIehM";
	}
}
