package com.tadpole.service.impl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadpole.entity.User;
import com.tadpole.repository.UserRepository;
import com.tadpole.service.UserService;
import com.tadpole.util.SystemUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findUserByName(String name) {

		return userRepository.findByName(name);
	}

	public Set<String> findAllAccounts() {

		List<User> users = userRepository.findAll();
		Set<String> userAccounts = new TreeSet<String>();
		for (User user : users) {
			userAccounts.add(user.getName());
		}
		return userAccounts;
	}

	public User findUser(final String username, final String password) {

		List<User> users = userRepository.findByUsernameAndPassword(username, password);

		if (users.isEmpty()) {
			return null;
		}

		if (users.size() != 1) {

		}
		return users.get(0);
	}

	public User getCurrentLoggedInUser() {

		String username = SystemUtils.getCurrentUserName();

		return userRepository.findByUsername(username);

	}
}
