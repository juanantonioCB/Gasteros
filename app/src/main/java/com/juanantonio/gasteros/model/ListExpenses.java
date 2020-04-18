package com.juanantonio.gasteros.model;

import java.util.List;

public class ListExpenses {
    private String id;
    private String name;
    private String ownerId;
    private String companyId;
    private List<Expenses> expenses;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public List<Expenses> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expenses> expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString(){
        return "Nombre: "+this.name+"\n"+
                "Owner ID "+this.ownerId+"\n"+
                "Company ID "+this.companyId+"\n"+
                "Gastos: "+this.expenses+"\n";
    }
}
