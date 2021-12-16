package com.team5.c4quanlyphongsach.service.customer;

import com.team5.c4quanlyphongsach.model.Customer;
import com.team5.c4quanlyphongsach.service.IGeneralService;

public interface ICustomerService extends IGeneralService<Customer> {

    void updateBalance(Double balance,Customer customer);
}
