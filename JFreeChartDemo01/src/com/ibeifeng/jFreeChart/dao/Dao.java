package com.ibeifeng.jFreeChart.dao;

import java.util.HashMap;


/**
 * To get, update and save Datasource  
 *
 */
public class Dao 
{
	/**
	 * To get the data source that will be charted.
	 * @return a intance of HashMap encapsulating the datasource with key-value pair 
	 * 
	 */
	public HashMap<String, Double> getDatasource(){
		HashMap<String, Double> map = new HashMap<>();
		map.put("USA", 21.439);
		map.put("China", 14.140);
		map.put("Japan", 5.154);
		map.put("Germany", 3.863);
		map.put("India", 2.935);
		map.put("other countries", 39.734);
		return map;
	}
}
