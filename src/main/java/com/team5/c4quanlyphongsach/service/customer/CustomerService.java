package com.team5.c4quanlyphongsach.service.customer;

import com.team5.c4quanlyphongsach.model.Customer;
import com.team5.c4quanlyphongsach.repository.customer.ICustomerRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

<<<<<<< HEAD

=======
>>>>>>> 51523b8c277df1ae36c842df39916043b690c1cc
    @Override
    public Optional<Customer> findByEmail(String email){
        return customerRepository.findCustomerByEmail(email);
    }
<<<<<<< HEAD
    @Override
=======

        @Override
>>>>>>> 51523b8c277df1ae36c842df39916043b690c1cc
    public void updateBalance(Double balance, Long id) {
        customerRepository.updateBalance(balance,id);
    }
}
