package com.test.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.test.dao.ITestDao;
@Repository
public class TestDaoImpl extends SqlSessionDaoSupport implements ITestDao {
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public void getList(String param) {
		 this.getSqlSession().insert("com.mvc.damain.User.doInsert", "1231");
	}
}
