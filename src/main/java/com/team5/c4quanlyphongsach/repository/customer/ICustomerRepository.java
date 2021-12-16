package com.team5.c4quanlyphongsach.repository.customer;

import com.team5.c4quanlyphongsach.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {
<<<<<<< HEAD
    Optional<Customer> findCustomerByEmail(String email);
=======
    @Query("UPDATE Customer set money = :money WHERE id = :customer_id")
    void updateBalance(@Param("money") Double balance, @Param("customer_id")Long id);
>>>>>>> 698eea05efaca642e706b47b1c8fb0fdf0d250f5
}
