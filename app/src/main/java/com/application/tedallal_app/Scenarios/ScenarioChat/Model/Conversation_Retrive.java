package com.application.tedallal_app.Scenarios.ScenarioChat.Model;

import java.util.Date;
import java.util.List;

public class Conversation_Retrive {

    public List<Passenger> chatMembers;
    public String lastMessage;
    public String status;
    public Date timeStamp;
    public String event_id;
    public String group_id;


    public Conversation_Retrive() {
    }

    public Conversation_Retrive(List<Passenger> chatMembers, String lastMessage, String status, Date timeStamp, String event_id, String group_id) {
        this.chatMembers = chatMembers;
        this.lastMessage = lastMessage;
        this.status = status;
        this.timeStamp = timeStamp;
        this.event_id = event_id;
        this.group_id = group_id;
    }

    public List<Passenger> getChatMembers() {
        return chatMembers;
    }

    public void setChatMembers(List<Passenger> chatMembers) {
        this.chatMembers = chatMembers;
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

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
