package dev.tomle.ims.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import dev.tomle.ims.model.Batch;
import dev.tomle.ims.model.Product;
import dev.tomle.ims.model.PurchaseOrderLine;
import dev.tomle.ims.model.SalesOrder;
import dev.tomle.ims.model.SalesOrderLine;
import dev.tomle.ims.model.dto.PurchaseOrderLineDTO;
import dev.tomle.ims.model.exception.PurchaseOrderSaveException;
import dev.tomle.ims.model.repository.BatchRepository;
import dev.tomle.ims.model.repository.ProductRepository;
import dev.tomle.ims.model.repository.PurchaseOrderLineRepository;
import dev.tomle.ims.model.repository.SalesOrderLineRepository;
import dev.tomle.ims.model.repository.SalesOrderRepository;

@SpringBootTest
public class BatchTest {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private BatchRepository batchRepository;
	@Autowired
	private SalesOrderRepository salesOrderRepository;
	@Autowired
	private SalesOrderLineRepository salesOrderLineRepository;
	@Autowired
	private PurchaseOrderLineRepository purchaseOrderLineRepository;

	@Test
	public void testBatch() throws PurchaseOrderSaveException {
		long lineNumber = 1;
		long batchQty = 10;
		long orderQty = 10;
		/*List<Product> products = productRepository.findAll();
		Batch batch = new Batch(batchQty, null, products.get(0));
		SalesOrder salesOrder = new SalesOrder(0, null, null, 0, null, null);
		SalesOrderLine salesOrderLine = new SalesOrderLine(lineNumber, products.get(0).getSku(), products.get(0).getName(), orderQty, 0, products.get(0), null);
		salesOrder.addSalesOrderLine(salesOrderLine);
		salesOrderRepository.save(salesOrder);
		batch.allocate(salesOrderLine);
		batchRepository.save(batch);
		//salesOrderLineRepository.save(salesOrderLine);
		
		List<Batch> batches = batchRepository.findAll();
		List<SalesOrder> salesOrders = salesOrderRepository.findAll();
		List<SalesOrderLine> salesOrderLines = salesOrderLineRepository.findAll();*/
		
		List<PurchaseOrderLine> poLines = purchaseOrderLineRepository.findAll();
		for(PurchaseOrderLine poLine: poLines) {
			Batch batch = new Batch(poLine.getQty(), null, poLine.getProduct());
			batch = batchRepository.save(batch);
			poLine.receive(batch);
			purchaseOrderLineRepository.save(poLine);
			
		}

		Pageable pageable = PageRequest.of(0, 10);
		Page<Batch> batches1 = batchRepository.findByProductId(1, pageable);
		Page<Batch> batches2 = batchRepository.findByProductId(2, pageable);
		Page<Batch> batches3 = batchRepository.findByProductId(3, pageable);
		
		assert(true);
	}
	
	@Test
	public void testBatchQtyAllocated() {
		
	}
	
	@Test
	public void testBatchCanAllocate() {
		long lineNumber = 1;
		long batchQty = 10;
		long orderQty = 10;
		Batch batch = new Batch(batchQty, null, null);
		SalesOrderLine salesOrderLine = new SalesOrderLine(lineNumber, null, null, orderQty, 0, null, null);
		
		assert(batch.canAllocate(salesOrderLine));
	}

	@Test
	public void testBatchCanAllocatePartially() {
		long lineNumber = 1;
		long batchQty = 5;
		long orderQty = 10;
		Batch batch = new Batch(batchQty, null, null);
		SalesOrderLine salesOrderLine = new SalesOrderLine(lineNumber, null, null, orderQty, 0, null, null);
		
		assert(batch.canAllocate(salesOrderLine));
	}
	
	@Test
	public void testBatchCannotAllocate() {
		long lineNumber = 1;
		long batchQty = 10;
		long orderQty = 10;
		Batch batch = new Batch(batchQty, null, null);
		SalesOrderLine salesOrderLine = new SalesOrderLine(lineNumber, null, null, orderQty, 0, null, null);
		batch.allocate(salesOrderLine);
		
		lineNumber = 2;
		orderQty = 1;
		salesOrderLine = new SalesOrderLine(lineNumber, null, null, orderQty, 0, null, null);
		
		assert(!batch.canAllocate(salesOrderLine));
	}

	@Test
	public void testBatchAllocate() {
		long lineNumber = 1;
		long batchQty = 10;
		long orderQty1 = 5;
		Batch batch = new Batch(batchQty, null, null);
		SalesOrderLine salesOrderLine1 = new SalesOrderLine(lineNumber, null, null, orderQty1, 0, null, null);
		batch.allocate(salesOrderLine1);

		long qtyCheck = batch.getBatchOrderLines().stream().map(x -> x.getSalesOrderLine().getQtyAllocated()).reduce(0L, Long::sum);
		assertEquals(orderQty1, qtyCheck);
		assertEquals(orderQty1, batch.getQtyAllocated());
		assertEquals(orderQty1, salesOrderLine1.getQtyAllocated());

		long lineNumber2 = 2;
		long orderQty2 = 3;
		SalesOrderLine salesOrderLine2 = new SalesOrderLine(lineNumber2, null, null, orderQty2, 0, null, null);
		batch.allocate(salesOrderLine2);
		qtyCheck = batch.getBatchOrderLines().stream().map(x -> x.getSalesOrderLine().getQtyAllocated()).reduce(0L, Long::sum);
		assertEquals(orderQty1 + orderQty2, qtyCheck);
		assertEquals(orderQty1 + orderQty2, batch.getQtyAllocated());
		assertEquals(orderQty2, salesOrderLine2.getQtyAllocated());
		
		long lineNumber3 = 3;
		long orderQty3 = 4;
		SalesOrderLine salesOrderLine3 = new SalesOrderLine(lineNumber3, null, null, orderQty3, 0, null, null);
		batch.allocate(salesOrderLine3);
		qtyCheck = batch.getBatchOrderLines().stream().map(x -> x.getSalesOrderLine().getQtyAllocated()).reduce(0L, Long::sum);
		assertEquals(batchQty, qtyCheck);
		assertEquals(batchQty, batch.getQtyAllocated());
		assertEquals(batchQty - orderQty1 - orderQty2, salesOrderLine3.getQtyAllocated());
	}
}
