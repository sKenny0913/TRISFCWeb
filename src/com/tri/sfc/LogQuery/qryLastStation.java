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
@WebServlet("/qryLastStation")
public class qryLastStation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public qryLastStation() {
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
//		String strStation = request.getParameter("Station");
		String strSerialNumber = request.getParameter("SerialNumber");
//		String strFDay = request.getParameter("fDay");
//		String strTDay = request.getParameter("tDay");
		ResourceBundle config = ResourceBundle.getBundle("Config");
		String strDBurl = config.getString("dburl");
		String strDBusername = config.getString("dbusername");
		String strDBpassword = config.getString("dbpassword");
//		String strSQLforTable = config.getString("SQLforTable");
		String strSQLforLastStation = config.getString("SQLforLastStation");
		String strSQLforLastStation1 = config.getString("SQLforLastStation1");
		String strSQLforLastStation2 = config.getString("SQLforLastStation2");
		String strSQLforLastStation3 = config.getString("SQLforLastStation3");
		String strTable1 = config.getString("Table1");
		String strTable2 = config.getString("Table2");
		String strTable3 = config.getString("Table3");
		
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

//		response.setContentType("text/html");
		

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(strDBurl, strDBusername, strDBpassword);
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String strSQLexecute = strSQLforLastStation + " " + strTable3 + " " + strSQLforLastStation1 + " '%" + strSerialNumber + "%'";
//			response.getWriter().write(strSQLexecute);
			ResultSet rs2 = stmt.executeQuery(strSQLexecute);
			JSONArray jArray = new JSONArray();
			if (rs2.next()) {
				if(!(rs2.getString(1)).equals("0")) {
					String strSQLexecute2 = strSQLforLastStation3 + " " + strTable3 + " " + strSQLforLastStation2 + " " + strTable3 + ")" + " and serialnumber like " + " '%" + strSerialNumber + "%'";
//					response.getWriter().write(strSQLexecute2);
					ResultSet rs = stmt.executeQuery(strSQLexecute2);
					
//					response.getWriter().write("Test");
					while (rs.next()) {
//						response.getWriter().write("Test2");
						JSONObject jObjNew = new JSONObject();
						jObjNew.put("serialNumber", rs.getString(3));
						jObjNew.put("station", rs.getString(2));
						jObjNew.put("result", rs.getString(5));
						jObjNew.put("date", rs.getTimestamp(7));
						jObjNew.put("uploadtimes", rs.getString(8));
						jArray.put(jObjNew);
					}

//					JSONObject jObjDevice = new JSONObject();
//					jObjDevice.put("stations", jArray);
//					response.setContentType("application/json");
//					response.getWriter().write(jObjDevice.toString());
					
					
				}else {
					strSQLexecute = strSQLforLastStation + " " + strTable2 + " " + strSQLforLastStation1 + " '%" + strSerialNumber + "%'";
					rs2 = stmt.executeQuery(strSQLexecute);
//					response.getWriter().write(strSQLexecute);
					if (rs2.next()) {
						if(!(rs2.getString(1)).equals("0")) {
							
							String strSQLexecute2 = strSQLforLastStation3 + " " + strTable2 + " " + strSQLforLastStation2 + " " + strTable2 + ")" + " and serialnumber like " + " '%" + strSerialNumber + "%'";
//							response.getWriter().write(strSQLexecute2);
							ResultSet rs = stmt.executeQuery(strSQLexecute2);
							while (rs.next()) {
								JSONObject jObjNew = new JSONObject();
								jObjNew.put("serialNumber", rs.getString(3));
								jObjNew.put("station", rs.getString(2));
									jObjNew.put("result", rs.getString(5));
									jObjNew.put("date", rs.getTimestamp(7));
									jObjNew.put("uploadtimes", rs.getString(8));
									jArray.put(jObjNew);
							}

//							JSONObject jObjDevice = new JSONObject();
//							jObjDevice.put("stations", jArray);
//							response.setContentType("application/json");
//							response.getWriter().write(jObjDevice.toString());
							
						}else {
							strSQLexecute = strSQLforLastStation + " " + strTable1 + " " + strSQLforLastStation1 + " '%" + strSerialNumber + "%'";
							rs2 = stmt.executeQuery(strSQLexecute);
//							response.getWriter().write(strSQLexecute);
							if (rs2.next()) {
								if(!(rs2.getString(1)).equals("0")) {
									
									String strSQLexecute2 = strSQLforLastStation3 + " " + strTable1 + " " + strSQLforLastStation2 + " " + strTable1 + ")" + " and serialnumber like " + " '%" + strSerialNumber + "%'";
//									response.getWriter().write(strSQLexecute2);
									ResultSet rs = stmt.executeQuery(strSQLexecute2);
									while (rs.next()) {
										JSONObject jObjNew = new JSONObject();
										jObjNew.put("serialNumber", rs.getString(3));
										jObjNew.put("station", rs.getString(2));
											jObjNew.put("result", rs.getString(5));
											jObjNew.put("date", rs.getTimestamp(7));
											jObjNew.put("uploadtimes", rs.getString(8));
											jArray.put(jObjNew);
									}

								}
							}
						}
					}
				}
			}
			JSONObject jObjDevice = new JSONObject();
			jObjDevice.put("stations", jArray);
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
