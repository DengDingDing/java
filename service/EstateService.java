package service;

import java.util.List;
import entity.Estate;

public interface EstateService {
	public void addEstate(Estate estate) throws Exception;

	public List<Estate> getAllEstate();

	public Estate getEstateById(int EstateId) throws Exception;

	public void deleteEstate(int EstateId) throws Exception;
}