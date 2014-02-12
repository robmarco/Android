package com.example.robospicetest;

import com.google.api.client.util.Key;

public class Plant {
	@Key
	private String COMMON;
	@Key
	private String BOTANICAL;
	
	public String getCommonName() {
		return COMMON;
	}
	public String getBotanicalName() {
		return BOTANICAL;
	}
}
