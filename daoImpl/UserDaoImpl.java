package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.BaseDao;
import dao.UserDao;
import entity.User;

public class UserDaoImpl extends BaseDao implements UserDao {
	public User getUser(String account, String password, String userType) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = getConn();
			String sql = "SELECT * FROM test." + userType + " WHERE account = ? AND password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setAccount(rs.getString("account"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return user;
	}

	public void addUser(String account, String username, String password, String userType) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConn();
			String sql = "INSERT INTO test." + userType + " (account,username, password) VALUES (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, username);
			pstmt.setString(3, password);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
	}

	@Override
	public void updatePassword(int userId, String password, String userType) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConn();
			String sql = "UPDATE test." + userType + " Set password=? WHERE userId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setInt(2, userId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}

	}

	@Override
	public void deleteUser(int userId, String userType) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConn();
			String sql = "DELETE test." + userType + " WHERE userId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}

	}

	@Override
	public Set<String> getAllUsersAccount(String userType) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Set<String> accounts = new HashSet<>();
		try {
			conn = getConn();
			String sql = "SELECT * FROM test." + userType;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String account=rs.getString("account");
				accounts.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return accounts;
	}

	@Override
	public List<User> getAllUsers(String userType) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try {
			conn = getConn();
			String sql = "SELECT * FROM test." + userType;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user=new User();
				user.setUserId(rs.getInt("userId"));
				user.setAccount(rs.getString("account"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return users;
	}
}
