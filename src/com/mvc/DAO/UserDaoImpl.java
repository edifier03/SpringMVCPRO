package com.mvc.DAO;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository 
public class UserDaoImpl extends SqlSessionDaoSupport implements IUserDao
{
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	@Override
	public int insert() throws Exception {
		 this.getSqlSession().insert("com.mvc.damain.User.doInsert", "1231");

		 return 0;
	}

}
