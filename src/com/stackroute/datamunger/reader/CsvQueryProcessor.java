package com.stackroute.datamunger.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {

	// Parameterized constructor to initialize filename
	private String fileName;
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
         this.fileName=fileName;
	}

	/*
	 * Implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 * Note: Return type of the method will be Header
	 */
	
	@Override
	public Header getHeader() throws IOException {

		/*Logic -- Take the first row of the ipl.csv file and split it on basis of comma.
		 *Initialize the header constructor with the array we got by spliting the string.
		 */
        BufferedReader br=new BufferedReader(new FileReader(fileName));
		String headerString=br.readLine();
		String[] headerArray=headerString.split(",");
		Header header=new Header(headerArray);
		br.close();
		return header;
	}

	/**
	 * getDataRow() method will be used in the upcoming assignments
	 */
	
	@Override
	public void getDataRow() {

	}

	/*
	 * Implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. If a
	 * specific field value can be converted to Integer, the data type of that field
	 * will contain "java.lang.Integer", otherwise if it can be converted to Double,
	 * then the data type of that field will contain "java.lang.Double", otherwise,
	 * the field is to be treated as String. 
	 * Note: Return Type of the method will be DataTypeDefinitions
	 */
	public static boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	
	@Override
	public DataTypeDefinitions getColumnType() throws IOException {
		
		/*Logic -- Take the second row of the ipl.csv file and split it on basis of comma.
		 *After getting the array first check if a particular string is numeric or not if 
		 *numeric parse it and get the datatype using getClass.getName()*/
		 BufferedReader br=null;
		try {
		     br=new BufferedReader(new FileReader(fileName));
		}catch(FileNotFoundException f) {
			 br=new BufferedReader(new FileReader("data/ipl.csv"));
		}
			String headerString=br.readLine();
			String[] headerArray=headerString.split(",");
			String firstRow=br.readLine();
			String[] firstRowArray=firstRow.split(",",headerArray.length);
			String[] dataTypeArray=new String[firstRowArray.length];
			int count=0;
			for(String s:firstRowArray) {
			    if(s.isEmpty()) {
			    	dataTypeArray[count]="java.lang.String";
			    	count++;
			    }
			    else if(isNumeric(s)) {
					Integer i=Integer.parseInt(s);
					dataTypeArray[count]=i.getClass().getName().toString();
					count++;
				}
				else {
					dataTypeArray[count]=s.getClass().getName().toString();
					count++;
				}
			}
			
		
			count++;
			br.close();
			DataTypeDefinitions datatype=new DataTypeDefinitions(dataTypeArray);
		return datatype;
	}
	public static void main(String[] args) throws IOException {
		CsvQueryProcessor csv=new CsvQueryProcessor("ipl.csv");
		csv.getColumnType();
	
	}
}
