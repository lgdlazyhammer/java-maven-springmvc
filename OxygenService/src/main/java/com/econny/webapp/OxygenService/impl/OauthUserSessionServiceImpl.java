package com.econny.webapp.OxygenService.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.econny.webapp.OxygenEntity.OauthUserSessionEntity;
import com.econny.webapp.OxygenService.inter.OauthUserSessionService;

@Repository("oauthUserSessionServiceImpl")
public class OauthUserSessionServiceImpl implements OauthUserSessionService{

	public void save(OauthUserSessionEntity user) {
		// TODO Auto-generated method stub
		
	}

	public OauthUserSessionEntity read(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(String uid) {
		// TODO Auto-generated method stub
		
	}

	/*@Autowired
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
					userSaved = objectMapper.readValue(userInfoStr, OauthUserSessionEntity.class);
					OauthUserSessionEntity user = new OauthUserSessionEntity();
					user.setId(id);
					
					return user;
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
	}*/
}
