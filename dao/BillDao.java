package dao;

import java.util.List;

import entity.Bill;

public interface BillDao {
	public void addBill(Bill bill);

	public List<Bill> getAllBills();

	public List<Bill> getBillsByUserId(int userId);

	public List<Bill> getBillByHouseId(int houseId);
}
