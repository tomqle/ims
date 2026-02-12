package dev.tomle.ims.model.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import dev.tomle.ims.model.security.Privilege;

public interface PrivilegeRepository extends CrudRepository<Privilege, Long>, PagingAndSortingRepository<Privilege, Long> {
	@Query(value = "SELECT p1 FROM Privilege p1 ",
			countQuery = "select count(p1) from Privilege p1")
	public Page<Privilege> findAll(Pageable pageable);
	@Query(value = "SELECT p1 FROM Privilege p1 "
					+ "where lower(p1.name) like lower(concat('%', ?1, '%'))",
			countQuery = "select count(p1) from Privilege p1 "
						+ "where lower(p1.name) like lower(concat('%', ?1, '%'))")
	public Page<Privilege> findLikeName(String name, Pageable pageable);
	@Query(value = "SELECT r1 FROM Privilege r1 where lower(r1.name) = lower(?1)")
	public Privilege findByName(String name);
}
