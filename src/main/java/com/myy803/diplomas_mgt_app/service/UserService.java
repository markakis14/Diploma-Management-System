package com.myy803.diplomas_mgt_app.service;

import com.myy803.diplomas_mgt_app.model.User;

public interface UserService {
	public void saveUser(User user);
    public boolean isUserPresent(User user);
    public int getCurrentUserId();
}
