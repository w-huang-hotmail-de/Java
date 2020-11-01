package com.ibeifeng.jFreeChart.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
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

/**
 * Servlet implementation class PieFreeChartServlet
 */
@WebServlet("/PieFreeChartServlet")
public class PieFreeChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PieFreeChartServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);	//no matter get or post requests, we always work with method doPost
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the data source which will be charted, and save them in an object of Type DefaultPieDataset 
		DefaultPieDataset dataset = new DefaultPieDataset();	
		HashMap<String, Double> map = this.getDatasource();			//get the data source from outside
		Set<Map.Entry<String, Double>> entries = map.entrySet();
		for(Map.Entry<String, Double> entry : entries) {				//fill the data source into the object dataset
			dataset.setValue(entry.getKey(), entry.getValue());
			dataset.sortByValues(SortOrder.DESCENDING);
		}
		
		//create chart for the data source
		JFreeChart pieChart = ChartFactory.createPieChart("Globales BIP im Jahr 2019", dataset);
		
		//send this chart to client (browser)
		try(OutputStream out = response.getOutputStream()) {		//get an outputstream from response
			response.setContentType("image/png");					//specify the type of message that will be sent to the client
			ChartUtils.writeChartAsPNG(out, pieChart, 500, 350); 	//write the chart into outputstream
		}catch(IOException e) {		//outputstream will be closed automatic
			e.printStackTrace();
		}
	}
	
	/**
	 * get the data source which will be charted.
	 */
	private HashMap<String, Double> getDatasource(){
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
