package com.davydov.telebot;

import com.davydov.database.CityRepository;
import com.davydov.model.City;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


public class SimpleBot extends TelegramLongPollingBot {

	private CityRepository repository;

	public SimpleBot(CityRepository peripheral) {
		this.repository = peripheral;
	}

	public void onUpdateReceived(Update update) {
		try {
			String message = update.getMessage().getText();

			if (message.charAt(0) != '/') {

				String info;
				try {
					info = repository.getCity(message).getDescription(); // throws NPtrExc, when nothing found in db
				} catch(NullPointerException e){
					info = "No info about " + message + ".";
				}
				execute(new SendMessage(update.getMessage().getChatId(), info));

			} else if (message.charAt(0) == '/') {

				Command command = Command.parse(message);

				if (command != null) {
					switch (command) {
						case START:
							sendMessage(update, command.getDescription());
							break;

						case HELP:
							Command[] list = command.getCommands();

							StringBuilder sb1 = new StringBuilder();
							for (int i = 1; i < list.length; i++){
								sb1.append(list[i].getCommand().concat(" "))
										.append(list[i].getDescription().concat("\n"));
							}
							sendMessage(update, sb1.toString());
							break;

						case CITIES:
							List<City> cities = repository.findAll();

							StringBuilder sb2 = new StringBuilder();
							for (City c: cities) {
								sb2.append(c.getCityName().concat("\n"));
							}
							sendMessage(update, sb2.toString());
							break;
					}
				} else {
					execute(new SendMessage(update.getMessage().getChatId(), message.concat(" not found.")));
				}
			}

		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(Update update, String description) throws TelegramApiException {
		execute(new SendMessage(update.getMessage().getChatId(), description));
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
