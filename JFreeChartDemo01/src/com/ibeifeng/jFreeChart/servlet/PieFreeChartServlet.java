package com.ibeifeng.jFreeChart.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.*;
import org.jfree.chart.util.SortOrder;
import org.jfree.data.general.DefaultPieDataset;
import com.ibeifeng.jFreeChart.dao.Dao;


/**
 * To process the request from Client.
 * 
 */
@WebServlet("/PieFreeChartServlet")		//determine servlet mapping pattern
public class PieFreeChartServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;	//serialVersionUID for the serializable class PieFreeChartServlet 
     
    /**
     * To process the GET-request from Client (browser)
     * @param request instance of HttpServletRequest encapsulating the request-data from client
     * @param response instance of HttpServletResponse encapsulating the response-data sent back to client
     * 
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);	//no matter GET or POST requests, it always is dealed with method doPost
	}

	
	/**
     * To process the POST-request from Client (browser)
     * @param request instance of HttpServletRequest encapsulating the request-data from client
     * @param response instance of HttpServletResponse encapsulating the response-data sent back to client
     * 
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get data source that will be charted, and save them in the instance of DefaultPieDataset 
		DefaultPieDataset dataset = new DefaultPieDataset();		//create an instance of DefaultPieDataset that will save datasource
		HashMap<String, Double> map = new Dao().getDatasource();	//get datasource from Dao-layer (persistence layer)
		Set<Map.Entry<String, Double>> entries = map.entrySet();	//get entries from map
		for(Map.Entry<String, Double> entry : entries) {			
			dataset.setValue(entry.getKey(), entry.getValue());		//save data source into the instance dataset of DefaultPieDataset
			dataset.sortByValues(SortOrder.DESCENDING);				//set new order of entries: descending
		}
		
		JFreeChart pieChart = ChartFactory.createPieChart("Global GDP in 2019", dataset);	//create a chart for data source
		
		//send this chart to client (browser)
		try(OutputStream out = response.getOutputStream()) {		//get outputstream of response
			response.setContentType("image/png");					//specify the type of message sent to the client
			ChartUtils.writeChartAsPNG(out, pieChart, 500, 350); 	//write the chart into outputstream
		}catch(IOException e) {		//outputstream will be closed automatic
			e.printStackTrace();
		}
	}
	
}
