package com.sandh.billsplitter.domain;

/**
 * Created by user on 19/09/15.
 */

import org.junit.Assert;
import org.junit.Test;

public class ItemTests {

    public static final String KALAMARY = "kalamary";
    public static final double ITEMPRICE = -5;

    @Test
    public void testItemName() {
        Item item = new Item();

        item.setName(KALAMARY);

        Assert.assertNotNull("Item name should not be empty", item.getName());
        Assert.assertEquals("name should be " + KALAMARY, KALAMARY, item.getName());

    }

    @Test
    public void testItemPrice() {
        Item item = new Item();

        item.setPrice(ITEMPRICE);

        Assert.assertTrue("Price should be " + ITEMPRICE, ITEMPRICE == item.getPrice());

    }
}

