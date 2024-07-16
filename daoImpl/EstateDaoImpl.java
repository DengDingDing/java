package daoImpl;

import dao.BaseDao;
import dao.EstateDao;
import entity.Estate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstateDaoImpl extends BaseDao implements EstateDao {
	public void addEstate(Estate estate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConn();
			String sql = "INSERT INTO test.estate (name, area,developer,address) VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, estate.getName());
			pstmt.setDouble(2, estate.getArea());
			pstmt.setString(3, estate.getDeveloper());
			pstmt.setString(4, estate.getAddress());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				estate.setEstateId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
	}

	public List<Estate> getAllEstates() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Estate> estates = new ArrayList<>();
		try {
			conn = getConn();
			String sql = "SELECT * FROM test.estate";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Estate estate = new Estate();
				estate.setEstateId(rs.getInt("estateId"));
				estate.setName(rs.getString("name"));
				estate.setArea(rs.getDouble("area"));
				estate.setDeveloper(rs.getString("developer"));
				estate.setAddress(rs.getString("address"));
				estates.add(estate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return estates;
	}
	
	public Estate getEstateById(int EstateId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Estate estate = new Estate();
		try {
			conn = getConn();
			String sql = "SELECT * FROM test.estate Where estateId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, EstateId);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 检查结果集是否有数据  
				estate.setEstateId(rs.getInt("estateId"));
				estate.setName(rs.getString("name"));
				estate.setArea(rs.getDouble("area"));
				estate.setDeveloper(rs.getString("developer"));
				estate.setAddress(rs.getString("address"));
	        }else {  
	        	estate = null;
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return estate;
	}

	public void deleteEstateById(int EstateId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConn();
			String sql = "delete from test.estate Where estateId = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, EstateId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt,null);
		}
	}
}