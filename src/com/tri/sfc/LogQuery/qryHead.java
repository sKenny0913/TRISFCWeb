package com.tri.sfc.LogQuery;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dao.MyBatisDAO;
import com.dao.qryHead_POJO;

/**
 * Servlet implementation class SelectToJSON
 */
@WebServlet("/qryHead")
public class qryHead extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public qryHead() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Website you wish to allow to connect
		response.setHeader("Access-Control-Allow-Origin", "*");
		// Request methods you wish to allow
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		// Request headers you wish to allow
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		String strStation = request.getParameter("Station");
		String strSerialNumber = request.getParameter("SerialNumber");
		String sColumn_Name = request.getParameter("Column_Name");
		String sColumn_Value = request.getParameter("Column_Value");
		String strFDay = request.getParameter("fDay");
		String strTDay = request.getParameter("tDay");
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {
			
			//TODO if serialnumber is null and column name is not null, then qry by column name to get serialNumber, then qry serialNumber
			
			MyBatisDAO MyBatisDAO = new MyBatisDAO();
			
//			response.getWriter().write(strStation);
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("StationName", strStation);
			
			qryHead_POJO qryHead_POJO_table = MyBatisDAO.getTableName(params);
			System.out.println(qryHead_POJO_table.getDataTableName());
			
//			response.getWriter().write(qryHead_POJO.getDataTableName());
			
			params.put("DataTableName", qryHead_POJO_table.getDataTableName());
			params.put("KEYNAME", sColumn_Name);
			params.put("VALUE", sColumn_Value);
			params.put("fDay", strFDay);
			params.put("tDay", strTDay);
			
			if(strSerialNumber == null || strSerialNumber.equals("") && 
					sColumn_Name != null || !sColumn_Name.equals("") && 
					sColumn_Value != null || !sColumn_Value.equals("")) {
				qryHead_POJO qryHead_POJO_SN = MyBatisDAO.getSerialNumber(params);
				qryHead_POJO_SN.getSerialNumber();
				params.put("SerialNumber", qryHead_POJO_SN.getSerialNumber());
			}else {
				params.put("SerialNumber", strSerialNumber);	
			}
			
			
//			response.getWriter().write(params.toString());
			
			
//			response.getWriter().write(params.toString());
			
			List<qryHead_POJO> listqryHead_POJO = MyBatisDAO.getqryHead_POJOSelectListMap(params);
			
			JSONArray jArray = new JSONArray();
			
			for (qryHead_POJO Head : listqryHead_POJO) {

				JSONObject jObjNew = new JSONObject();
				jObjNew.put("Station", Head.getStationName());
				jObjNew.put("SerialNumber", Head.getSerialNumber());
				jObjNew.put("VALUE", Head.getVALUE());
				jObjNew.put("date", Head.getLogDate());
				jObjNew.put("uploadtimes", Head.getUploadTimes());
				jArray.put(jObjNew);

			}
//			

//			response.setContentType("text/html");
			JSONObject jObjDevice = new JSONObject();
			jObjDevice.put("stations", jArray);
			response.setContentType("application/json");
			response.getWriter().write(jObjDevice.toString());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
