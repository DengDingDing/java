package dao;

import java.util.List;

import entity.House;

public interface HouseDao {
	public void addHouse(House house);

	public List<House> getAllHouses();

	public House getHouseById(int houseId);

	public void updateHouseStatus(int houseId, String status);

	public List<House> getHouseByStatus(String status);

	public List<House> getHousesByUserId(int userId);

	public List<House> getHouseByEstateId(int EstateId);

	void deleteHouse(int houseId);

	void updatePrice(int houseId, double unitPrice);

	void updateHouseOwner(int houseId, int userId);
}