package com.application.tedallal_app.Scenarios.ScenarioChat.Model;

import java.util.Date;
import java.util.List;

public class ChatItem {

    public String message;
    public int sender;
    public Date timeStamp;


    public ChatItem(String message, int sender, Date timeStamp) {
        this.message = message;
        this.sender = sender;
        this.timeStamp = timeStamp;
    }



    public ChatItem() {
        //empty constructor needed
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}