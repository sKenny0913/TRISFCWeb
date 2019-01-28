package com.tri.sfc.Dongle;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import com.dao.Dongle_POJO;
import com.dao.MyBatisDAO;
import com.dao.MyBatisUtil;
import com.tri.exception.WOnotFound;
import com.tri.exception.WOnotMatch;

public class ServerThreadCode extends Thread {
	private ServerSocket m_serverSocket;// 伺服器端的Socket，接收Client端的連線
	private Socket m_socket;// Server和Client之間的連線通道
	private ResourceBundle config = ResourceBundle.getBundle("Config");
	private HashMap<String,List<String>> mapSave = new HashMap<String, List<String>>();
	private SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
	private SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");// 設定時間格式
	public ServerThreadCode(int port) {
		try {
			m_serverSocket = new ServerSocket(port);// 建立伺服器端的Socket，並且設定Port
		} catch (IOException e) {
			System.out.println(e.getMessage());// 出現例外時，捕捉並顯示例外訊息
		}
	}

	@Override
	public void run()// 覆寫Thread內的run()方法
	{
		try {
			while (true) {
				System.out.println("等待連線......");
				m_socket = m_serverSocket.accept();// 等待伺服器端的連線，若未連線則程式一直停在這裡
				try {
					System.out.println("連線成功！" + m_socket);
					// m_serverSocket.close();// 一旦連線建立成功，且不需要再接收其他連線，則可關閉ServerSocket

					// 送出端的編寫必須和接收端的接收Class相同
					// 使用Socket的getInputStream()和getOutputStream()進行接收和發送資料
					// 想要寫入字串可以用 PrintStream；想要把各種基本資料型態，如 int, double...等的 "值" 輸出，可以用
					// DataOutputStream；想要把整個物件 Serialize，則可以用 ObjectOutputStream。
					PrintStream writer;// 在此我使用PrintStream將字串進行編寫和送出
//					BufferedReader reader;// 在此我使用BufferedReader將資料進行接收和讀取
//					BufferedReader reader;// 在此我使用BufferedReader將資料進行接收和讀取
					writer = new PrintStream(m_socket.getOutputStream());// 由於是將資料編寫並送出，所以是Output
					Scanner reader = new Scanner(m_socket.getInputStream()).useDelimiter("~"); // cuz Victor not willing to add line feed in his program. so change to use scanner instead bufferedeader
					// BufferedReader在建構時接受一個Reader物件，在讀取標準輸入串流時，會使用InputStreamReader，它繼承了Reader類別
//					reader = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));// 接收傳進來的資料，所以是Input
					
					// System.out.println("Client: " + reader.readLine());//讀取一行字串資料
					
					while(reader.hasNext()) { // check client is still connected
						String nowDate = sdFormat.format(new Date());// 取得現在時間
//						GetERPWO("111");
//						System.out.println(reader.next());
//						String[] saReader = reader.readLine().split("\\*");
						String[] saReader = reader.next().split("\\*");
//						System.out.println("111");
						try {
						InsertData(saReader); // insert to SFC DB
						writer.println("OK. SN: " + saReader[2] + " ,Time: " + nowDate);// 將資料編寫進串流內
						writer.flush();// 清空緩衝區並送出資料
						}catch (WOnotMatch e) {
							writer.println("NG. SN: " + saReader[2] + " ,Time: " + nowDate + ", " + e);// 將資料編寫進串流內
							writer.flush();// 清空緩衝區並送出資料
						} catch (WOnotFound e) {
							writer.println("NG. SN: " + saReader[2] + " ,Time: " + nowDate + ", " + e);// 將資料編寫進串流內
							writer.flush();// 清空緩衝區並送出資料
						}
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());// 出現例外時，捕捉並顯示例外訊息(連線成功不會出現例外)
					m_socket.close();// 關閉連線
					m_serverSocket.close();
				}
			}
			

		} catch (IOException e) {
			System.out.println(e.getMessage());// 出現例外時，捕捉並顯示例外訊息(連線成功不會出現例外)
		} finally {
			try {
				session.close();
				m_serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());// 出現例外時，捕捉並顯示例外訊息(連線成功不會出現例外)
			}
		}
	}

	private void GetERPWO(String sWO) throws WOnotFound {
		String strDBurl = config.getString("dburlERP");
		String strDBusername = config.getString("dbusernameERP");
		String strDBpassword = config.getString("dbpasswordERP");
		String strSQL = config.getString("SQLforDongleWO");
		String strSQL1 = config.getString("SQLforDongleWO1");
		Pattern pattern = Pattern.compile("-0*(.*)-");
		
		String strSQLexecute = strSQL + " " + strSQL1 + " '" + sWO + "'";
		// response.getWriter().write(strSQLexecute);
		ResultSet rs = null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection(strDBurl, strDBusername, strDBpassword);
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(strSQLexecute);
		
			List<String> list = new ArrayList<String>();
			String key = null; 
			while (rs.next()) {
				String s = rs.getString(2).trim();
				Matcher matcher = pattern.matcher(s);
				if (matcher.find())
				{
//				    System.out.println(matcher.group(1));
				    list.add(matcher.group(1));
				}
				key = rs.getString(1).trim();
			}
			if(key == null) {
//				System.out.println("cannot find WO in ERP");
				throw new WOnotFound("Cannot find WO in ERP");
			}
			mapSave.put(key, list);
			System.out.println("ERP WO: " + mapSave);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	
	private void InsertData(String[] saReader) throws WOnotMatch, WOnotFound {
		String sWO = saReader[1];
		String sSN = saReader[2];
		String sPC_NAME = saReader[3];
		String sOP_ID = saReader[4];
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String sLOGDATE = dateFormat.format(date).toString();
		Integer iUPLOADTIMES = null;

		GetERPWO(sWO);
		
		MyBatisDAO Dongle_DAO = new MyBatisDAO();

		List<Dongle_POJO> listUT = Dongle_DAO.getUplodTimes(sSN);
		if (listUT.isEmpty()) {
			iUPLOADTIMES = 1;
		} else {
			for (Dongle_POJO Dongle : listUT) {
				iUPLOADTIMES = Dongle.getUPLOADTIMES() + 1;
			}
		}

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("WO", sWO);
		params.put("SN", sSN);
		params.put("PC_NAME", sPC_NAME);
		params.put("OP_ID", sOP_ID);
		params.put("LOGDATE", sLOGDATE);
		params.put("UPLOADTIMES", iUPLOADTIMES);
		for (String sa : saReader) {
			if (sa.contains(",")) {
				String sFeature_id = org.apache.commons.lang.StringUtils.substringBefore(sa, ",");
				String sSubString = org.apache.commons.lang.StringUtils.substringAfter(sa, ",");
				String sWarranty = org.apache.commons.lang.StringUtils.substringBefore(sSubString, ",");
				String sWarranty_date = org.apache.commons.lang.StringUtils.substringAfter(sSubString, ",");
				params.put("FEATURE_ID", sFeature_id);
				params.put("WARRANTY", sWarranty);
			    params.put("WARRANTY_DATE", sWarranty_date);
			    if(!mapSave.get(sWO).contains(sFeature_id)) {
//			    	System.out.println("Feature ID does not matach: " +sWO + " " +sFeature_id);
			    	session.rollback();
			    	throw new WOnotMatch("Feature ID does not matach. WO: " +sWO + ", Feature ID: " +sFeature_id);
			    }else{
			    	Dongle_DAO.insertDongle(params, session);
			    }
//				System.out.println(params);
			}
		}
		session.commit();
//		session.close();
	}
}