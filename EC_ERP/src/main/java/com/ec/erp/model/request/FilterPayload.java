package com.ec.erp.model.request;

import java.util.List;

public class FilterPayload {
	
	private List<Filter> filters;

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

}
