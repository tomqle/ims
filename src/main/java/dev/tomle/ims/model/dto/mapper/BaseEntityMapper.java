package dev.tomle.ims.model.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import dev.tomle.ims.model.BaseEntity;
import dev.tomle.ims.model.dto.BaseEntityDTO;

public interface BaseEntityMapper<T extends BaseEntity, S extends BaseEntityDTO> {
	public S toDto(T t);
	public T toDomain(S s);
	default void setBaseEntityMembers(T t, S s) {
		if(s != null && t != null) {
			s.id = t.getId();
			s.createdDate = t.getCreatedDate();
			s.createdBy = t.getCreatedBy();
			s.lastModDate = t.getLastModDate();
			s.lastModBy = t.getLastModBy();
		}
	}
	default void setBaseEntityMembers(S s, T t) {
		if(t != null && s != null) {
			t.setId(s.id);
			t.setCreatedDate(s.createdDate);
			t.setCreatedBy(s.createdBy);
			t.setLastModDate(s.lastModDate);
			t.setLastModBy(s.lastModBy);
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
