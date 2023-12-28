package dev.tomle.ims.infrastructure.order.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import dev.tomle.ims.domain.model.order.Batch;

public interface BatchRepository extends CrudRepository<Batch, Long>, PagingAndSortingRepository<Batch, Long> {
	public List<Batch> findAll();
	@Query(value = "SELECT b1 FROM Batch b1"
					+ " left join fetch b1.product"
					+ " left join fetch b1.purchaseOrderLine"
					+ " left join fetch b1.purchaseOrderLine.purchaseOrder"
					+ " left join fetch b1.purchaseOrderLine.purchaseOrder.supplier",
			countQuery = "SELECT count(b1) FROM Batch b1")
	public Page<Batch> findAll(Pageable pageable);
	@Query(value = "SELECT b1 FROM Batch b1"
					+ " left join fetch b1.product"
					+ " left join fetch b1.purchaseOrderLine"
					+ " left join fetch b1.purchaseOrderLine.purchaseOrder"
					+ " left join fetch b1.purchaseOrderLine.purchaseOrder.supplier"
					+ " where b1.product.id = ?1",
			countQuery = "SELECT count(b1) FROM Batch b1 where b1.product.id = ?1")
	public Page<Batch> findByProductId(long productId, Pageable pageable);
	@Query(value = "SELECT b1 FROM Batch b1"
					+ " left join fetch b1.product"
					+ " left join fetch b1.purchaseOrderLine"
					+ " left join fetch b1.purchaseOrderLine.purchaseOrder"
					+ " left join fetch b1.purchaseOrderLine.purchaseOrder.supplier"
					+ " where b1.product.id = ?1"
					+ " and b1.qtyAllocated < b1.qty",
			countQuery = "SELECT count(b1) FROM Batch b1 where b1.product.id = ?1")
	public Page<Batch> findUnallocatedByProductId(long productId, Pageable pageable);
	
	// TODO: add a query to link allocated batches to sales order
}
