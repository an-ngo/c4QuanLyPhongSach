package com.team5.c4quanlyphongsach.service.customer;

import com.team5.c4quanlyphongsach.model.Customer;
import com.team5.c4quanlyphongsach.service.IGeneralService;

import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {

    void updateBalance(Double balance,Customer customer);
    Optional<Customer> findByEmail(String email);
}
