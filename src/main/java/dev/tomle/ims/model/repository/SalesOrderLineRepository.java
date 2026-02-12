package dev.tomle.ims.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dev.tomle.ims.model.SalesOrderLine;

public interface SalesOrderLineRepository extends CrudRepository<SalesOrderLine, Long> {
	public List<SalesOrderLine> findAll();
}
