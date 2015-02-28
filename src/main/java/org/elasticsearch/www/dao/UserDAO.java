package org.elasticsearch.www.dao;

import java.util.List;
import org.elasticsearch.www.domain.User;

public interface UserDAO {
	public List<User> getUserInfo();
}
