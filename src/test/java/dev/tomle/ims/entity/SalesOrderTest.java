package dev.tomle.ims.entity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.tomle.ims.model.Batch;
import dev.tomle.ims.model.Product;
import dev.tomle.ims.model.SalesOrder;
import dev.tomle.ims.model.SalesOrderLine;
import dev.tomle.ims.model.repository.BatchRepository;
import dev.tomle.ims.model.repository.ProductRepository;
import dev.tomle.ims.model.repository.SalesOrderLineRepository;
import dev.tomle.ims.model.repository.SalesOrderRepository;

@SpringBootTest
public class SalesOrderTest {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private BatchRepository batchRepository;
	@Autowired
	private SalesOrderRepository salesOrderRepository;
	@Autowired
	private SalesOrderLineRepository salesOrderLineRepository;

	@Test
	public void testSalesOrder() {
		long lineNumber = 1;
		long batchQty = 10;
		long orderQty = 10;
		List<Product> products = productRepository.findAll();
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
		List<SalesOrderLine> salesOrderLines = salesOrderLineRepository.findAll();
		
		assert(true);
	}
	
	@Test
	public void testSalesOrderQtyAllocated() {
		
	}
	
	@Test
	public void testSalesOrderCanAllocate() {
		long lineNumber = 1;
		long batchQty = 10;
		long orderQty = 10;
		Batch batch = new Batch(batchQty, null, null);
		SalesOrderLine salesOrderLine = new SalesOrderLine(lineNumber, null, null, orderQty, 0, null, null);
		
		assert(salesOrderLine.canAllocate(batch));
	}

	@Test
	public void testSalesOrderCanAllocatePartially() {
		long lineNumber = 1;
		long batchQty = 5;
		long orderQty = 10;
		Batch batch = new Batch(batchQty, null, null);
		SalesOrderLine salesOrderLine = new SalesOrderLine(lineNumber, null, null, orderQty, 0, null, null);
		
		assert(salesOrderLine.canAllocate(batch));
	}
	
	@Test
	public void testSalesOrderCannotAllocate() {
		long lineNumber = 1;
		long batchQty = 10;
		long orderQty = 10;
		Batch batch = new Batch(batchQty, null, null);
		SalesOrderLine salesOrderLine = new SalesOrderLine(lineNumber, null, null, orderQty, 0, null, null);
		batch.allocate(salesOrderLine);
		
		lineNumber = 2;
		orderQty = 1;
		salesOrderLine = new SalesOrderLine(lineNumber, null, null, orderQty, 0, null, null);
		
		assert(!salesOrderLine.canAllocate(batch));
	}

	@Test
	public void testSalesOrderAllocateSingleBatch() {
		long lineNumber = 1;
		long batchQty = 10;
		long orderQty1 = 5;
		Batch batch = new Batch(batchQty, null, null);
		SalesOrderLine salesOrderLine1 = new SalesOrderLine(lineNumber, null, null, orderQty1, 0, null, null);
		salesOrderLine1.allocate(batch);

		long qtyCheck = batch.getBatchOrderLines().stream().map(x -> x.getSalesOrderLine().getQtyAllocated()).reduce(0L, Long::sum);
		assertEquals(orderQty1, qtyCheck);
		assertEquals(orderQty1, batch.getQtyAllocated());
		assertEquals(orderQty1, salesOrderLine1.getQtyAllocated());

		long lineNumber2 = 2;
		long orderQty2 = 3;
		SalesOrderLine salesOrderLine2 = new SalesOrderLine(lineNumber2, null, null, orderQty2, 0, null, null);
		salesOrderLine2.allocate(batch);
		qtyCheck = batch.getBatchOrderLines().stream().map(x -> x.getSalesOrderLine().getQtyAllocated()).reduce(0L, Long::sum);
		assertEquals(orderQty1 + orderQty2, qtyCheck);
		assertEquals(orderQty1 + orderQty2, batch.getQtyAllocated());
		assertEquals(orderQty2, salesOrderLine2.getQtyAllocated());
		
		long lineNumber3 = 3;
		long orderQty3 = 4;
		SalesOrderLine salesOrderLine3 = new SalesOrderLine(lineNumber3, null, null, orderQty3, 0, null, null);
		salesOrderLine3.allocate(batch);
		qtyCheck = batch.getBatchOrderLines().stream().map(x -> x.getSalesOrderLine().getQtyAllocated()).reduce(0L, Long::sum);
		assertEquals(batchQty, qtyCheck);
		assertEquals(batchQty, batch.getQtyAllocated());
		assertEquals(batchQty - orderQty1 - orderQty2, salesOrderLine3.getQtyAllocated());
	}

	@Test
	public void testSalesOrderAllocateMultipleBatches() {
		long lineNumber = 1;
		long[] batchQty = { 8, 5 };
		long orderQty = 10;
		List<Batch> batches = new ArrayList<>();
		batches.add(new Batch(batchQty[0], null, null));
		batches.add(new Batch(batchQty[1], null, null));
		SalesOrderLine salesOrderLine = new SalesOrderLine(lineNumber, null, null, orderQty, 0, null, null);
		salesOrderLine.allocate(batches.get(0));

		long qtyCheck = salesOrderLine.getBatchOrderLines().stream().map(x -> x.getSalesOrderLine().getQtyAllocated()).reduce(0L, Long::sum);
		assertEquals(batchQty[0], qtyCheck);
		assertEquals(batchQty[0], batches.get(0).getQtyAllocated());
		assertEquals(batchQty[0], salesOrderLine.getQtyAllocated());
		
		salesOrderLine.allocate(batches.get(1));

		qtyCheck = salesOrderLine.getBatchOrderLines().stream().map(x -> x.getBatch().getQtyAllocated()).reduce(0L, Long::sum);
		assertEquals(orderQty - batchQty[0], batches.get(1).getQtyAllocated());
		assertEquals(orderQty, qtyCheck);
		assertEquals(orderQty, salesOrderLine.getQtyAllocated());
	}
}
