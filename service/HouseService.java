package service;

import java.util.List;
import entity.House;

public interface HouseService {
	public void addHouse(House house) throws Exception;

	public List<House> getAllHouses();

	public House getHouseById(int houseId) throws Exception;

	public List<House> getHousesByUserId(int userId) throws Exception;

	public List<House> getHouseByStatus() throws Exception;

	public List<House> getHouseByEstateId(int estateId) throws Exception;

	public void updateHouseStatus(int houseId, String string);

	public void deleteHouse(int houseId) throws Exception;

	public void updatePrice(int houseId, double unitPrice,int userId) throws Exception;

	public void buyHouse(int houseId, int userId, double discount);
	
	public void sellHouse(int houseId,double unitPrice) throws Exception;
}
