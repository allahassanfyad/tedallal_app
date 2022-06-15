package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.ScenariosAlshalChat.Model;


import java.util.Date;
import java.util.List;

public class Conversation1 {

    public String senderName;
    public String lastMessage;
    public String status;
    public Date timeStamp;
    public List<Integer> users;
    public int userId;


    public Conversation1() {
    }

    public Conversation1(String senderName, String lastMessage, String status, Date timeStamp, List<Integer> users, int userId) {
        this.senderName = senderName;
        this.lastMessage = lastMessage;
        this.status = status;
        this.timeStamp = timeStamp;
        this.users = users;
        this.userId = userId;

    }


    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
