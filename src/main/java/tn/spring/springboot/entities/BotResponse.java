package tn.spring.springboot.entities;

import java.util.List;

public class BotResponse {

    private List<Choice> choices;


    public static class Choice {

        private int index;
        private Message message;

    }
}