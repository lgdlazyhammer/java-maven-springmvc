package com.econny.webapp.OxygenService.inter;

import com.econny.webapp.OxygenEntity.OauthUserSessionEntity;

public interface OauthUserSessionService {
	/**
	 * @param
	 */
	public void save(OauthUserSessionEntity user);
	/**
	 * @param
	 * @return
	 */
	public OauthUserSessionEntity read(String uid);
	/**
	 * @param
	 */
	public void delete(String uid);
}
