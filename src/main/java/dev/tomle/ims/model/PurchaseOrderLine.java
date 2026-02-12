package dev.tomle.ims.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dev.tomle.ims.model.exception.OrderValidationException;
import dev.tomle.ims.model.exception.OverReceiveException;
import dev.tomle.ims.model.exception.PurchaseOrderSaveException;
import dev.tomle.ims.model.exception.ReceivedProductMismatchException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name="purchaseorderline")
public class PurchaseOrderLine extends BaseEntity {

	@Column(name = "linenumber")
	private long lineNumber;
	private String sku;
	@Column(name = "productname")
	private String productName;
	private long qty;
	@Column(name = "qtyreceived")
	private long qtyReceived;
	@Column(name = "basecost")
	private double baseCost;
	private double cost;
	private Date eta;
	
	@ManyToOne
	@JoinColumn(name = "purchaseorderid")
	private PurchaseOrder purchaseOrder;
	@ManyToOne
	@JoinColumn(name = "productid")
	private Product product;
	@OneToMany(mappedBy = "purchaseOrderLine", cascade = CascadeType.ALL)
	private List<Batch> batches;
	
	public PurchaseOrderLine() {
		this(0, 0, null, null, 0, 0.0, null, null);
		qtyReceived = 0;
	}

	public PurchaseOrderLine(long id, long lineNumber, String sku, String productName, long qty, double baseCost, Product product, PurchaseOrder purchaseOrder) {
		this(lineNumber, sku, productName, qty, baseCost, product, purchaseOrder);
		this.id = id;
	}

	public PurchaseOrderLine(long lineNumber, String sku, String productName, long qty, double baseCost, Product product, PurchaseOrder purchaseOrder) {
		this.lineNumber = lineNumber;
		this.sku = sku;
		this.productName = productName;
		this.qty = qty;
		this.baseCost = baseCost;
		this.cost = baseCost;
		this.product = product;
		this.purchaseOrder = purchaseOrder;
		this.batches = new ArrayList<>();
	}
	
	public long getLineNumber() {
		return lineNumber;
	}
	
	public void setLineNumber(long lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	public long getQtyReceived() {
		return qtyReceived;
	}

	public void setQtyReceived(long qtyReceived) {
		this.qtyReceived = qtyReceived;
	}

	public double getBaseCost() {
		return baseCost;
	}
	
	public void setBaseCost(double baseCost) {
		this.baseCost = baseCost;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public Date getEta() {
		return eta;
	}
	
	public void setEta(Date eta) {
		this.eta = eta;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}
	
	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public List<Batch> getBatches() {
		return batches;
	}
	
	public void addBatch(Batch batch) {
		if(batches == null) {
			batches = new ArrayList<>();
		}
		batches.add(batch);
	}
	
	public void setBatches(List<Batch> batches) {
		this.batches = batches;
	}
	
	public void validateOrderLine() throws OrderValidationException {
		if(getProduct().getSku() != getSku()) {
			throw new OrderValidationException("Order line has incorrect sku.");
		}
	}
	
	public void validateQtyReceived() {
		if((batches == null && qtyReceived != 0)
				|| batches.stream().map(x -> x.getQty()).reduce(0L, Long::sum) != qtyReceived) {
		}
	}
	
	public void validateReceive(Batch batch) throws PurchaseOrderSaveException {
		if(!canReceive(batch)) {
			throw new OverReceiveException(this);
		}
		if(product == null && batch.getProduct() != null
				|| product != null && batch.getProduct() == null
				|| product != null && batch.getProduct() != null && product.getId() != batch.getProduct().getId()){
			throw new ReceivedProductMismatchException(product, batch.getProduct());
		}
	}
	
	public boolean isFullyReceived() {
		return qty == qtyReceived;
	}
	
	public boolean canReceive(Batch batch) {
		if(batch.getQty() > qty - qtyReceived) {
			return false;
		}
		return true;
	}
	
	public void receive(Batch batch) throws PurchaseOrderSaveException {
		validateQtyReceived();
		validateReceive(batch);
		batch.setPurchaseOrderLine(this);
		addBatch(batch);
		qtyReceived += batch.getQty();
	}
	
	public void unreceive(long[] batchLocationIds) {
		
	}
}
