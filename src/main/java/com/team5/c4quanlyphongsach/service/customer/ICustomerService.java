package com.team5.c4quanlyphongsach.service.customer;

import com.team5.c4quanlyphongsach.model.Customer;
import com.team5.c4quanlyphongsach.service.IGeneralService;

import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {


    Optional<Customer> findByEmail(String email);
    void updateBalance(Double amount, Long id);
}
