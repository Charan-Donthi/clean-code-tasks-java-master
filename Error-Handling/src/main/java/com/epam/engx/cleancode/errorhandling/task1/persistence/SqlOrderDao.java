package com.epam.engx.cleancode.errorhandling.task1.persistence;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Address;
import com.epam.engx.cleancode.errorhandling.task1.OrderDao;
import com.epam.engx.cleancode.errorhandling.task1.persistence.thirdpartyjar.SqlService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlOrderDao implements OrderDao {

    SqlService sqlService;

    public List<Address> getOrderAddresses(String userId) throws SQLException {
        List<Address> addresses = new ArrayList<Address>();
        for (Map addressData : sqlService.queryUserOrderAddress(userId))
            addresses.add(new Address(addressData));
        return addresses;
    }
}
