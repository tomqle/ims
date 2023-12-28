package dev.tomle.ims.interfaces.shared.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import dev.tomle.ims.domain.shared.BaseEntity;

public interface BaseEntityMapper<T extends BaseEntity, S extends BaseEntityDTO> {
	public S toDto(T t);
	public T toDomain(S s);
	default void setBaseEntityMembers(T t, S s) {
		if(s != null && t != null) {
			s.setId(t.getId());
			s.setCreatedDate(t.getCreatedDate());
			s.setCreatedBy(t.getCreatedBy());
			s.setLastModifiedDate(t.getLastModifiedDate());
			s.setLastModifiedBy(t.getLastModifiedBy());
		}
	}
	default void setBaseEntityMembers(S s, T t) {
		if(t != null && s != null) {
			t.setId(s.getId());
			t.setCreatedDate(s.getCreatedDate());
			t.setCreatedBy(s.getCreatedBy());
			t.setLastModifiedDate(s.getLastModifiedDate());
			t.setLastModifiedBy(s.getLastModifiedBy());
		}
	}
	default List<S> toDto(List<T> t) {
		return t == null ? null : t.stream().map(x -> toDto(x)).collect(Collectors.toCollection(ArrayList::new));
	}
	default List<T> toDomain(List<S> s) {
		return s == null ? null : s.stream().map(x -> toDomain(x)).collect(Collectors.toCollection(ArrayList::new));
	}
	default Page<S> toDto(Page<T> pageT) {
		Page<S> usersPage = null;
		if(pageT != null) {
			List<S> userDTOs = pageT.getContent() == null ? null : pageT.getContent().stream().map(x -> toDto(x)).collect(Collectors.toCollection(ArrayList::new));
			usersPage = new PageImpl<>(userDTOs, pageT.getPageable(), pageT.getTotalElements());
		}
		return usersPage;
	}
}
