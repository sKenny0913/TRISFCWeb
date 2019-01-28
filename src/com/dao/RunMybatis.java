package com.dao;

import org.apache.commons.lang.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RunMybatis {

	public static void main(String[] args) {
		Dongle();
	}

	public static void Dongle() {
		
		String str = "Dongle*MMO0-180500001*NAAA01FK*A01000028NB*01000028*1,perpetual*2,perpetual*3,perpetual*4,perpetual*16,perpetual*17,perpetual*32,perpetual*33,perpetual*34,perpetual*48,perpetual*64,perpetual*88,perpetual*89,perpetual*96,perpetual*97,perpetual*136,perpetual*137,perpetual*138,perpetual~";
		String[] str1 = str.split("\\*");
//		System.out.println(str1);
//		System.out.println(str1[0]);
//		System.out.println(str1[1]);
//		System.out.println(str1[2]);
//		System.out.println(str1[3]);
		for (String stra : str1) {
			System.out.println(stra);
			if(stra.contains(",")) {
//			System.out.println(org.apache.commons.lang.StringUtils.substringBefore(stra,","));
//			System.out.println(org.apache.commons.lang.StringUtils.substringAfter(stra,","));
			}
		}
			
//		String strN1 = "11";
//		String strN2 = "22";
//		String strN3 = "33";
//		String strN4 = "44";
//		String strN5 = "55";
//		String strN6 = "66";
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		Date date = new Date();
//		String strN8 = "88";
//		MyBatisDAO Dongle_DAO = new MyBatisDAO();
//		// Village Dongle_ = new Village();
//
//		Map<String, Object> params = new HashMap<String, Object>();
//		
//		params.put("WO", strN1);
//		params.put("SN", strN2);
//		params.put("PC_NAME", strN3);
//		params.put("OP_ID", strN4);
//		params.put("FEATURE_ID", strN5);
//		params.put("WARRANTY", strN6);
//		params.put("LOGDATE", dateFormat.format(date).toString());
//		params.put("UPLOADTIMES", strN8);

//		List<Dongle_POJO> listDongle = Dongle_DAO.getSelectList(params);
//		for (Dongle_POJO Dongle : listDongle) {
//			System.out.println(Dongle.getWO());
//			System.out.println(Dongle.getSN());
//			System.out.println(Dongle.getPC_NAME());
//			System.out.println(Dongle.getOP_ID());
//			System.out.println(Dongle.getFEATURE_ID());
//			System.out.println(Dongle.getWARRANTY());
//			System.out.println(Dongle.getLOGDATE());
//			System.out.println(Dongle.getUPLOADTIMES());
//		}
		
//		Dongle_DAO.insertDongle(params);
	}
}