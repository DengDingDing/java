package serviceImpl;

import java.util.List;

import dao.EstateDao;
import daoImpl.EstateDaoImpl;
import entity.Estate;
import service.EstateService;

public class EstateServiceImpl implements EstateService {
	private EstateDao estateDao = new EstateDaoImpl();

	@Override
	public void addEstate(Estate estate) throws Exception {
		List<Estate> estates = estateDao.getAllEstates();
		for (Estate estate2 : estates) {
			if (estate2.getName().equals(estate.getName()) && estate2.getAddress() == estate.getAddress())
				throw new Exception("添加失败，该楼盘已存在");
		}
		estateDao.addEstate(estate);
		System.out.println("楼盘信息添加成功！");
	}

	@Override
	public List<Estate> getAllEstate() {
		return estateDao.getAllEstates();
	}

	@Override
	public Estate getEstateById(int EstateId) throws Exception {
		Estate estate = estateDao.getEstateById(EstateId);
		if (estate == null)
			throw new Exception("不存在该用户的房屋信息");
		else
			return estate;
	}

	@Override
	public void deleteEstate(int EstateId) throws Exception {
		Estate estate = estateDao.getEstateById(EstateId);
		if (estate == null)
			throw new Exception("删除失败，不存在该楼盘编号的楼盘");
		else {
			System.out.println("删除成功");
			estateDao.deleteEstateById(EstateId);
		}
	}

}