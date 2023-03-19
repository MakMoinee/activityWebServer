package com.sample.webserver.interfaces;

import java.util.List;

import com.sample.webserver.models.Users;

public interface DBOperationsListener {
	void onError(Exception e);
	
	default void onSuccess() {

	}

	default void onSuccess(Users users) {

	}
	
	default void onSuccess(List<Users> users) {

	}

	default void onSuccess(String response) {

	}

}
