package com.sandh.billsplitter.domain;
/**
 * Created by user on 19/09/15.
test
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bill {
    private List<Item> items  = new ArrayList<Item>();
    final static Logger log = LoggerFactory.getLogger(Bill.class);

    public UUID getId() {
        return id;
    }
    private UUID id = UUID.randomUUID();

    public void addItem(Item item)
    {
        items.add(item);
    }

    public Item[] getItems()
    {
        return items.toArray(new Item[items.size()]);
    }

    public int getNumberItems()
    {
        return items.size();
    }

    public UUID addItem(String name, double price) {
        Item item = new Item(name, price);
        addItem(item);
        return item.getId();
    }

    public boolean deleteItem(Item item)
    {
        return items.remove(item);
    }
}
