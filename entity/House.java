package entity;

public class House {
	private int houseId; // 房屋编号
	private int estateId; // 楼盘编号
	private int buildingNo; // 楼栋编号
	private int houseNo; // 房号
	private double area; // 房屋面积
	private int ownerId; // 拥有者编号
	private double unitPrice; // 单价
	private double totalPrice; // 总价
	private String status; // 状态

	public House() {

	}

	public House(int houseId, int estateId, int buildingNo, int houseNo, double area, int ownerId, double unitPrice,
			double totalPrice, String status) {
		this.houseId = houseId;
		this.estateId = estateId;
		this.buildingNo = buildingNo;
		this.houseNo = houseNo;
		this.area = area;
		this.ownerId = ownerId;
		this.unitPrice = unitPrice;
		this.totalPrice = totalPrice;
		this.status = status;
	}

	public int getHouseId() {
		return houseId;
	}

	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}

	public int getEstateId() {
		return estateId;
	}

	public void setEstateId(int estateId) {
		this.estateId = estateId;
	}

	public int getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(int buildingNo) {
		this.buildingNo = buildingNo;
	}

	public int getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
}