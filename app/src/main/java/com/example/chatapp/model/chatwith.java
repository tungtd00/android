package com.example.chatapp.model;

import java.util.HashSet;

public class chatwith {
    private String myEmail;
    private HashSet<String> hashSetUses;

    public chatwith() {
    }

    public chatwith(String myEmail, HashSet<String> hashSetUses) {
        this.myEmail = myEmail;
        this.hashSetUses = hashSetUses;
    }

    public String getMyEmail() {
        return myEmail;
    }

    public void setMyEmail(String myEmail) {
        this.myEmail = myEmail;
    }

    public HashSet<String> getHashSetUses() {
        return hashSetUses;
    }

    public void setHashSetUses(HashSet<String> hashSetUses) {
        this.hashSetUses = hashSetUses;
    }
}
