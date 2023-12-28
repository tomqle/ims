package dev.tomle.ims.infrastructure.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import dev.tomle.ims.domain.model.order.PurchaseOrderLine;

public interface PurchaseOrderLineRepository extends CrudRepository<PurchaseOrderLine, Long> {
	@Query(value = "SELECT pl1 FROM PurchaseOrderLine pl1"
					+ " left join fetch pl1.product"
					+ " left join fetch pl1.purchaseOrder"
					+ " left join fetch pl1.purchaseOrder.supplier"
					+ " left join fetch pl1.batches")
	public List<PurchaseOrderLine> findAll();
	@Query(value = "SELECT pl1 FROM PurchaseOrderLine pl1 left join fetch pl1.batches where pl1.id=?1")
	public Optional<PurchaseOrderLine> findById(long id);
}
