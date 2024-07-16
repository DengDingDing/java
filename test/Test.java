package test;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import entity.Bill;
import entity.Estate;
import entity.House;
import entity.User;
import service.*;
import serviceImpl.*;

public class Test {
	private static HouseService houseService = new HouseServiceImpl();
	private static UserService userService = new UserServiceImpl();
	private static BillService billService = new BillServiceImpl();
	private static EstateService estateService = new EstateServiceImpl();
	private static User loggedInUser = null;
	private static String userType = "";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			if (loggedInUser == null)
				showLoginMenu(scanner);
			else
				showMainMenu(scanner);
		}
	}

	private static void showLoginMenu(Scanner scanner) {
		System.out.println("**********欢迎来到XXX房地产公司**********");
		System.out.println("登录菜单:");
		System.out.println("1. 注册普通用户");
		System.out.println("2. 注册房屋销售用户");
		System.out.println("3. 普通用户登录");
		System.out.println("4. 房屋销售登录");
		System.out.println("5. 退出系统");
		System.out.print("请选择操作（输入1~5的序号）：");
		try {
			int choice = scanner.nextInt();
			scanner.nextLine();// 清除输入缓冲区
			switch (choice) {
			case 1:
				register(scanner, "regular_user");
				break;
			case 2:
				register(scanner, "sales_user");
				break;
			case 3:
				login(scanner, "regular_user");
				break;
			case 4:
				login(scanner, "sales_user");
				break;
			case 5:
				System.out.println("谢谢使用！");
				System.exit(0);
				break;
			default:
				System.out.println("无效的选择，请重新输入！");
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("无效的选择,请输入数字选项。");
			scanner.nextLine(); // 清除输入缓冲区
		}
	}

	private static void register(Scanner scanner, String userType) {
		String[] numbers = new String[9];
		for (int i = 0; i < 9; ++i) {
			numbers[i] = createNumber(userType);
		}
		System.out.println("**********可选择的账号**********");
		for (int i = 0; i < 9; ++i) {
			System.out.print(i + 1 + "." + numbers[i] + "\t\t");
			if ((i + 1) % 3 == 0) {
				System.out.println();
			}
		}
		System.out.print("请选择账号（输入1~9的序号）：");
		try {
			int index = scanner.nextInt();
			scanner.nextLine();// 清除输入缓冲区
			String number = numbers[index - 1];
			System.out.print("请输入用户姓名: ");
			String username = scanner.nextLine();
			System.out.print("请输入密码: ");
			String password = scanner.nextLine();
			System.out.print("请再次输入确认密码: ");
			String password2 = scanner.nextLine();
			if (!password.equals(password2)) {
				throw new Exception("注册失败，两次输入的密码不一致。");
			}
			userService.addUser(number, username, password, userType);
			System.out.println("注册成功！" + username + "用户，您好，" + "请牢记您的账号和密码！");
		} catch (InputMismatchException e) {
			System.out.println("注册失败，请输入数字选项。");
			scanner.nextLine(); // 清除输入缓冲区
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void login(Scanner scanner, String userType) {
		System.out.print("请输入账号: ");
		String account = scanner.nextLine();
		int attempt = 0;
		boolean success = false;
		while (attempt < 3 && !success) {
			System.out.print("输入密码：");
			String password = scanner.nextLine();
			try {
				User user = userService.getUser(account, password, userType);
				success = true;
				Test.loggedInUser = user;
				Test.userType = userType;
				System.out.println("登录成功！");
			} catch (Exception e) {
				attempt++;
				System.out.println(e.getMessage());
				if (attempt >= 3) {
					System.out.println("错误次数超过三次，终止登录。");
				}
				if (e.getMessage().equals("登录失败，该账号不存在，请先完成注册！")) {
					break;
				}
			}
		}
	}

	private static void showMainMenu(Scanner scanner) {
		System.out.println("**********欢迎来到XXX房地产公司**********");
		System.out.println("主菜单:");
		if ("regular_user".equals(userType)) {
			System.out.println("1. 查看待售房屋信息");
			System.out.println("2. 查看我的房屋信息");
			System.out.println("3. 查看我的购买账单");
			System.out.println("4. 购买房屋");
			System.out.println("5. 出售房屋");
			System.out.println("6. 更改待售房屋单价");
			System.out.println("7. 更改密码");
			System.out.println("8. 账号注销");
			System.out.println("9. 退出登录");
			System.out.print("请选择操作（输入1~9的序号）：");
			try {
				int choice = scanner.nextInt();
				scanner.nextLine(); // 清除输入缓冲区
				switch (choice) {
				case 1:
					viewSaleHouses();
					break;
				case 2:
					viewMyHouses();
					break;
				case 3:
					viewMyBills();
					break;
				case 4:
					buyHouse(scanner);
					break;
				case 5:
					sellHouse(scanner);
					break;
				case 6:
					changePrice(scanner);
					break;
				case 7:
					changePassword(scanner,"regular_user");
					break;
				case 8:
					cancelAccount(scanner,"regular_user");
					break;
				case 9:
					loggedInUser = null;
					userType=null;
					System.out.println("已退出登录！");
					break;
				default:
					System.out.println("无效的选择，请重新输入！");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("操作失败，请输入数字选项。");
				scanner.nextLine(); // 清除输入缓冲区
			}
		} else if ("sales_user".equals(userType)) {
			System.out.println("1. 楼盘信息");
			System.out.println("2. 房屋信息");
			System.out.println("3. 账单信息");
			System.out.println("4. 用户信息");
			System.out.println("5. 更改密码");
			System.out.println("6. 账号注销");
			System.out.println("7. 退出登录");
			System.out.print("请选择操作（输入1~7的序号）：");
			try {
				int choice = scanner.nextInt();
				scanner.nextLine(); // 清除输入缓冲区
				switch (choice) {
				case 1:
					System.out.println("1. 查看所有楼盘信息");
					System.out.println("2. 添加楼盘信息");
					System.out.println("3. 删除楼盘信息");
					System.out.print("请选择操作（输入1~3的序号）：");
					try {
						int choicet = scanner.nextInt();
						scanner.nextLine(); // 清除输入缓冲区
						switch(choicet) {
						case 1:
							viewAllEstates();
							break;
						case 2:
							addEstate(scanner);
							break;
						case 3:
							deleteEstate(scanner);
							break;
						default:
							System.out.println("无效的选择，请重新输入！");
							break;
						}
					} catch (InputMismatchException e) {
						System.out.println("操作失败，请输入数字选项。");
						scanner.nextLine(); // 清除输入缓冲区
					}
					break;
				case 2:
					System.out.println("1. 查看所有房屋信息");
					System.out.println("2. 添加房屋信息");
					System.out.println("3. 删除房屋信息");
					try {
						int choicet = scanner.nextInt();
						scanner.nextLine(); // 清除输入缓冲区
						switch(choicet) {
						case 1:
							viewAllHouses();
							break;
						case 2:
							addHouse(scanner);
							break;
						case 3:
							deleteHouse(scanner);
							break;
						default:
							System.out.println("无效的选择，请重新输入！");
							break;
						}
					} catch (InputMismatchException e) {
						System.out.println("操作失败，请输入数字选项。");
						scanner.nextLine(); // 清除输入缓冲区
					}
					break;
				case 3:
					System.out.println("1. 查看所有购买账单");
					System.out.println("2. 查看特定房屋账单");
					System.out.println("3. 查看特定用户账单");
					try {
						int choicet = scanner.nextInt();
						scanner.nextLine(); // 清除输入缓冲区
						switch(choicet) {
						case 1:
							viewAllBills();
							break;
						case 2:
							viewHouseBills(scanner);
							break;
						case 3:
							viewUserBills(scanner);
							break;
						default:
							System.out.println("无效的选择，请重新输入！");
							break;
						}
					} catch (InputMismatchException e) {
						System.out.println("操作失败，请输入数字选项。");
						scanner.nextLine(); // 清除输入缓冲区
					}
					break;
				case 4:
					viewAllUsers();
					break;
				case 5:
					changePassword(scanner,"sales_user");
					break;
				case 6:
					cancelAccount(scanner,"sales_user");
					break;
				case 7:
					loggedInUser = null;
					System.out.println("已退出登录！");
					break;
				default:
					System.out.println("无效的选择，请重新输入！");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("操作失败，请输入数字选项。");
				scanner.nextLine(); // 清除输入缓冲区
			}
		}
	}

	private static void viewUserBills(Scanner scanner) {
		try {
			System.out.print("请输入用户编号: ");
			int userId = scanner.nextInt();
			List<Bill> bills = billService.getBillsByUserId(userId);
			System.out.println("账单编号\t房屋编号\t卖家编号\t购买日期\t\t\t最终价格");
			for (Bill bill : bills) {
				System.out.println(bill.getBillId() + "\t" + bill.getHouseId() + "\t" + bill.getUserId() + "\t"
						+ bill.getPurchaseDate() + "\t" + bill.getFinalPrice());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void viewAllEstates() {
		List<Estate> estates = estateService.getAllEstate();
		System.out.println("楼盘编号\t楼盘名称\t占地面积\t开发商\t\t地址");
		for (Estate estate : estates) {
			System.out.println(estate.getEstateId() + "\t" + estate.getName() + "\t" + estate.getArea() + "\t"
					+ estate.getDeveloper() + "\t" + estate.getAddress());
		}
	}

	private static void viewAllBills() {
		try {
			List<Bill> bills = billService.getAllBills();
			System.out.println("账单编号\t房屋编号\t卖家编号\t购买日期\t\t\t最终价格");
			for (Bill bill : bills) {
				System.out.println(bill.getBillId() + "\t" + bill.getHouseId() + "\t" + bill.getUserId() + "\t"
						+ bill.getPurchaseDate() + "\t" + bill.getFinalPrice());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void viewAllHouses() {
		List<House> houses = houseService.getAllHouses();
		System.out.println("房屋编号\t楼盘编号\t楼号\t房号\t房屋面积\t单价\t总价\t\t状态\t拥有者\t楼盘名称\t楼盘地址");
		for (House house : houses) {
			System.out.print(house.getHouseId() + "\t" + house.getEstateId() + "\t" + house.getBuildingNo() + "\t"
					+ house.getHouseNo() + "\t" + house.getArea() + "\t" + house.getUnitPrice() + "\t"
					+ house.getTotalPrice() + "\t" + house.getStatus() + "\t" + house.getOwnerId());
			try{
				Estate estate = estateService.getEstateById(house.getEstateId());
				System.out.println("\t"+estate.getName()+"\t"+estate.getAddress());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
			
	}

	private static void viewAllUsers() {
		List<User> users = userService.getAllUsers();
		System.out.println("用户编号\t账号\t密码\t用户姓名");
		for (User user : users) {
			System.out.println(user.getUserId() + "\t" + user.getPassword() + "\t" + user.getPassword() + "\t"
					+ user.getUsername());
		}
	}

	private static void viewHouseBills(Scanner scanner) {
		try {
			System.out.print("请输入房屋编号: ");
			int houseId = scanner.nextInt();
			scanner.nextLine(); // 清除输入缓冲区
			List<Bill> bills = billService.getBillByHouseId(houseId);
			System.out.println("账单编号\t房屋编号\t卖家编号\t购买日期\t\t\t最终价格");
			for (Bill bill : bills) {
				System.out.println(bill.getBillId() + "\t" + bill.getHouseId() + "\t" + bill.getUserId() + "\t"
						+ bill.getPurchaseDate() + "\t" + bill.getFinalPrice());
			}
		} catch (InputMismatchException e) {
			System.out.println("操作失败，请输入数字编号。");
			scanner.nextLine(); // 清除输入缓冲区
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void viewMyBills() {
		try {
			List<Bill> bills = billService.getBillsByUserId(loggedInUser.getUserId());
			System.out.println("账单编号\t房屋编号\t用户编号\t购买日期\t\t\t最终价格");
			for (Bill bill : bills) {
				System.out.println(bill.getBillId() + "\t" + bill.getHouseId() + "\t" + bill.getUserId() + "\t"
						+ bill.getPurchaseDate() + "\t" + bill.getFinalPrice());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void viewMyHouses() {
		try {
			List<House> houses = houseService.getHousesByUserId(loggedInUser.getUserId());
			System.out.println("房屋编号\t楼盘编号\t楼号\t房号\t房屋面积\t单价\t总价\t\t状态\t拥有者\t楼盘名称\t楼盘地址");
			for (House house : houses) {
				System.out.print(house.getHouseId() + "\t" + house.getEstateId() + "\t" + house.getBuildingNo() + "\t"
						+ house.getHouseNo() + "\t" + house.getArea() + "\t" + house.getUnitPrice() + "\t"
						+ house.getTotalPrice() + "\t" + house.getStatus() + "\t" + house.getOwnerId());
				try{
					Estate estate = estateService.getEstateById(house.getEstateId());
					System.out.println("\t"+estate.getName()+"\t"+estate.getAddress());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void viewSaleHouses() {
		try {
			List<House> houses = houseService.getHouseByStatus();
			System.out.println("房屋编号\t楼盘编号\t楼号\t房号\t房屋面积\t单价\t总价\t\t状态\t拥有者\t楼盘名称\t楼盘地址");
			for (House house : houses) {
				System.out.print(house.getHouseId() + "\t" + house.getEstateId() + "\t" + house.getBuildingNo() + "\t"
						+ house.getHouseNo() + "\t" + house.getArea() + "\t" + house.getUnitPrice() + "\t"
						+ house.getTotalPrice() + "\t" + house.getStatus() + "\t" + house.getOwnerId());
				try{
					Estate estate = estateService.getEstateById(house.getEstateId());
					System.out.println("\t"+estate.getName()+"\t"+estate.getAddress());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void addHouse(Scanner scanner) {
		try {
			System.out.print("请输入楼盘编号: ");
			int estateId = scanner.nextInt();
			scanner.nextLine(); // 清除输入缓冲区
			System.out.print("请输入楼栋编号: ");
			int buildingNo = scanner.nextInt();
			scanner.nextLine(); // 清除输入缓冲区
			System.out.print("请输入房号: ");
			int houseNo = scanner.nextInt();
			scanner.nextLine(); // 清除输入缓冲区
			System.out.print("请输入房屋面积: ");
			double area = scanner.nextDouble();
			scanner.nextLine();// 清除输入缓冲区
			System.out.print("请输入单价: ");
			double unitPrice = scanner.nextDouble();
			scanner.nextLine();// 清除输入缓冲区
			double totalPrice = area * unitPrice;
			House house = new House();
			house.setEstateId(estateId);
			house.setBuildingNo(buildingNo);
			house.setHouseNo(houseNo);
			house.setArea(area);
			house.setUnitPrice(unitPrice);
			house.setTotalPrice(totalPrice);
			house.setStatus("待售");
			houseService.addHouse(house);
		} catch (InputMismatchException e) {
			System.out.println("操作失败，请输入数字。");
			scanner.nextLine(); // 清除输入缓冲区
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void deleteHouse(Scanner scanner) {
		Test.viewAllHouses();
		try {
			System.out.print("请输入房屋编号: ");
			int houseId = scanner.nextInt();
			scanner.nextLine(); // 清除输入缓冲区
			houseService.deleteHouse(houseId);
		} catch (InputMismatchException e) {
			System.out.println("操作失败，请输入数字编号。");
			scanner.nextLine(); // 清除输入缓冲区
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void addEstate(Scanner scanner) {
		try {
			System.out.print("请输入楼盘名称: ");
			String estatename = scanner.nextLine();
			System.out.print("请输入占地面积: ");
			double area = scanner.nextDouble();
			scanner.nextLine(); // 清除输入缓冲区
			System.out.print("请输入开发商名称: ");
			String developer = scanner.nextLine();
			System.out.print("请输入楼盘地址: ");
			String address = scanner.nextLine();
			Estate estate = new Estate();
			estate.setName(estatename);
			estate.setArea(area);
			estate.setDeveloper(developer);
			estate.setAddress(address);
			estateService.addEstate(estate);
		} catch (InputMismatchException e) {
			System.out.println("操作失败，请输入数字。");
			scanner.nextLine(); // 清除输入缓冲区
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void deleteEstate(Scanner scanner) {
		Test.viewAllEstates();
		try {
			System.out.print("请输入楼盘编号: ");
			int estateId = scanner.nextInt();
			scanner.nextLine(); // 清除输入缓冲区
			estateService.deleteEstate(estateId);
			;
		} catch (InputMismatchException e) {
			System.out.println("操作失败，请输入数字编号。");
			scanner.nextLine(); // 清除输入缓冲区
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void cancelAccount(Scanner scanner,String userType) {
		try {
			System.out.println("确定注销账号？ (0确定  1取消):");
			int check = scanner.nextInt();
			scanner.nextLine(); // 清除输入缓冲区
			if (check == 0) {
				userService.deleteUser(loggedInUser.getUserId(), userType);
				loggedInUser = null;
			}
		} catch (InputMismatchException e) {
			System.out.println("操作失败，请输入数字。");
			scanner.nextLine(); // 清除输入缓冲区
		}
	}

	private static void changePrice(Scanner scanner) {
		try {
			// 查看我的房屋
			List<House> houses = houseService.getHousesByUserId(loggedInUser.getUserId());
			System.out.println("房屋编号\t楼盘编号\t楼号\t房号\t房屋面积\t单价\t总价\t\t状态\t拥有者");
			for (House house : houses) {
				System.out.println(house.getHouseId() + "\t" + house.getEstateId() + "\t" + house.getBuildingNo() + "\t"
						+ house.getHouseNo() + "\t" + house.getArea() + "\t" + house.getUnitPrice() + "\t"
						+ house.getTotalPrice() + "\t" + house.getStatus() + "\t" + house.getOwnerId());
			}

			System.out.print("请输入要更改单价的房屋编号: ");
			int houseId = scanner.nextInt();
			scanner.nextLine(); // 清除输入缓冲区
			System.out.print("请输入新的单价: ");
			double unitPrice = scanner.nextDouble();
			scanner.nextLine();// 清除输入缓冲区
			houseService.updatePrice(houseId, unitPrice, loggedInUser.getUserId());
		} catch (InputMismatchException e) {
			System.out.println("操作失败，请输入数字编号。");
			scanner.nextLine(); // 清除输入缓冲区
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void changePassword(Scanner scanner,String userType) {
		try {
			System.out.print("请输入新密码: ");
			String password = scanner.nextLine();
			System.out.print("请再次输入确认密码: ");
			String password2 = scanner.nextLine();
			if (!password.equals(password2)) {
				throw new Exception("更改失败，两次输入的密码不一致。");
			}
			userService.updatePassword(loggedInUser.getUserId(), password, userType);
			loggedInUser = null;
			System.out.println("更改成功，请重新登录！");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void buyHouse(Scanner scanner) {
		Test.viewSaleHouses();
		try {
			System.out.print("请输入要购买的房屋编号: ");
			int houseId = scanner.nextInt();
			scanner.nextLine(); // 清除输入缓冲区
			System.out.print("请输入折扣（例如0.95表示九五折）: ");
			double discount = scanner.nextDouble();
			scanner.nextLine(); // 清除输入缓冲区

			houseService.buyHouse(houseId, loggedInUser.getUserId(), discount);
		} catch (InputMismatchException e) {
			System.out.println("操作失败，请输入数字。");
			scanner.nextLine(); // 清除输入缓冲区
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void sellHouse(Scanner scanner) {
		try {
			// 查看我的房屋
			List<House> houses = houseService.getHousesByUserId(loggedInUser.getUserId());
			System.out.println("房屋编号\t楼盘编号\t楼号\t房号\t房屋面积\t单价\t总价\t状态\t拥有者");
			for (House house : houses) {
				System.out.println(house.getHouseId() + "\t" + house.getEstateId() + "\t" + house.getBuildingNo() + "\t"
						+ house.getHouseNo() + "\t" + house.getArea() + "\t" + house.getUnitPrice() + "\t"
						+ house.getTotalPrice() + "\t" + house.getStatus() + "\t" + house.getOwnerId());
			}

			System.out.print("请输入要出售的房屋编号: ");
			int houseId = scanner.nextInt();
			scanner.nextLine(); // 清除输入缓冲区
			System.out.print("请输入单价: ");
			double unitPrice = scanner.nextDouble();
			scanner.nextLine();// 清除输入缓冲区
			houseService.sellHouse(houseId, unitPrice);
		} catch (InputMismatchException e) {
			System.out.println("操作失败，请输入数字编号。");
			scanner.nextLine(); // 清除输入缓冲区
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static String createNumber(String userType) {
		Random random = new Random();
		boolean isExist = false; // 记录现有账号中是否存在此账号用户 是：true 否：false
		String number = "";
		int temp = 0;
		do {
			isExist = false; // 标志位重置为false,用于控制外重循环，当生成了
			do {
				temp = random.nextInt(999);
			} while (temp < 100);
			Set<String> accounts = userService.getAllUsersAccount(userType);
			if ("regular_user".equals(userType)) {
				number = "A" + temp;// 生成之前，前面加“A”
				// 和现有用户的账号比较，不能是重复
				for (String account : accounts) {
					if (number.equals(account)) {
						isExist = true;
						break;
					}
				}
			} else {
				number = "B" + temp;// 生成之前，前面加“B”
				// 和现有用户的账号比较，不能是重复
				for (String account : accounts) {
					if (number.equals(account)) {
						isExist = true;
						break;
					}
				}
			}
		} while (isExist);
		return number;
	}
}
