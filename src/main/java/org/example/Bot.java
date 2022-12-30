package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

public class Bot extends TelegramLongPollingBot {
    static Bot botVar;
    Long userId;
    static boolean Check = false;
    @Override
    public String getBotUsername() {
        return "SketchTest_Bot";
    }

    @Override
    public String getBotToken() {
        return "5598922722:AAFdQ5W4eC1g83e9tfBgiwppEgEU40m9ozY";
    }

    @Override
    public  void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();
        userId = user.getId();
        Check = true;
        System.out.println(user.getFirstName() + " " + user.getLastName() + " wrote " + msg.getText());
        System.out.println(user.getId());
        if(botVar.Check == true) {
            botVar.sendText(userId, "Hello," + " "+ user.getFirstName()+" "+user.getLastName());
            Check = false;
        }

    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot = new Bot();
        botsApi.registerBot(bot);
        botVar = bot;

    }
    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}
