package dev.tomle.ims.infrastructure.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import dev.tomle.ims.domain.model.security.User;

public interface UserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {
	@Query(value = "SELECT u1 FROM USERS u1 left join fetch u1.roles",
			countQuery = "select count(u1) from USERS u1")
	public Page<User> findAll(Pageable pageable);
	@Query(value = "SELECT u1 FROM USERS u1 "
						+ "left join fetch u1.roles "
					+ "where lower(u1.username) like lower(concat('%', ?1, '%'))",
			countQuery = "select count(u1) from USERS u1 "
						+ "where lower(u1.username) like lower(concat('%', ?1, '%'))")
	public Page<User> findLikeUsername(String username, Pageable pageable);
	@Query(value = "SELECT u1 FROM USERS u1 "
					+ "left join fetch u1.roles " 
					+ "where lower(u1.username) = lower(?1)")
	public User findByUsername(String username);
}
