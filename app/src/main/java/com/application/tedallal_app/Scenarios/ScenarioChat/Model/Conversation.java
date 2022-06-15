package com.application.tedallal_app.Scenarios.ScenarioChat.Model;

import java.util.Date;
import java.util.List;

public class Conversation {

    public String senderName;
    public String lastMessage;
    public String status;
    public Date timeStamp;
    public List<Integer> users;


    public Conversation() {
    }

    public Conversation(String senderName, String lastMessage, String status, Date timeStamp, List<Integer> users) {
        this.senderName = senderName;
        this.lastMessage = lastMessage;
        this.status = status;
        this.timeStamp = timeStamp;
        this.users = users;

    }


    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public List<Integer> getUsers() {
        return users;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

}
