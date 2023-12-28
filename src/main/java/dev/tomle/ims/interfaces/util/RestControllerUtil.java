package dev.tomle.ims.interfaces.util;

public final class RestControllerUtil {

	public final static int getValidatedPageNumber(Integer pageNumber)  {
		if(pageNumber == null) {
			return 0;
		}
		return pageNumber;
	}

	public final static int getValidatedPageSize(Integer pageSize) {
		if(pageSize == null || pageSize == 0) {
			return 100;
		}
		return pageSize;
	}
	
	public final static String getValidatedSortyBy(String sortBy) {
		if(sortBy != null && sortBy.equals("")) {
			return null;
		}
		return sortBy;
	}
	
	public final static boolean getValidatedDesc(Boolean desc) {
		if(desc == null) {
			return false;
		}
		return desc;
	}
}
