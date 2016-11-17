package com.econny.webapp.OxygenService.impl;

import java.io.IOException;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.econny.webapp.OxygenEntity.OauthUserSessionEntity;
import com.econny.webapp.OxygenService.inter.OauthUserSessionService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OauthUserSessionServiceImpl implements OauthUserSessionService{

	/*public void save(OauthUserSessionEntity user) {
		// TODO Auto-generated method stub
		
	}

	public OauthUserSessionEntity read(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(String uid) {
		// TODO Auto-generated method stub
		
	}*/

	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	public void save(final OauthUserSessionEntity user) {
		// TODO Auto-generated method stub
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(
						redisTemplate.getStringSerializer().serialize(
								"user.id." + user.getId()),
						redisTemplate.getStringSerializer().serialize(
								user.toString()));
				return null;
			}
		});
	}

	public OauthUserSessionEntity read(final String id) {
		// TODO Auto-generated method stub
		return redisTemplate.execute(new RedisCallback<OauthUserSessionEntity>() {
			public OauthUserSessionEntity doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(
						"user.id." + id);
				if (connection.exists(key)) {
					byte[] value = connection.get(key);
					String userInfoStr = redisTemplate.getStringSerializer()
							.deserialize(value);
					OauthUserSessionEntity userSaved = new OauthUserSessionEntity();
					ObjectMapper mapper = new ObjectMapper();
					try {
						userSaved = mapper.readValue(userInfoStr, OauthUserSessionEntity.class);
					} catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return userSaved;
				}
				return null;
			}
		});
	}

	public void delete(final String id) {
		// TODO Auto-generated method stub
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) {
				connection.del(redisTemplate.getStringSerializer().serialize(
						"user.id." + id));
				return null;
			}
		});
	}
}
