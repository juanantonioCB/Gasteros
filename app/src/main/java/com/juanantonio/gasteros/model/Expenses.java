package com.juanantonio.gasteros.model;

import java.util.Date;

public class Expenses {
    private String name;
    private float amount;
    private Date date;
    private String idExpense;
    private String userId;
    private String idList;


    public String getIdList() {
        return idList;
    }

    public void setIdList(String idList) {
        this.idList = idList;
    }
    public String getIdExpense() {
        return idExpense;
    }

    public void setIdExpense(String idExpense) {
        this.idExpense = idExpense;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private String listId;

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

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
