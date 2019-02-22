package com.footprint.boot.web;

import com.footprint.boot.vo.Customer;
import com.footprint.boot.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/users")
public class MyRestController {
    private static final Logger logger = LoggerFactory.getLogger(MyRestController.class);

    @RequestMapping(value="/{userId}", method= RequestMethod.GET)
    public User getUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        logger.info("查询用户信息");
        return user;
    }

    @RequestMapping(value="/{userId}/customers", method=RequestMethod.GET)
    List<Customer> getUserCustomers(@PathVariable Long userId) {
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(userId);
        customers.add(customer);
        logger.info("查询用户下面的所有客户信息");
        return customers;
    }

    @RequestMapping(value="/del/{user}", method=RequestMethod.GET)
    public User deleteUser(@PathVariable Long user) {
        logger.info("删除用户信息");
        if(user != null) throw new NullPointerException("空指针异常");
        return new User();
    }

}