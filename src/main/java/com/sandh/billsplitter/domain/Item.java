package com.sandh.billsplitter.domain;
/**
 * Created by user on 19/09/15.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Item {
    private double price;
    private String name;
    private List<String> owners = new ArrayList<String>();

    public UUID getId() {
        return id;
    }

    private UUID id = UUID.randomUUID();

    public Item(){}

    public Item(String name, double price) {
        setName(name);
        setPrice(price);
    }

    public Item(String name, double price, String owner) {
        this(name, price);
        owners.add(owner);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public List<String> getOwners() {
        return owners;
    }

    public void setOwners(List<String> owners) {
        this.owners = owners;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addOwner(String ownerName) {
        owners.add(ownerName);
    }

    public boolean removeOwner(String name) {
        return owners.remove(name);
    }
}

