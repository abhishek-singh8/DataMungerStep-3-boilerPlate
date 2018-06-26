package com.stackroute.datamunger.query;

import java.util.Arrays;

public class DataTypeDefinitions {

	/*
	 * This class should contain a member variable which is a String array, to hold
	 * the data type for all columns for all data types
	 */
   private String[] dataTypes;
   
	public DataTypeDefinitions(String[] dataTypes) {
	this.dataTypes = dataTypes;
    }

	public String[] getDataTypes() {
		return dataTypes;
	}

	@Override
	public String toString() {
		return "DataTypeDefinitions [dataTypes=" + Arrays.toString(dataTypes) + "]";
	}
	
}
