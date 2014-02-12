package com.example.robospicetest;

import java.util.List;

import com.google.api.client.util.Key;

public class DataModel {
	@Key
	private List<Plant> PLANT;


	public List<Plant> getPlants() {
		return PLANT;
	}


}
