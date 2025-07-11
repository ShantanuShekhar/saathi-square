package com.saathisquare.societyservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocietyDataRequest {
	private String createdBy;
	private int pageNo;
	private int pageSize;
}
