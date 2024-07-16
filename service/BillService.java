package service;

import java.util.List;

import entity.Bill;

public interface BillService {
	public void addBill(Bill bill);

	public List<Bill> getBillsByUserId(int userId) throws Exception;

	public List<Bill> getAllBills() throws Exception;

	public List<Bill> getBillByHouseId(int houseId) throws Exception;
}
