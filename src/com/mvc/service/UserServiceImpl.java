package com.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.DAO.IUserDao;
@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserDao userDao;
	public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public boolean traInsert(String usr) throws Exception  {
		int result = userDao.insert();
		int result2 = userDao.insert();
		if("abc".equals(usr))
		{
			throw new Exception("rollback");
		}
		return false;
	}

}
