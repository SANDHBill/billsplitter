package com.sandh.billsplitter.services;

/**
 * Created by user on 19/09/15.
 */

import org.junit.Assert;
import org.junit.Test;
import java.util.Map;

public class BillUtilityTests {
    public static final String SHOULD_PAY = " should pay ";
    private final BillTestScenario billTestScenario = new BillTestScenario();
    private BillUtility billUtility = new BillUtility();

    @Test
    public void calculateTotalTest() {
        BillTestScenario.BillScenario billScenario = billTestScenario.simpleBill();
        double total = billUtility.calculateTotal(billScenario.getBill());
        double expectedTotal = BillTestScenario.KOOBIDEH_PRICE + BillTestScenario.JOOJEH_PRICE + BillTestScenario.GHORMEH_PRICE;
        Assert.assertEquals("Total should be ", expectedTotal, total, 0.00001);
    }

    private void runAsserts(BillTestScenario.BillScenario billScenario)
    {
        Map participants = billUtility.calculateTotalPerPerson(billScenario.getBill());
        for (Map.Entry<String, Double> assertion : billScenario.getAsserts().entrySet())
        {
            String name = assertion.getKey();
            Object share = participants.get(name);
            Assert.assertNotNull(name + " is missing",share);
            Assert.assertTrue(name + SHOULD_PAY, (double) share == assertion.getValue());
        }
    }

    @Test
    public void splitBill(){
        BillTestScenario.BillScenario billScenario = billTestScenario.simpleBill();
        runAsserts(billScenario);
    }

    @Test
    public void simpleBillDisclaim(){
        BillTestScenario.BillScenario billScenario = billTestScenario.simpleBillDisclaim();
        runAsserts(billScenario);
    }

    @Test
    public void splitSharedBill(){
        BillTestScenario.BillScenario billScenario = billTestScenario.simpleBillShared();
        runAsserts(billScenario);
    }

    @Test
    public void ownedByHamedLaterSharedByShahram(){
        BillTestScenario.BillScenario billScenario = billTestScenario.ownedByHamedLaterSharedByShahram();
        runAsserts(billScenario);
    }

    @Test
    public void sameItemNameOwnedbyDifferentGroups(){
        BillTestScenario.BillScenario billScenario = billTestScenario.sameItemnameOwnedbyDifferentGroups();
        runAsserts(billScenario);
    }

    @Test
    public void sharedBillDisclaim(){
        BillTestScenario.BillScenario billScenario = billTestScenario.sharedBillDisclaim();
        runAsserts(billScenario);
    }
}


