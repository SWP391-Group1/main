package com.management.clinic.paging;


import com.management.clinic.sort.Sorter;

public interface Pageable {

	Integer getPage();
	Integer getOffset();
	Integer getLimit();
	Sorter getSorter();
}
