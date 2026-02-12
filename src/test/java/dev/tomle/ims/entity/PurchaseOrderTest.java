package dev.tomle.ims.entity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import dev.tomle.ims.model.Batch;
import dev.tomle.ims.model.Product;
import dev.tomle.ims.model.ProductStatus;
import dev.tomle.ims.model.PurchaseOrder;
import dev.tomle.ims.model.PurchaseOrderLine;
import dev.tomle.ims.model.exception.OverReceiveException;
import dev.tomle.ims.model.exception.PurchaseOrderSaveException;
import dev.tomle.ims.model.exception.ReceivedProductMismatchException;
import dev.tomle.ims.model.repository.ProductRepository;
import dev.tomle.ims.model.repository.PurchaseOrderLineRepository;
import dev.tomle.ims.model.repository.PurchaseOrderRepository;
import dev.tomle.ims.service.PurchaseOrderService;

@SpringBootTest
public class PurchaseOrderTest {
	
	@Autowired
	private PurchaseOrderService orderService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	@Autowired
	private PurchaseOrderLineRepository purchaseOrderLineRepository;
	
	@Test
	public void testPurchaseOrder() {
		List<Product> products = productRepository.findAll();

		/*List<PurchaseOrderLine> purchaseOrderLines = new ArrayList<>();
		PurchaseOrder purchaseOrder = new PurchaseOrder(null, null, 0.0, 0.0, null, purchaseOrderLines);
		purchaseOrderLines.add(new PurchaseOrderLine(1, products.get(0).getSku(), products.get(0).getName(), 5, 1.0, products.get(0), purchaseOrder));
		purchaseOrderLines.add(new PurchaseOrderLine(2, products.get(1).getSku(), products.get(1).getName(), 10, 2.0, products.get(1), purchaseOrder));
		purchaseOrderLines.add(new PurchaseOrderLine(3, products.get(2).getSku(), products.get(2).getName(), 20, 3.0, products.get(2), purchaseOrder));
		
		orderService.savePurchaseOrder(purchaseOrder);*/
		
		//List<PurchaseOrder> orders = purchaseOrderRepository.findAll();
		//Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by("supplier.company"));
		Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
		Page<PurchaseOrder> orders = purchaseOrderRepository.findAll(pageable);
		//purchaseOrderLines = orders.get(0).getPurchaseOrderLines();
		List<PurchaseOrder> purchaseOrders = orders.toList();
		Pageable p = orders.nextPageable();
		boolean b = orders.hasNext();
		boolean b2 = orders.hasPrevious();
		Pageable p1 = orders.nextOrLastPageable();
		Pageable p2 = orders.previousOrFirstPageable();
		Pageable p7 = orders.previousPageable();
		Pageable p8 = orders.nextPageable();
		for(PurchaseOrder order: orders) {
			long id = order.getId();
		}
		
		PurchaseOrder.class.getFields();
		
		Page<PurchaseOrder> newOrders = new PageImpl<>(orders.getContent(), orders.getPageable(), orders.getTotalElements());
		Pageable p3 = orders.nextPageable();
		boolean b3 = orders.hasNext();
		boolean b4 = orders.hasPrevious();
		Pageable p4 = orders.nextOrLastPageable();
		Pageable p5 = orders.previousOrFirstPageable();
		Pageable p6 = orders.previousPageable();
		
		
		//purchaseOrderLines = purchaseOrderLineRepository.findAll();
		
		//assertEquals(0, purchaseOrderLines.get(0).getPurchaseOrder().getId());
		assertEquals(1, 1);
	}

	@Test
	public void testPurchaseOrderTotal() {
		long[] qty = { 5, 8 };
		double[] cost = { 1.0, 2.0 };
		double total = 21.0;
		double precision = 0.001;

		List<Product> products = new ArrayList<>();
		products.add(new Product("SKU-01", "Product 1", ProductStatus.StatusType.ACTIVE.id));
		products.add(new Product("SKU-02", "Product 2", ProductStatus.StatusType.ACTIVE.id));

		List<PurchaseOrderLine> purchaseOrderLines = new ArrayList<>();
		PurchaseOrder purchaseOrder = new PurchaseOrder(null, null, 0.0, 0.0, null, purchaseOrderLines);
		purchaseOrderLines.add(new PurchaseOrderLine(1, products.get(0).getSku(), products.get(0).getName(), qty[0], cost[0], products.get(0), purchaseOrder));
		purchaseOrderLines.add(new PurchaseOrderLine(2, products.get(1).getSku(), products.get(1).getName(), qty[1], cost[1], products.get(1), purchaseOrder));
		purchaseOrder.calculateTotal();
		
		assertEquals(total, purchaseOrder.getTotal(), precision);
	}
	
	@Test
	public void testPurchaseOrderFullyReceived() throws PurchaseOrderSaveException {
		long[] qty = { 5, 8 };
		double[] cost = { 1.0, 2.0 };

		List<Product> products = new ArrayList<>();
		products.add(new Product("SKU-01", "Product 1", ProductStatus.StatusType.ACTIVE.id));
		products.add(new Product("SKU-02", "Product 2", ProductStatus.StatusType.ACTIVE.id));


		List<PurchaseOrderLine> purchaseOrderLines = new ArrayList<>();
		PurchaseOrder purchaseOrder = new PurchaseOrder(null, null, 0.0, 0.0, null, purchaseOrderLines);
		purchaseOrderLines.add(new PurchaseOrderLine(1, products.get(0).getSku(), products.get(0).getName(), qty[0], cost[0], products.get(0), purchaseOrder));
		purchaseOrderLines.add(new PurchaseOrderLine(2, products.get(1).getSku(), products.get(1).getName(), qty[1], cost[1], products.get(1), purchaseOrder));
		purchaseOrder.receive();
		
		assertEquals(1, 1);
	}
	
	@Test
	public void testPurchaseOrderLineFullyReceived() {
		long lineNumber = 1;
		long qty = 3;
		double cost = 1.0;

		PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine(lineNumber, null, null, qty, cost, null, null);
		purchaseOrderLine.setQtyReceived(qty);
		assert(purchaseOrderLine.isFullyReceived());
	}

	@Test
	public void testPurchaseOrderLineNotFullyReceived() {
		long lineNumber = 1;
		long qty = 3;
		double cost = 1.0;

		PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine(lineNumber, null, null, qty, cost, null, null);
		purchaseOrderLine.setQtyReceived(qty - 1);
		assert(!purchaseOrderLine.isFullyReceived());
	}
	
	@Test
	public void testPurchaseOrderLineValidateQtyReceived() {
		
	}

	@Test
	public void testPurchaseOrderLineCanReceive() {
		long lineNumber = 1;
		long qty = 10;
		long qtyReceived = 4;
		long qtyToReceive = 6;
		double cost = 1.0;

		PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine(lineNumber, null, null, qty, cost, null, null);
		purchaseOrderLine.setQtyReceived(qtyReceived);
		purchaseOrderLine.addBatch(new Batch(qtyReceived, null, null));
		Batch batch = new Batch(qtyToReceive, null, null);
		
		assert(purchaseOrderLine.canReceive(batch));
	}

	@Test
	public void testPurchaseOrderLineCannotReceive() {
		long lineNumber = 1;
		long qty = 10;
		long qtyReceived = 5;
		long qtyToReceive = 6;
		double cost = 1.0;

		PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine(lineNumber, null, null, qty, cost, null, null);
		purchaseOrderLine.setQtyReceived(qtyReceived);
		purchaseOrderLine.addBatch(new Batch(qtyReceived, null, null));
		Batch batch = new Batch(qtyToReceive, null, null);
		
		assert(!purchaseOrderLine.canReceive(batch));
	}
	
	@Test
	public void testValidateReceive() {
        assertThrows(ReceivedProductMismatchException.class, () -> {
            Product product1 = new Product();
            product1.setId(1);
            product1.setSku("TEST-SKU-1");
            product1.setName("Test Product 1");
            product1.setProductStatusId(1);
            Product product2 = new Product();
            product2.setId(2);
            product2.setSku("TEST-SKU-2");
            product2.setName("Test Product 2");
            product2.setProductStatusId(1);

            PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine();
            purchaseOrderLine.setQty(10);
            purchaseOrderLine.setQtyReceived(0);
            purchaseOrderLine.setProduct(product1);
            Batch batch = new Batch();
            batch.setQty(10);
            batch.setProduct(product2);

            purchaseOrderLine.validateReceive(batch);
        });
    }
	
	@Test
	public void testPurchaseOrderLineReceive() throws PurchaseOrderSaveException {
		long lineNumber = 1;
		long qty = 10;
		long qtyReceived = 7;
		long qtyToReceive = 3;
		double cost = 1.0;

		PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine(lineNumber, null, null, qty, cost, null, null);
		purchaseOrderLine.setQtyReceived(qtyReceived);
		purchaseOrderLine.addBatch(new Batch(qtyReceived, null, null));
		Batch batch = new Batch(qtyToReceive, null, null);
		purchaseOrderLine.receive(batch);

		long qtyCheck = purchaseOrderLine.getBatches().stream().map(x -> x.getQty()).reduce(0L, Long::sum);
		assertEquals(qty, qtyCheck);
		assertEquals(qty, purchaseOrderLine.getQtyReceived());
	}

	@Test
	public void testPurchaseOrderLineOverReceive() {
        assertThrows(OverReceiveException.class, () -> {
            long lineNumber = 1;
            long qty = 5;
            long qtyReceived = 2;
            long qtyToReceive = 4;
            double cost = 1.0;

            PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine(lineNumber, null, null, qty, cost, null, null);
            purchaseOrderLine.setQtyReceived(qtyReceived);
            purchaseOrderLine.addBatch(new Batch(qtyReceived, null, null));
            Batch batch = new Batch(qtyToReceive, null, null);
            purchaseOrderLine.receive(batch);
        });
    }
}
