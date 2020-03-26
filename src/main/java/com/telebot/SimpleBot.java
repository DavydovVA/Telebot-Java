package com.telebot;

import com.database.model.City;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class SimpleBot extends TelegramLongPollingBot {
	Peripheral periphery = new Peripheral();


	public void onUpdateReceived(Update update) {
		try {
			String message = update.getMessage().getText();

			if (message.charAt(0) != '/') {
				String description = periphery.getCityDescription(message);

				if (description != null) {
					execute(new SendMessage(update.getMessage().getChatId(), description));
				} else {
					execute(new SendMessage(update.getMessage().getChatId(), "No info about ".concat(message)));
				}

			} else if (message.charAt(0) == '/') {
				execute(new SendMessage(update.getMessage().getChatId(), periphery.getCommandDescription(message)));
			}

		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getBotUsername() {
		return "test_dva_pvsm_bot";
	}

	@Override
	public String getBotToken() {
		return "1115004291:AAGlnBaydiN6BAQshXphYXqb_W1R8lIIehM";
	}
}
