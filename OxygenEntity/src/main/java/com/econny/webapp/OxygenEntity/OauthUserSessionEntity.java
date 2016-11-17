package com.econny.webapp.OxygenEntity;

import java.io.Serializable;
import java.util.Date;

public class OauthUserSessionEntity implements Serializable {

	private static final long serialVersionUID = 2668307865623183776L;
	private String id;
	private OauthUserEntity user;
	private Date expires;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OauthUserEntity getUser() {
		return user;
	}

	public void setUser(OauthUserEntity user) {
		this.user = user;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OauthUserSessionEntity other = (OauthUserSessionEntity) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "{\"id\":"+id+",\"user\":{\"id\":\""+user.getId()+"\",\"name\":\""+user.getName()+"\",\"password\":\""+user.getPassword()+"\"},\"expires\":\""+expires+"\"}";
	}
}
