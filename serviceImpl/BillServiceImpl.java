package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import dao.BillDao;
import daoImpl.BillDaoImpl;
import entity.Bill;
import service.BillService;

public class BillServiceImpl implements BillService {
	private BillDao billDao = new BillDaoImpl();

	public void addBill(Bill bill) {
		billDao.addBill(bill);
	}

	public List<Bill> getBillsByUserId(int userId) throws Exception {
		List<Bill> bills=new ArrayList<Bill>();
		bills=billDao.getBillsByUserId(userId);
		if(bills.isEmpty())
			throw new Exception("不存在该用户的购买账单");
		else
			return bills;
	}

	public List<Bill> getAllBills() throws Exception {
		List<Bill> bills=new ArrayList<Bill>();
		bills=billDao.getAllBills();
		if(bills.isEmpty())
			throw new Exception("不存在任何购买账单");
		else
			return bills;
	}

	public List<Bill> getBillByHouseId(int houseId) throws Exception {
		List<Bill> bills=new ArrayList<Bill>();
		bills=billDao.getBillByHouseId(houseId);
		if(bills.isEmpty())
			throw new Exception("不存在该房屋的购买账单");
		else
			return bills;
	}

}
