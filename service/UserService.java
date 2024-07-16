package service;

import java.util.List;
import java.util.Set;

import entity.User;

public interface UserService {
	public User getUser(String account, String password, String userType) throws Exception;

	public Set<String> getAllUsersAccount(String userType);

	public void addUser(String account, String username, String password, String userType) throws Exception;

	public void updatePassword(int userId, String password, String userType) throws Exception;

	public void deleteUser(int userId, String userType);

	public List<User> getAllUsers();
}
