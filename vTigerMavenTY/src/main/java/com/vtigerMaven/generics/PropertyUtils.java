package com.vtigerMaven.generics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class is used to read data from property file.
 * @author Baishali
 *
 */
public class PropertyUtils {
/**
 * The below function reads data from property file.
 * @param Key
 * @return
 * @throws IOException
 */
	public String fetchDataFromFile(String Key) throws IOException
	{
		FileInputStream fis = new FileInputStream("./src/test/resources/commondata.properties");
		//cCreate java reference of properties. 
		Properties pobj = new Properties();
		//Load all the keys to the properties object.
		pobj.load(fis);
		//Returns the particular value for the given key from the properties object.
		String value = pobj.getProperty(Key);
		return value;
		
	}
}
