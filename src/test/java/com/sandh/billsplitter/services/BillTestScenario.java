package com.sandh.billsplitter.services;

/**
 * Created by user on 19/09/15.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.sandh.billsplitter.domain.Bill;

public class BillTestScenario {
    public static final String KOOBIDEH = "Koobideh";
    public static final String JOOJEH = "Joojeh";
    public static final String GHORMEH = "Ghormeh";
    public static final String BARG = "Barg";
    public static final String HAMED = "Hamed";
    public static final String SHAHRAM = "Shahram";
    public static final String ROSHANAK = "Roshanak";
    public static final String AVA = "Ava";
    public static final double KOOBIDEH_PRICE = 12.0;
    public static final double JOOJEH_PRICE = 9.0;
    public static final double GHORMEH_PRICE = 5.0;
    public static final double BARG_PRICE = 14.0;
    private BillUtility billUtility = new BillUtility();

    public BillTestScenario() {
    }

    public BillScenario simpleBill() {
        BillScenario scenario = new BillScenario();
        Bill bill = new Bill();

        UUID koobidehId = bill.addItem(KOOBIDEH, KOOBIDEH_PRICE);
        UUID joojehId = bill.addItem(JOOJEH, JOOJEH_PRICE);
        UUID ghormehId = bill.addItem(GHORMEH, GHORMEH_PRICE);
        billUtility.claimItem(bill, koobidehId, HAMED);
        billUtility.claimItem(bill, joojehId, SHAHRAM);

        scenario.setBill(bill);
        scenario.addAssert(SHAHRAM, JOOJEH_PRICE);
        scenario.addAssert(HAMED, KOOBIDEH_PRICE);
        scenario.addAssert(BillUtility.NOT_OWNED, GHORMEH_PRICE);

        return scenario;
    }

    public BillScenario simpleBillDisclaim() {
        BillScenario scenario = new BillScenario();
        Bill bill = new Bill();

        UUID koobidehId = bill.addItem(KOOBIDEH, KOOBIDEH_PRICE);
        UUID joojehId = bill.addItem(JOOJEH, JOOJEH_PRICE);
        UUID ghormehId = bill.addItem(GHORMEH, GHORMEH_PRICE);
        billUtility.claimItem(bill, koobidehId, HAMED);
        billUtility.claimItem(bill, joojehId, SHAHRAM);
        billUtility.claimItem(bill, ghormehId, SHAHRAM);
        billUtility.disclaimItem(bill, ghormehId, SHAHRAM);

        scenario.setBill(bill);
        scenario.addAssert(SHAHRAM, JOOJEH_PRICE);
        scenario.addAssert(HAMED, KOOBIDEH_PRICE);
        scenario.addAssert(BillUtility.NOT_OWNED, GHORMEH_PRICE);

        return scenario;
    }

    public BillScenario sharedBillDisclaim() {
        BillScenario scenario = new BillScenario();
        Bill bill = new Bill();

        UUID koobidehId = bill.addItem(KOOBIDEH, KOOBIDEH_PRICE);
        UUID joojehId = bill.addItem(JOOJEH, JOOJEH_PRICE);
        UUID ghormehId = bill.addItem(GHORMEH, GHORMEH_PRICE);
        billUtility.claimItem(bill, koobidehId, HAMED);
        billUtility.claimItem(bill, joojehId, SHAHRAM);
        billUtility.claimItem(bill, ghormehId, SHAHRAM);
        billUtility.claimItem(bill, joojehId, HAMED);
        billUtility.disclaimItem(bill, ghormehId, SHAHRAM);
        billUtility.disclaimItem(bill, joojehId, SHAHRAM);

        scenario.setBill(bill);
        scenario.addAssert(HAMED, KOOBIDEH_PRICE + JOOJEH_PRICE);
        scenario.addAssert(BillUtility.NOT_OWNED, GHORMEH_PRICE);

        return scenario;
    }

    public BillScenario ownedByHamedLaterSharedByShahram() {

        BillScenario scenario = new BillScenario();
        Bill bill = new Bill();
        UUID id = bill.addItem(KOOBIDEH, KOOBIDEH_PRICE);
        billUtility.claimItem(bill,id, HAMED);
        billUtility.claimItem(bill,id, SHAHRAM);

        scenario.setBill(bill);
        scenario.addAssert(SHAHRAM, KOOBIDEH_PRICE * 0.5);
        scenario.addAssert(HAMED, KOOBIDEH_PRICE * 0.5);
        scenario.addAssert(BillUtility.NOT_OWNED, 0.0);

        return scenario;
    }

    public BillScenario simpleBillShared() {
        BillScenario scenario = new BillScenario();
        Bill bill = new Bill();

        UUID koobidehId = bill.addItem(KOOBIDEH, KOOBIDEH_PRICE);
        UUID joojehId = bill.addItem(JOOJEH, JOOJEH_PRICE);
        UUID ghormehId = bill.addItem(GHORMEH, GHORMEH_PRICE);
        billUtility.claimItem(bill,koobidehId, HAMED);
        billUtility.claimItem(bill,joojehId, SHAHRAM);
        billUtility.claimItemMultiple(bill, ghormehId, SHAHRAM, HAMED);

        scenario.setBill(bill);
        scenario.addAssert(SHAHRAM, JOOJEH_PRICE + .5 * GHORMEH_PRICE);
        scenario.addAssert(HAMED, KOOBIDEH_PRICE + .5 * GHORMEH_PRICE);
        scenario.addAssert(BillUtility.NOT_OWNED, 0.0);

        return scenario;
    }

    public BillScenario sameItemnameOwnedbyDifferentGroups()
    {
        BillScenario scenario = new BillScenario();
        Bill bill = new Bill();

        UUID koobidehId1 = bill.addItem(KOOBIDEH, KOOBIDEH_PRICE);
        UUID koobidehId2 = bill.addItem(KOOBIDEH, KOOBIDEH_PRICE);
        UUID joojehId = bill.addItem(JOOJEH, JOOJEH_PRICE);
        UUID ghormehId = bill.addItem(GHORMEH, GHORMEH_PRICE);

        billUtility.claimItemMultiple(bill, koobidehId1, HAMED, ROSHANAK);
        billUtility.claimItem(bill, koobidehId2, SHAHRAM);
        billUtility.claimItem(bill, joojehId, SHAHRAM);
        billUtility.claimItemMultiple(bill, ghormehId, SHAHRAM, HAMED);
        billUtility.claimItem(bill, koobidehId2, AVA);

        scenario.setBill(bill);
        scenario.addAssert(SHAHRAM, JOOJEH_PRICE + .5 * GHORMEH_PRICE + .5 * KOOBIDEH_PRICE);
        scenario.addAssert(HAMED, .5 * GHORMEH_PRICE + .5 * KOOBIDEH_PRICE);
        scenario.addAssert(ROSHANAK, .5 * KOOBIDEH_PRICE);
        scenario.addAssert(AVA, .5 * KOOBIDEH_PRICE);
        scenario.addAssert(BillUtility.NOT_OWNED, 0.0);

        return scenario;
    }

    public class BillScenario {
        private Bill bill;
        private Map<String, Double> asserts = new HashMap<>();

        public Bill getBill() {
            return bill;
        }

        public void setBill(Bill bill) {
            this.bill = bill;
        }

        public Map<String, Double> getAsserts() {
            return asserts;
        }

        public void addAssert(String o, Double p)
        {
            asserts.put(o,p);
        }
    }
}
