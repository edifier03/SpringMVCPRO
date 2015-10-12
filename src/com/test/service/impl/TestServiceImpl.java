package com.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.ITestDao;
import com.test.service.ITestService;
@Service
public class TestServiceImpl implements ITestService {
	@Autowired
	private ITestDao testDao;

	public ITestDao getTestDao() {
		return testDao;
	}

	public void setTestDao(ITestDao testDao) {
		this.testDao = testDao;
	}

	@Override
	public void getList(String param) {
		testDao.getList(param);
	}
}
