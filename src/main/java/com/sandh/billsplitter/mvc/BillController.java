package com.sandh.billsplitter.mvc;

import com.sandh.billsplitter.domain.Bill;
import com.sandh.billsplitter.services.BillUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by user on 19/09/15.
 */

@RestController
public class BillController {
    private BillUtility billUtility = new BillUtility();
    private static final Map<UUID,Bill> billsCache= new ConcurrentHashMap<UUID,Bill>();

    @RequestMapping(value = "/bill/add/", method = RequestMethod.PUT)
      public ResponseEntity<Bill> createBill(
            @RequestParam(value="food") String foodName,
            @RequestParam(value="price") String price,
            @RequestParam(value="billId") String billId

    )
    {
        //http://localhost:8080/billsplitter/bill/add/?food=ghormeh&price=11.0&billId=???
        if(null == foodName || null == price || null == billId)
            return new ResponseEntity<Bill>(HttpStatus.BAD_REQUEST);
        Bill bill = billsCache.get(UUID.fromString(billId));
        if(null == bill)
            return new ResponseEntity<Bill>(HttpStatus.BAD_REQUEST);
        if(!foodName.equals(""))
            bill.addItem(foodName,Double.parseDouble(price));

        return new ResponseEntity<Bill>(bill,HttpStatus.OK);
    }

    @RequestMapping(value = "/bill/create/", method = RequestMethod.PUT)
    public ResponseEntity<String> createBill2()
    {
        //http://localhost:8080/billsplitter/bill/create/
        Bill bill = new Bill();
        billsCache.put(bill.getId(), bill);
       return new ResponseEntity<String>(bill.getId().toString(),HttpStatus.OK);
    }

}
