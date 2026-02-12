package dev.tomle.ims.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dev.tomle.ims.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findAll();
}
