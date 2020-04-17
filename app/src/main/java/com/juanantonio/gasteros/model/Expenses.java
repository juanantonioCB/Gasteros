package com.juanantonio.gasteros.model;

public class Expenses {
    private String name;
    private float amount;
    private String userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString(){
        return "Nombre: "+this.name+"\n"+
                "Cantidad: "+this.amount+"\n"+
                "User Id"+this.userId+"\n";
    }
}
