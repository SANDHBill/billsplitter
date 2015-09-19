package com.sandh.billsplitter.mvc;

import com.sandh.billsplitter.domain.Bill;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by user on 19/09/15.
 */

@RestController
public class BillController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final Bill bill_ = new Bill();

    @RequestMapping("/bill")
    public Bill bill(
            @RequestParam(value="operation", defaultValue="") String operation,
            @RequestParam(value="food", defaultValue="") String foodName,
            @RequestParam(value="price", defaultValue="0") String price
            )
    {
        //http://localhost:8080/billsplitter/bill?operation=add&food=ghormeh&price=11.0
        if(operation.equals("add") && !foodName.equals(""))
            bill_.addItem(foodName,Double.parseDouble(price));
        return bill_;
    }
}
