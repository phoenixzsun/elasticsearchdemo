package org.elasticsearch.www.service;

import java.util.List;

import org.elasticsearch.www.domain.User;

public interface UserService {
	public List<User> getUserInfo();
	public void putUserInfo();
}
