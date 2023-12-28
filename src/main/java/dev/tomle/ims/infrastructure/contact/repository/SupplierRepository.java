package dev.tomle.ims.infrastructure.contact.repository;

import org.springframework.data.repository.CrudRepository;

import dev.tomle.ims.domain.model.contact.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {

}
