package entity;

public class User {
	private int userId; // 用户编号
	private String account; // 账号
	private String username; // 用户名
	private String password; // 密码

	public User() {

	}

	public User(int userId, String account, String username, String password) {
		this.userId = userId;
		this.account = account;
		this.username = username;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}