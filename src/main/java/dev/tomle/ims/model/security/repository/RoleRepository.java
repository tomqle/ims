package dev.tomle.ims.model.security.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import dev.tomle.ims.model.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long>, PagingAndSortingRepository<Role, Long> {
	@Query(value = "SELECT r1 FROM Role r1 "
					+ "left join fetch r1.rolePrivileges rp1 "
					+ "left join fetch rp1.privilege ",
			countQuery = "select count(r1) from Role r1")
	public Page<Role> findAll(Pageable pageable);
	@Query(value = "SELECT r1 FROM Role r1 "
					+ "left join fetch r1.rolePrivileges rp1 "
					+ "left join fetch rp1.privilege "
					+ "where lower(r1.name) like lower(concat('%', ?1, '%'))",
			countQuery = "select count(r1) from Role r1 "
						+ "where lower(r1.name) like lower(concat('%', ?1, '%'))")
	public Page<Role> findLikeName(String name, Pageable pageable);
	@Query(value = "SELECT r1 FROM Role r1 "
					+ "left join fetch r1.rolePrivileges rp1 "
					+ "left join fetch rp1.privilege "
					+ "where r1.id = ?1")
	public Optional<Role> findById(long id);
	@Query(value = "SELECT r1 FROM Role r1 "
					+ "left join fetch r1.rolePrivileges rp1 "
					+ "left join fetch rp1.privilege "
					+ "where lower(r1.name) = lower(?1)")
	public Role findByName(String name);
}
