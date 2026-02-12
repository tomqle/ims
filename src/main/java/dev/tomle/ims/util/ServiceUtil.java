package dev.tomle.ims.util;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class ServiceUtil {
	public static Pageable getPageable(int pageNum, int pageSize, String sortBy, boolean desc, List<String> sortByList) {
		String validSortBy = null;
		if(sortByList == null || sortByList.size() == 0) {
			validSortBy = "";
		}
		else if(!sortByList.stream().anyMatch(x -> x.equalsIgnoreCase(sortBy))) {
			validSortBy = sortByList.get(0);
		} else {
			validSortBy = sortBy;
		}
		
		Sort sort = desc ? Sort.by(validSortBy).descending() : Sort.by(validSortBy);
		return PageRequest.of(pageNum, pageSize, sort);
	}
}
