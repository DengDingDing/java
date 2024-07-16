package daoImpl;

import dao.BaseDao;
import dao.HouseDao;
import entity.House;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HouseDaoImpl extends BaseDao implements HouseDao {

	public void addHouse(House house) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConn();
			String sql = "INSERT INTO test.house (estateId, buildingNo, houseNo, area, unitPrice, totalPrice, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, house.getEstateId());
			pstmt.setInt(2, house.getBuildingNo());
			pstmt.setInt(3, house.getHouseNo());
			pstmt.setDouble(4, house.getArea());
			pstmt.setDouble(5, house.getUnitPrice());
			pstmt.setDouble(6, house.getTotalPrice());
			pstmt.setString(7, house.getStatus());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				house.setHouseId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
	}

	public List<House> getAllHouses() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<House> houses = new ArrayList<>();
		try {
			conn = getConn();
			String sql = "SELECT * FROM test.house";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				House house = new House();
				house.setHouseId(rs.getInt("houseId"));
				house.setEstateId(rs.getInt("estateId"));
				house.setBuildingNo(rs.getInt("buildingNo"));
				house.setHouseNo(rs.getInt("houseNo"));
				house.setArea(rs.getDouble("area"));
				house.setUnitPrice(rs.getDouble("unitPrice"));
				house.setTotalPrice(rs.getDouble("totalPrice"));
				house.setStatus(rs.getString("status"));
				houses.add(house);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return houses;
	}

	public List<House> getHousesByUserId(int userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<House> houses = new ArrayList<>();
		try {
			conn = getConn();
			String sql = "SELECT h.* FROM test.house h JOIN test.bill b ON h.houseId = b.houseId WHERE b.userId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				House house = new House();
				house.setHouseId(rs.getInt("houseId"));
				house.setEstateId(rs.getInt("estateId"));
				house.setBuildingNo(rs.getInt("buildingNo"));
				house.setHouseNo(rs.getInt("houseNo"));
				house.setArea(rs.getDouble("area"));
				house.setUnitPrice(rs.getDouble("unitPrice"));
				house.setTotalPrice(rs.getDouble("totalPrice"));
				house.setStatus(rs.getString("status"));
				house.setOwnerId(rs.getInt("ownerId"));
				houses.add(house);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return houses;
	}

	public House getHouseById(int houseId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		House house = null;
		try {
			conn = getConn();
			String sql = "SELECT * FROM test.house WHERE houseId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, houseId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				house = new House();
				house.setHouseId(rs.getInt("houseId"));
				house.setEstateId(rs.getInt("estateId"));
				house.setBuildingNo(rs.getInt("buildingNo"));
				house.setHouseNo(rs.getInt("houseNo"));
				house.setArea(rs.getDouble("area"));
				house.setUnitPrice(rs.getDouble("unitPrice"));
				house.setTotalPrice(rs.getDouble("totalPrice"));
				house.setStatus(rs.getString("status"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return house;
	}

	public void updateHouseStatus(int houseId, String status) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConn();
			String sql = "UPDATE test.house SET status = ? WHERE houseId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setInt(2, houseId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
	}

	public List<House> getHouseByStatus(String status) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<House> houses = new ArrayList<>();
		try {
			conn = getConn();
			String sql = "SELECT * FROM test.house WHERE status = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				House house = new House();
				house.setHouseId(rs.getInt("houseId"));
				house.setEstateId(rs.getInt("estateId"));
				house.setBuildingNo(rs.getInt("buildingNo"));
				house.setHouseNo(rs.getInt("houseNo"));
				house.setArea(rs.getDouble("area"));
				house.setUnitPrice(rs.getDouble("unitPrice"));
				house.setTotalPrice(rs.getDouble("totalPrice"));
				houses.add(house);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return houses;
	}

	public List<House> getHouseByEstateId(int EstateId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<House> houses = new ArrayList<>();
		try {
			conn = getConn();
			String sql = "SELECT * FROM test.house WHERE estateId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, EstateId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				House house = new House();
				house.setHouseId(rs.getInt("houseId"));
				house.setBuildingNo(rs.getInt("buildingNo"));
				house.setHouseNo(rs.getInt("houseNo"));
				house.setArea(rs.getDouble("area"));
				house.setUnitPrice(rs.getDouble("unitPrice"));
				house.setTotalPrice(rs.getDouble("totalPrice"));
				house.setOwnerId(rs.getInt("ownerId"));
				house.setStatus(rs.getString("status"));
				houses.add(house);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return houses;
	}

	public void deleteHouse(int houseId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConn();
			String sql = "delete from test.house Where houseId = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, houseId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
	}

	public void updatePrice(int houseId, double unitPrice) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			// 第一步：查询面积
			String sql1 = "SELECT area FROM test.house WHERE houseId = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, houseId);
			rs = pstmt.executeQuery();

			if (rs.next()) { // 确保结果集不为空
				double area = rs.getDouble("area");

				// 第二步：更新单价和总价
				String sql2 = "UPDATE test.house SET unitPrice = ?, totalPrice = ? WHERE houseId = ?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setDouble(1, unitPrice);
				pstmt.setDouble(2, unitPrice * area);
				pstmt.setInt(3, houseId);
				pstmt.executeUpdate();
			} else {
				// 如果没有找到对应的 houseId，可以选择抛出异常或进行其他处理
				throw new IllegalArgumentException("No house found with ID: " + houseId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
	}

	@Override
	public void updateHouseOwner(int houseId, int userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			String sql = "UPDATE test.house SET ownerId = ? WHERE houseId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, houseId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}

	}
}
