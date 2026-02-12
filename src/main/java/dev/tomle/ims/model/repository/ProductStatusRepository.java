package dev.tomle.ims.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository ;

import dev.tomle.ims.model.ProductStatus;

public interface ProductStatusRepository extends CrudRepository<ProductStatus, Long>, PagingAndSortingRepository<ProductStatus, Long> {
	@Query(value="select ps1 from productstatus ps1",
			countQuery = "select count(ps1) from productstatus ps1")
	public Page<ProductStatus> findAll(Pageable pageable);

}
