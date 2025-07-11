package com.saathisquare.societyservice.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginatedResponse<T> {
	private List<T> content;
	private long totalElements;
	private int totalPages;
	private int currentPage;
	private int pageSize;

	public PaginatedResponse(List<T> content, long totalElements, int totalPages, int currentPage, int pageSize) {
		this.content = content;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
}
