package mtm.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mtm.db.pojos.Company;
import mtm.db.pojos.Employee;
import mtm.db.pojos.Hospital;
import mtm.db.pojos.Instrument;
import mtm.db.pojos.Machinery;
import mtm.db.pojos.Material;
import mtm.db.pojos.Order;
import mtm.db.pojos.Warehouse;
import mtm.db.jdbc.JDBCManager;

public class JDBCSelect 
{
	private Connection c;
	static JDBCManager jdbcManager = new JDBCManager();
	
	public JDBCSelect(Connection c)
	{
		this.c = c;
	}
	
	//Pojos
	
	public Hospital selectHospital( String query, int pk)
	{
		Hospital hosp = null;
		int hospitalID;
		String name, location,medicalSpecialization;
		
		try
		{
			String sql = query;
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, pk);
			ResultSet rs = prep.executeQuery();
			
			while (rs.next()) 
			{
				hospitalID = rs.getInt("hospitalID");
				name = rs.getString("name");
				location = rs.getString("location");
				medicalSpecialization = rs.getString("medical_specialization");
				
				hosp = new Hospital(hospitalID,name,location,medicalSpecialization);
			}
			
			prep.close();
			rs.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}

		
		return hosp;
	}
	
	public Hospital selectHospital( String query, String nameHosp)
	{
		Hospital hosp = null;
		int hospitalID;
		String name, location,medicalSpecialization;
		
		try
		{
			String sql = query;
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%"+nameHosp+"%");
			ResultSet rs = prep.executeQuery();
			
			while (rs.next()) 
			{
				hospitalID = rs.getInt("hospitalID");
				name = rs.getString("name");
				location = rs.getString("location");
				medicalSpecialization = rs.getString("medical_specialization");
				
				hosp = new Hospital(hospitalID,name,location,medicalSpecialization);
			}
			
			prep.close();
			rs.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}

		
		return hosp;
	}
	
	public Order selectOrder(String query, int pk)
	{
		
		Order order = null;
		
		try
		{
			String sql = query;
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, pk);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next())
			{
				int orderID = rs.getInt("orderID");
				int totalAmountInstruments = rs.getInt("total_amount_instruments");
				java.sql.Date orderDate = rs.getDate("order_date");
				java.sql.Date deliveryDate = rs.getDate("delivery_date");
				
				order = new Order(orderID,totalAmountInstruments,orderDate,deliveryDate);
				
			}
			
			prep.close();
			rs.close();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return order;
	}
	
	public Instrument selectInstrument(String query, int pkInstrument)
	{
		Instrument instrument = null;
		int instrumentID;
		
		//attributes of instrument
		String name;
		String model;
		String purpose;
		Integer amount;
		Integer numberUses;
		String bodyLocation;
		Integer price;
		Warehouse war;
		
		
		try
		{
			String sql = query;
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, pkInstrument);
			ResultSet rs = prep.executeQuery();
			
			while (rs.next()) 
			{
				instrumentID = rs.getInt("instrumentID");
				name = rs.getString("name");
				model = rs.getString("model");
				purpose = rs.getString("purpose");
				amount = rs.getInt("amount");
				numberUses = rs.getInt("numberUses");
				bodyLocation = rs.getString("bodyLocation");
				price = rs.getInt("price");
				int idW = rs.getInt("warehouseID");

				war = new Warehouse(idW);				
				instrument = new Instrument(instrumentID,name,model,purpose,amount,numberUses,bodyLocation,price,war);
			}
			
			prep.close();
			rs.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}

		
		return instrument;
	}
	
	public Warehouse selectWarehouse( String query, int pkWarehouse)
	{
		Warehouse warehouse = null;
		//attributes of warehouse
		
		int warehouseID;
		String warehouseLocation;
		Integer capacity;
		Integer filledSpace;
		
		
		try
		{
			String sql = query;
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, pkWarehouse);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) 
			{
				warehouseID = rs.getInt("warehouseID");
				warehouseLocation = rs.getString("warehouseLocation");
				capacity = rs.getInt("capacity");
				filledSpace = rs.getInt("filledSpace");
			
				warehouse = new Warehouse(warehouseID,warehouseLocation,capacity,filledSpace);
				
			}
			
			prep.close();
			rs.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}

		
		return warehouse;
	}
	
	public Employee selectEmployee( String query, int pk)
	{
		Employee emp = null;
		int employeeID;
		String name, specializationType,typeofContract;
		int machID;
		
		try
		{
			String sql = query;
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, pk);
			ResultSet rs = prep.executeQuery();
			
			while (rs.next()) 
			{
				employeeID = rs.getInt("employeeID");
				name = rs.getString("name");
				specializationType = rs.getString("specializationType");
				typeofContract = rs.getString("typeofContract");
				machID = rs.getInt("machineryID");
				
				Machinery mach = new Machinery(machID);
				emp = new Employee(employeeID,name,specializationType,typeofContract,mach);
			}
			
			prep.close();
			rs.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}

		return emp;
	}
	//Fallo en el Select//
	public Employee selectEmployee(String query, String nameEmp)
	{
		Employee emp = null;
		int employeeID;
		String name, specializationType,typeofContract;
		
		try
		{
			String sql = query;
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, nameEmp);
			ResultSet rs = prep.executeQuery();
			
			while (rs.next()) 
			{
				employeeID = rs.getInt("employeeID");
				name = rs.getString("name");
				specializationType = rs.getString("specializationType");
				typeofContract = rs.getString("typeofContract");
				emp = new Employee(employeeID,name,specializationType,typeofContract);	

			}
			
			prep.close();
			rs.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}

		
		return emp;
	}
	
	public Machinery selectMachinery(String query, int pk)
	{
		
		Machinery machinery = null;
		
		try
		{
			String sql = query;
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, pk);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next())
			{
				
				int machineryID = rs.getInt("machineryID");
				String machineryType = rs.getString("machineryType");
				String stateofMachinery = rs.getString("stateofMachinery");				
				java.sql.Date machiDate = rs.getDate("dateofInstallation");
				int sizeofMachinery = rs.getInt("sizeofMachinery");
				
				machinery = new Machinery(machineryID,machineryType,stateofMachinery,machiDate,sizeofMachinery);
				
			}
			
			prep.close();
			rs.close();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return machinery;
	}

	public Machinery selectMachinery(String query, String nameMach)
	{
		Machinery mach = null;
		int machineryID, sizeofMachinery;
		String machineryType, stateofMachinery;
		
		try
		{
			String sql = query;
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, nameMach);
			ResultSet rs = prep.executeQuery();
			
			while (rs.next()) 
			{				
				
				machineryID = rs.getInt("machineryID");
				machineryType = rs.getString("machineryType");
				stateofMachinery = rs.getString("stateofMachinery");
				java.sql.Date dateMach = rs.getDate("order_date");
				sizeofMachinery = rs.getInt("sizeofMachinery");
				
				mach = new Machinery(machineryID,machineryType,stateofMachinery,dateMach,sizeofMachinery);	
			}
			
			prep.close();
			rs.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}

		
		return mach;
	}

	public Company selectCompany(String query, int pk){
			Company comp = null;
			int companyID;
			String name, location;
			
			try
			{
				String sql = query;
				PreparedStatement prep = c.prepareStatement(sql);
				prep.setInt(1, pk);
				ResultSet rs = prep.executeQuery();
				
				while (rs.next()) 
				{
					companyID = rs.getInt("companyID");
					name = rs.getString("companyName");
					location = rs.getString("location");
					
					
					comp = new Company(companyID, name, location);
				}
				
				prep.close();
				rs.close();
				
			}catch(SQLException e)
			{
				e.printStackTrace();
			}

			return comp;
		
	}

	public Material selectMaterial(String query, int pk){
		Material mat = null;
		int materialID, weight, volume;
		String type;
		
		try
		{
			String sql = query;
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, pk);
			ResultSet rs = prep.executeQuery();
			
			while (rs.next()) 
			{
				materialID = rs.getInt("materialID");
				weight = rs.getInt("weight");
				volume = rs.getInt("volume");
				type = rs.getString("type");
				int companyID = rs.getInt("companyID");
				int machineryID = rs.getInt("machineryID");
				int warehouseID = rs.getInt("warehouseID");
				
				Company com = new Company(companyID);
				Machinery mach = new Machinery(machineryID);
				Warehouse war = new Warehouse(warehouseID);
				
				
				mat = new Material(materialID, weight, volume, type, com, mach, war);
			}
			
			prep.close();
			rs.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}

		return mat;
	
}

	
	//Arrays
	
	public ArrayList<Hospital> selectAllHospitals()
	{
		
		ArrayList<Hospital> hospitals = new ArrayList<Hospital>();
		
		try
		{
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM hospital";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				int hospitalID = rs.getInt("hospitalID");
				String name = rs.getString("name");
				String location = rs.getString("location");
				String medicalSpecialization = rs.getString("medical_specialization");
				
				Hospital hosp = new Hospital(hospitalID,name,location,medicalSpecialization);
				hospitals.add(hosp);
			}
			
			stmt.close();
			rs.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return hospitals;
	}
	
	public ArrayList<Order> selectAllOrders()
	{
		ArrayList<Order> orderList = new ArrayList<Order>();
		
		try
		{
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM orders";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				int orderID = rs.getInt("orderID");
				int totalAmountInstruments = rs.getInt("total_amount_instruments");
				java.sql.Date orderDate = rs.getDate("order_date");
				java.sql.Date deliveryDate = rs.getDate("delivery_date");
				
				Order order = new Order(orderID,totalAmountInstruments,orderDate,deliveryDate);
				orderList.add(order);
			}
			
			stmt.close();
			rs.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return orderList;
	}
	
	public ArrayList<Instrument> selectAllInstruments()
	{
		

		ArrayList<Instrument> instrumentList = new ArrayList<Instrument>();
		try
		{
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM instrument";
			ResultSet rs = stmt.executeQuery(sql);
			


			while(rs.next())
			{
				int instrumentID = rs.getInt("instrumentID");
				String name = rs.getString("name");
				String model = rs.getString("model");
				String purpose = rs.getString("purpose");
				int amount = rs.getInt("amount");
				int numberUses = rs.getInt("numberUses");
				String bodyLocation = rs.getString("bodyLocation");
				int price = rs.getInt("price");
				int wID = rs.getInt("warehouseID");
				Warehouse war = new Warehouse(wID);
				Instrument instrument = new Instrument(instrumentID,name,model,purpose,amount,numberUses,bodyLocation,price,war);
				instrumentList.add(instrument);
				
			}
			
			stmt.close();
			rs.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return instrumentList;
	}
	
	public ArrayList<Warehouse> selectAllWarehouses()
	{
		ArrayList<Warehouse> warehouseList = new ArrayList<Warehouse>();
		try
		{
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM warehouse";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				int warehouseID = rs.getInt("warehouseID");
				String warehouseLocation = rs.getString("warehouseLocation");
				int capacity = rs.getInt("capacity");
				int filledSpace = rs.getInt("filledSpace");
				
				Warehouse warehouse = new Warehouse(warehouseID,warehouseLocation,capacity,filledSpace);
				warehouseList.add(warehouse);
			}
			
			stmt.close();
			rs.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return warehouseList;
	}
	
	public ArrayList<Employee> selectAllEmployee(){
		
		ArrayList<Employee> employee = new ArrayList<Employee>();
		try
		{
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM employee";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				int employeeID = rs.getInt("employeeID");
				String name = rs.getString("name");
				String typeofContract = rs.getString("typeofContract");
				String specializationType = rs.getString("specializationType");
				int machID = rs.getInt("machineryID");
				
				Machinery mach = new Machinery(machID);
				Employee emp = new Employee(employeeID,name,specializationType,typeofContract,mach);
				employee.add(emp);
			}
			
			stmt.close();
			rs.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return employee;
	}
	
	public ArrayList<Machinery> selectAllMachinery()
	{
		ArrayList<Machinery> machineryList = new ArrayList<Machinery>();
		
		try
		{
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM machinery";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				int machineryID = rs.getInt("machineryID");
				String machineryType = rs.getString("machineryType");
				String stateofMachinery = rs.getString("stateofMachinery");				
				java.sql.Date machiDate = rs.getDate("dateofInstallation");
				int sizeofMachinery = rs.getInt("sizeofMachinery");
				
				Machinery machinery = new Machinery(machineryID,machineryType,stateofMachinery,machiDate,sizeofMachinery);
				machineryList.add(machinery);
			}
			
			stmt.close();
			rs.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return machineryList;
	}
	
	public ArrayList<Company> selectAllCompanies()
	{
		ArrayList<Company> companies = new ArrayList<Company>();
		try
		{
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM company";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				int companyID = rs.getInt("companyID");
				String location = rs.getString("location");
				String name = rs.getString("companyName");
				
				Company com = new Company(companyID, location, name);
				companies.add(com);
			}
			
			stmt.close();
			rs.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return companies;
	}
	
	public  ArrayList<Material> selectAllMaterials()
	{
		ArrayList<Material> materials = new ArrayList<Material>();
		try
		{
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM material";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				int materialID = rs.getInt("materialID");
				int weight = rs.getInt("weight");
				int volume = rs.getInt("volume");
				String type = rs.getString("type");
				int companyID = rs.getInt("companyID");
				int machineryID = rs.getInt("machineryID");
				int warehouseID = rs.getInt("warehouseID");
				//Problema de la Conexi�n @ERROR
				
				Company com = new Company(companyID);
				Machinery mach = new Machinery(machineryID);
				Warehouse war = new Warehouse(warehouseID);
				
				
				Material mat = new Material(materialID, weight, volume, type, com, mach, war);
				materials.add(mat);
			}
			
			stmt.close();
			rs.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return materials;
	}

	// Help Methods
	
	public int selectIdTable(String query, String tableName)
	{
		int id = -1;
		try
		{
			
			String sql = query;
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, tableName);
			ResultSet rs = prep.executeQuery();
			
			id = rs.getInt("seq");
			
			prep.close();
			rs.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return id;
	}
	
}
