package dev.tomle.ims.infrastructure.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import dev.tomle.ims.domain.model.order.PurchaseOrder;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long>, PagingAndSortingRepository<PurchaseOrder, Long> {
	public List<PurchaseOrder> findAll();
	@Query(value = "SELECT po1 FROM PurchaseOrder po1 "
									+ "left join fetch po1.supplier " 
									+ "left join fetch po1.purchaseOrderLines "
									+ "left join fetch po1.purchaseOrderLines.product ",
			countQuery = "SELECT count(po1) FROM PurchaseOrder po1")
	public Page<PurchaseOrder> findAll(Pageable pageable);
	@Query(value = "SELECT po1 FROM PurchaseOrder po1 "
									+ "left join fetch po1.supplier " 
									+ "left join fetch po1.purchaseOrderLines "
									+ "left join fetch po1.purchaseOrderLines.product "
									+ "where po1.id = ?1")
	public Optional<PurchaseOrder> findById(Long id);
}
