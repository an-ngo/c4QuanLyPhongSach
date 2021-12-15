package com.team5.c4quanlyphongsach.repository.customer;

import com.team5.c4quanlyphongsach.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {
}
