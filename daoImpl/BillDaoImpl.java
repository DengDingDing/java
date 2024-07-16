package daoImpl;

import dao.BaseDao;
import dao.BillDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.*;

public class BillDaoImpl extends BaseDao implements BillDao {

	public void addBill(Bill bill) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConn();
			String sql = "INSERT INTO test.bill (houseId, userId, purchaseDate, finalPrice) VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, bill.getHouseId());
			pstmt.setInt(2, bill.getUserId());
			pstmt.setTimestamp(3, bill.getPurchaseDate());
			pstmt.setDouble(4, bill.getFinalPrice());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				bill.setBillId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
	}

	public List<Bill> getAllBills() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Bill> bills = new ArrayList<>();
		try {
			conn = getConn();
			String sql = "SELECT * FROM test.bill";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bill bill = new Bill();
				bill.setBillId(rs.getInt("billId"));
				bill.setHouseId(rs.getInt("houseId"));
				bill.setUserId(rs.getInt("userId"));
				bill.setPurchaseDate(rs.getTimestamp("purchaseDate"));
				bill.setFinalPrice(rs.getDouble("finalPrice"));
				bills.add(bill);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return bills;
	}

	public List<Bill> getBillByHouseId(int houseId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Bill> bills = new ArrayList<>();
		try {
			conn = getConn();
			String sql = "SELECT * FROM test.bill where houseId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, houseId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bill bill = new Bill();
				bill.setBillId(rs.getInt("billId"));
				bill.setHouseId(rs.getInt("houseId"));
				bill.setUserId(rs.getInt("userId"));
				bill.setPurchaseDate(rs.getTimestamp("purchaseDate"));
				bill.setFinalPrice(rs.getDouble("finalPrice"));
				bills.add(bill);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return bills;
	}

	public List<Bill> getBillsByUserId(int userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Bill> bills = new ArrayList<>();
		try {
			conn = getConn();
			String sql = "SELECT * FROM test.bill WHERE userId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bill bill = new Bill();
				bill.setBillId(rs.getInt("billId"));
				bill.setHouseId(rs.getInt("houseId"));
				bill.setUserId(rs.getInt("userId"));
				bill.setPurchaseDate(rs.getTimestamp("purchaseDate"));
				bill.setFinalPrice(rs.getDouble("finalPrice"));
				bills.add(bill);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return bills;
	}
}