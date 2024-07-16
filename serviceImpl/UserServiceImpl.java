package serviceImpl;

import entity.User;

import java.util.List;
import java.util.Set;

import dao.UserDao;
import daoImpl.UserDaoImpl;
import service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userdao = new UserDaoImpl();

	public User getUser(String account, String password, String userType) throws Exception {
		Set<String> accounts=userdao.getAllUsersAccount(userType);
		
		if(account.isEmpty()||password.isEmpty()) {
			throw new Exception("登录失败，账号或密码不能为空");
		}
		if(!accounts.contains(account)) {
			throw new Exception("登录失败，该账号不存在，请先完成注册！");
		}
		User user=userdao.getUser(account, password, userType);
		if(user==null)
			throw new Exception("登录失败，密码错误！");
		return user;
	}

	public void addUser(String account, String username, String password, String userType) throws Exception {
		if(username.isEmpty()||password.isEmpty()) {
			throw new Exception("注册失败，用户姓名或密码不能为空");
		}
		if (password.length() < 3 || password.length() > 20) {
			throw new Exception("注册失败，密码长度必须在3到20个字符之间。");
		}
		userdao.addUser(account, username, password, userType);
	}

	public void updatePassword(int userId, String password, String userType) throws Exception {
		if(password.isEmpty()) {
			throw new Exception("更改失败，密码不能为空");
		}
		if (password.length() < 3 || password.length() > 20) {
			throw new Exception("更改失败，密码长度必须在3到20个字符之间。");
		}
		userdao.updatePassword(userId, password, userType);
	}

	public void deleteUser(int userId, String userType) {
		userdao.deleteUser(userId, userType);
		System.out.println("注销成功，已退出登录！");
	}

	@Override
	public Set<String> getAllUsersAccount(String userType) {
		return userdao.getAllUsersAccount(userType);
	}

	@Override
	public List<User> getAllUsers() {
		return userdao.getAllUsers("regular_user");
	}

}
