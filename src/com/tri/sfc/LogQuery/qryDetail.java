package com.tri.sfc.LogQuery;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class SelectToJSON
 */
@WebServlet("/qryDetail")
public class qryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public qryDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// Website you wish to allow to connect
		response.setHeader("Access-Control-Allow-Origin", "*");
		// Request methods you wish to allow
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		// Request headers you wish to allow
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		String strSerialNumber = request.getParameter("SerialNumber");
		String strUploadtimes = request.getParameter("Uploadtimes");
		String strStation = request.getParameter("Station");

		ResourceBundle config = ResourceBundle.getBundle("Config");
		String strDBurl = config.getString("dburl");
		String strDBusername = config.getString("dbusername");
		String strDBpassword = config.getString("dbpassword");
		String strSQL = config.getString("SQLforDetails");
		String strSQL1 = config.getString("SQLforDetails1");
		String strSQL2 = config.getString("SQLforDetails2");
		String strSQLforTable = config.getString("SQLforTable");
		String strDataTableName = null;
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/html");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(strDBurl, strDBusername, strDBpassword);
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			String strSQLexecute1 = strSQLforTable + "'" + strStation + "'";
			ResultSet rs2 = stmt.executeQuery(strSQLexecute1);
			if (rs2.next()) {
				strDataTableName = rs2.getString(1);
			} else {
				// TODO no table name
			}

			String strSQLexecute = strSQL + " " + strDataTableName + " " + strSQL2 + "'" + strSerialNumber + "' "
					+ strSQL1 + strUploadtimes + " order by keyname asc, valueindex asc";
			ResultSet rs = null;
			// response.getWriter().write(strSQLexecute);

			rs = stmt.executeQuery(strSQLexecute);
			String strKey = null;
			JSONArray jArray = new JSONArray();
			JSONArray jSubArray = new JSONArray();

			while (rs.next()) {
//				response.getWriter().write(rs.getString(4)+"\n");
				
				if(rs.isLast()) {
					JSONObject jObjNew = new JSONObject();
					jObjNew.put("key", rs.getString(4));
					jObjNew.put("value", rs.getString(5));
					jArray.put(jObjNew);
//					response.getWriter().write(jObjNew.toString());
				}
				
				if (rs.next()) {// check if next key of data is the same as current, if yes, put them to a array
								// and add to a value object
					strKey = rs.getString(4);
					
					rs.previous();
					if (strKey.equals(rs.getString(4))) { // same key in next row, put value to same sub JSON array
						jSubArray.put(rs.getString(5));
					} else if (!strKey.equals(rs.getString(4)) && jSubArray.length() > 0 ) { // not same key in next row and sub JSON array has value, put sub JSON array to JSON object, clean sub JSON array   
//						response.getWriter().write(rs.getString(4));
//						response.getWriter().write(jSubArray.length() +"\n");
							jSubArray.put(rs.getString(5));
//							response.getWriter().write(jSubArray.length() +"\n");
							if(jSubArray.length() != 2) { 
							JSONObject jObjNew = new JSONObject();
							jObjNew.put("key", rs.getString(4));
							jObjNew.put("value", jSubArray);
							jArray.put(jObjNew);
//							response.getWriter().write(jArray.toString());
							jSubArray = new JSONArray();
							
							}else if(jSubArray.length() == 2) { // if only 2 value in a key, remove 2nd value as John.Huang request
								jSubArray.remove(1);
								JSONObject jObjNew = new JSONObject();
								jObjNew.put("key", rs.getString(4));
								jObjNew.put("value", jSubArray);
								jArray.put(jObjNew);
	//							response.getWriter().write(jArray.toString());
								jSubArray = new JSONArray();
							}
						
					} else if (!strKey.equals(rs.getString(4)) && jSubArray.length() == 0) { // not same key and  no value in sub JSON array
						JSONObject jObjNew = new JSONObject();
						jObjNew.put("key", rs.getString(4));
						jObjNew.put("value", rs.getString(5));
						jArray.put(jObjNew);
//						response.getWriter().write(jObjNew.toString());
					}
				}
				
			}
			
			JSONObject jObjDevice = new JSONObject();
			jObjDevice.put("details", jArray);
			response.setContentType("application/json");
			response.getWriter().write(jObjDevice.toString());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
