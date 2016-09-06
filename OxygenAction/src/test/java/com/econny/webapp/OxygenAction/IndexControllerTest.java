package com.econny.webapp.OxygenAction;

import static org.junit.Assert.*;

import java.io.Console;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import sun.misc.BASE64Encoder;

public class IndexControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIndex() {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] data = new String("").getBytes();
			md5.update(data);
			String token = (new BASE64Encoder()).encodeBuffer(md5.digest());
			System.out.println(token);
			Assert.notNull(token);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//fail("Not yet implemented");
	}

}
