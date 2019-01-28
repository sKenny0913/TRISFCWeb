package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


public class MyBatisDAO {
	public void insertDongle(Map<String, Object> map, SqlSession session) {
//		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("com.dao.DongleMapper.insertDongle" , map);
//		session.commit();
//		session.close();
	}
	public List<Dongle_POJO> getSelectList(Map<String, Object> map) {
		  SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		  List<Dongle_POJO> listDongle = session.selectList("com.dao.DongleMapper.selectDongle" , map);
		  session.close();
		  return listDongle;
		}
	public List<Dongle_POJO> getUplodTimes(String SN) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Dongle_POJO> listUT = session.selectList("com.dao.DongleMapper.selectUploadTimes" , SN);
		session.close();
		return listUT;
	}
	public List<qryHead_POJO> getqryHead_POJOSelectListMap(Map<String, Object> map) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<qryHead_POJO> list = session.selectList("com.dao.qryHeadMapper.selectqryHead" , map);
		session.close();
		return list;
	}
	public qryHead_POJO getTableName(Map<String, Object> map) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		qryHead_POJO qryHead_POJO = session.selectOne("com.dao.qryHeadMapper.selectTableName" , map);
		session.close();
		return qryHead_POJO;
	}
	public qryHead_POJO getSerialNumber(Map<String, Object> map) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		qryHead_POJO qryHead_POJO = session.selectOne("com.dao.qryHeadMapper.selectqryHeadbyColumnName" , map);
		session.close();
		return qryHead_POJO;
	}
}
