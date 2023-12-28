package dev.tomle.ims.infrastructure.order.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dev.tomle.ims.domain.model.order.SalesOrderLine;

public interface SalesOrderLineRepository extends CrudRepository<SalesOrderLine, Long> {
	public List<SalesOrderLine> findAll();
}
