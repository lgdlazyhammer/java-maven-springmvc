package com.econny.webapp.OxygenService.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.econny.webapp.OxygenDao.mybatis.mapper.UserMapper;
import com.econny.webapp.OxygenEntity.UserEntity;
import com.econny.webapp.OxygenService.inter.UserService;

public class UserServiceImpl implements UserService {

	public UserEntity getUserById() {

		String resource = "com/econny/webapp/OxygenDao/mybatis/mapper/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			SqlSession session = sqlSessionFactory.openSession();
			UserMapper mapper = session.getMapper(UserMapper.class);
			UserEntity user = mapper.selectUser(1);
			System.out.println("select user: " + user.toString());
			return user;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
