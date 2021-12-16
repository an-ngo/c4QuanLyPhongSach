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

    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

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

    @Override
    public void updateBalance(Double balance, Customer customer) {
        entityManager = sessionFactory.createEntityManager();
        String criteriaQuery = "UPDATE Customer set money = :money WHERE id = :customer_id";
        Query query =  entityManager.createQuery(criteriaQuery,Customer.class);
        query.setParameter("money",customer.getMoney()+balance);
        query.setParameter("customer_id",customer.getId());
        query.executeUpdate();
    }


}
