package com.namnd.rest.webservices.restfullwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();

	static {
		users.add(new User(1, "Dang Nam", LocalDate.now().minusYears(30)));
		users.add(new User(2, "Nhung Anh", LocalDate.now().minusYears(25)));
		users.add(new User(3, "Van Pu", LocalDate.now().minusYears(20)));
	}

	public List<User> findAll() {
		return users;
	}

	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public User save(User user) {
		user.setId(users.size() + 1);
		users.add(user);
		return user;
	}

	public void deleteUser(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}

}
