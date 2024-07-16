package dao;

import java.util.List;

import entity.Estate;

public interface EstateDao {
	public void addEstate(Estate estate);

	public List<Estate> getAllEstates();

	public Estate getEstateById(int EstateId);

	public void deleteEstateById(int EstateId);
}