package com.example.dania.ecom.Classes;

public class MsgClass {
    String sendFromID;
    String sendFromName;

    String sendToID;
    String sendToName;


    String chat;

    public MsgClass(){

    }
    public MsgClass(String sendFromID, String sendFromName, String sendToID, String sendToName, String chat){
        this.sendFromID = sendFromID;
        this.sendFromName = sendFromName;
        this.sendToID = sendToID;
        this.sendToName = sendToName;
        this.chat = chat;
    }

    public String getSendFromID() {
        return sendFromID;
    }

    public void setSendFromID(String sendFromID) {
        this.sendFromID = sendFromID;
    }

    public String getSendFromName() {
        return sendFromName;
    }

    public void setSendFromName(String sendFromName) {
        this.sendFromName = sendFromName;
    }

    public String getSendToID() {
        return sendToID;
    }

    public void setSendToID(String sendToID) {
        this.sendToID = sendToID;
    }

    public String getSendToName() {
        return sendToName;
    }

    public void setSendToName(String sendToName) {
        this.sendToName = sendToName;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }


}
