package dev.tomle.ims.infrastructure.contact.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dev.tomle.ims.domain.model.contact.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findAll();
}
