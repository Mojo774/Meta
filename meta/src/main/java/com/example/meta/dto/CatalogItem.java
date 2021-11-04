package com.example.meta.dto;


public class CatalogItem {

    private String name;
    private String description;
    private long amount;

    public CatalogItem() {
    }

    public CatalogItem(String name, String description, long amount) {
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }


}
