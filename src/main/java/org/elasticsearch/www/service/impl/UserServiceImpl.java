package org.elasticsearch.www.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import org.elasticsearch.www.dao.UserDAO;
import org.elasticsearch.www.domain.User;
import org.elasticsearch.www.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDAO userDAO;
	public List<User> getUserInfo() {
		System.out.println("Here?");
		return userDAO.getUserInfo();
	}

	public void putUserInfo() {
		List<User> userList = getUserInfo();

		Settings settings = ImmutableSettings.settingsBuilder()
				.put("cluster.name", "phoenixes").build();

		// on startup
		Client client = new TransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress("10.135.16.65", 9300));

		BulkRequestBuilder bulkRequest = client.prepareBulk();
		for (User user : userList){
			try {
				bulkRequest.add(client.prepareIndex("hac", "user")
								.setSource(jsonBuilder()
												.startObject()
												.field("username", user.getUsername())
												.field("email", user.getEmail())
												.field("phone", user.getPhone())
												.field("groupName", user.getGroupName())
												.field("privilegeName", user.getPrivilegeName())
												.field("description", user.getDescription())
												.endObject()
								)
				);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		if (bulkResponse.hasFailures()) {
			// process failures by iterating through each bulk response item
			System.out.println("There are errors");
		}

		// on shutdown
		client.close();

	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
