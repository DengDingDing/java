package serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.HouseDao;
import dao.EstateDao;
import daoImpl.HouseDaoImpl;
import daoImpl.EstateDaoImpl;
import entity.*;
import service.HouseService;
import service.BillService;

public class HouseServiceImpl implements HouseService {
	private HouseDao houseDao = new HouseDaoImpl();

	public void addHouse(House house) throws Exception {
		List<House> houses=houseDao.getAllHouses();
		for(House house2:houses) {
			if(house2.getEstateId()==house.getEstateId()&&house2.getBuildingNo()==house.getBuildingNo()&&
					house2.getHouseNo()==house.getHouseNo())
				throw new Exception("添加失败，该房屋已存在");
		}
		EstateDao estateDao=new EstateDaoImpl();
		List<Estate> estates=estateDao.getAllEstates();
		for(Estate estate:estates) {
			if(estate.getEstateId()==house.getEstateId()) {
				houseDao.addHouse(house);
				System.out.println("房屋信息添加成功！");
				return;
			}
		}
		throw new Exception("该楼盘编号不存在");
	}

	public List<House> getAllHouses() {
		return houseDao.getAllHouses();
	}

	public House getHouseById(int houseId) throws Exception {
		House house = houseDao.getHouseById(houseId);
		if (house == null)
			throw new Exception("不存在该房屋编号的房屋信息");
		else
			return house;
	}

	public List<House> getHousesByUserId(int userId) throws Exception {
		List<House> houses = new ArrayList<House>();
		houses = houseDao.getHousesByUserId(userId);
		if (houses.isEmpty())
			throw new Exception("不存在该用户的房屋信息");
		else
			return houses;
	}

	public List<House> getHouseByStatus() throws Exception {
		List<House> houses = new ArrayList<House>();
		houses = houseDao.getHouseByStatus("待售");
		if (houses.isEmpty())
			throw new Exception("不存在待售的房屋信息");
		else
			return houses;
	}

	public List<House> getHouseByEstateId(int EstateId) throws Exception {
		List<House> houses = new ArrayList<House>();
		houses = houseDao.getHouseByEstateId(EstateId);
		if (houses.isEmpty())
			throw new Exception("不存在该楼盘的房屋信息");
		else
			return houses;
	}

	public void updateHouseStatus(int houseId, String status) {
		houseDao.updateHouseStatus(houseId, status);
	}

	public void deleteHouse(int houseId) throws Exception {
		House house = houseDao.getHouseById(houseId);
		if (house == null)
			throw new Exception("删除失败，不存在该房屋编号的房屋");
		else {
			houseDao.deleteHouse(houseId);
			System.out.println("删除成功");
		}
	}

	public void updatePrice(int houseId, double unitPrice,int userId) throws Exception {
		House house = houseDao.getHouseById(houseId);
		if (house != null && "待售".equals(house.getStatus())&&house.getOwnerId()==userId) {
			houseDao.updatePrice(houseId, unitPrice);
			System.out.println("房屋单价更改成功！");
		} else {
			throw new Exception("房屋未被出售或不存在！");
		}
	}

	public void buyHouse(int houseId, int userId, double discount) {
		House house = houseDao.getHouseById(houseId);
		if (house != null && "待售".equals(house.getStatus())) {
			double finalPrice = house.getTotalPrice() * discount;
			house.setTotalPrice(finalPrice);
			house.setUnitPrice(finalPrice / house.getArea());
			house.setOwnerId(userId);
			houseDao.updateHouseStatus(houseId, "已售");
			houseDao.updateHouseOwner(houseId,userId);

			BillService billservice = new BillServiceImpl();
			Bill bill = new Bill();
			bill.setHouseId(houseId);
			bill.setUserId(userId);
			bill.setPurchaseDate(new Timestamp(System.currentTimeMillis()));
			bill.setFinalPrice(finalPrice);
			billservice.addBill(bill);
			System.out.println("购买成功！");
		} else {
			System.out.println("房屋已售或不存在。");
		}
	}
	public void sellHouse(int houseId,double unitPrice) throws Exception {
		House house = houseDao.getHouseById(houseId);
		if (house != null && "已售".equals(house.getStatus())) {
			houseDao.updateHouseStatus(houseId, "待售");
			houseDao.updatePrice(houseId, unitPrice);
			System.out.println("房屋出售成功！");
		} else {
			throw new Exception("房屋未被购买或不存在！");
		}
	}
}