package dev.tomle.ims.infrastructure.order.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import dev.tomle.ims.domain.model.order.SalesOrder;

public interface SalesOrderRepository extends CrudRepository<SalesOrder, Long>, PagingAndSortingRepository<SalesOrder, Long> {
	public List<SalesOrder> findAll();
	@Query(value = "SELECT so1 FROM SalesOrder so1 left join fetch so1.customer",
			countQuery = "SELECT count(so1) FROM SalesOrder so1")
	public Page<SalesOrder> findAll(Pageable pageable);
}
