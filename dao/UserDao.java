package dao;

import java.util.List;
import java.util.Set;

import entity.User;

public interface UserDao {
	public User getUser(String account, String password, String userType);

	public Set<String> getAllUsersAccount(String userType);
	
	public List<User> getAllUsers(String userType);

	public void addUser(String account, String username, String password, String userType);

	public void updatePassword(int userId, String password, String userType);

	public void deleteUser(int userId, String userType);
}
