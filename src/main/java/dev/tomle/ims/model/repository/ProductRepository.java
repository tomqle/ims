package dev.tomle.ims.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import dev.tomle.ims.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
	public List<Product> findAll();
	@Query(value="select p1 from Product p1",
			countQuery = "select count(p1) from Product p1")
	public Page<Product> findAll(Pageable pageable);
	@Query(value="select p1 from Product p1"
				+ " where p1.statusId = 1",
			countQuery="select count(p1) from Product p1"
				+ " where p1.statusId = 1")
	public Page<Product> findAllActive(Pageable pageable);
	@Query(value="select p1 from Product p1"
				+ " where p1.statusId = 1"
					+ " and lower(p1.sku) like lower(concat('%', ?1, '%'))"
				+ " order by p1.sku",
			countQuery="select count(p1) from Product p1"
				+ " where p1.statusId = 1"
					+ " and lower(p1.sku) like lower(concat('%', ?1, '%'))"
				+ " order by p1.sku")
	public Page<Product> findActiveBySku(String sku, Pageable pageable);
	@Query(value="select p1 from Product p1"
				+ " where p1.statusId = 1"
					+ " and lower(p1.name) like lower(concat('%', ?1, '%'))"
				+ " order by p1.name",
			countQuery="select count(p1) from Product p1"
				+ " where p1.statusId = 1"
					+ " and lower(p1.name) like lower(concat('%', ?1, '%'))"
				+ " order by p1.name")
	public Page<Product> findActiveByName(String name, Pageable pageable);
}
