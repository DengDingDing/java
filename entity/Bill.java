package entity;

import java.sql.Timestamp;

public class Bill {
	private int billId; // 销售记录编号
	private int houseId; // 销售的房屋编号
	private int userId; // 购买房屋的客户编号
	private Timestamp purchaseDate; // 销售日期
	private double finalPrice; // 成交价格

	public Bill() {

	}

	public Bill(int billId, int houseId, int userId, Timestamp purchaseDate, double finalPrice) {
		this.billId = billId;
		this.houseId = houseId;
		this.userId = userId;
		this.purchaseDate = purchaseDate;
		this.finalPrice = finalPrice;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getHouseId() {
		return houseId;
	}

	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
}
