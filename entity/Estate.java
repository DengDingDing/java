package entity;

public class Estate {
	private int estateId;// 楼盘编号
	private String name;// 楼盘名称
	private double area;// 占地面积
	private String developer;// 开发商名称
	private String address;// 地址

	public Estate() {

	}

	public Estate(int estateId, String name, double area, String developer, String address) {
		this.estateId = estateId;
		this.name = name;
		this.area = area;
		this.developer = developer;
		this.address = address;
	}

	public int getEstateId() {
		return estateId;
	}

	public void setEstateId(int estateId) {
		this.estateId = estateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
