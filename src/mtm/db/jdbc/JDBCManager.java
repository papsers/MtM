package mtm.db.jdbc;

import mtm.db.pojos.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;


public class JDBCManager 
{
	
	public Connection c = null;
	
	
	//7 8 Objects//
	//Different Methods to interact with the dataBase
	//Different Methods to create the Objects
	
	//Methods to create the Pojos

	//Pablo
	
	public Order createPojoOrder(int number,String d11,String d12,String d13,String d21,String d22,String d23)
	{
		Date date1SQL = dateConverterSQL(d13,d12,d11);
		Date date2SQL = dateConverterSQL(d23,d22,d21);
		Order ord = new Order(number,date1SQL,date2SQL);
		return ord;
	}
	
	//Celia

	
	public Machinery createPojoMachinery(String machineryType, String stateofMachinery,String d,String m, String y, int sizeofMachinery){
		
		Date date3SQL = dateConverterSQL(y,m,d);
		Machinery mach = new Machinery (machineryType, stateofMachinery, date3SQL, sizeofMachinery);
		return mach;
		
	}


	
	//Methods to connect with the DataBase
	//Connection
	public Connection openConnection()
{
	try
	{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:./db/mtm.db");
		c.createStatement().execute("PRAGMA foreign_keys=ON");
		System.out.println("Database connection opened.");
	
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return c;
}	
	public void closeConnection()
	{
		try
		{
			c.close();
			System.out.println("Database Closed");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//Methods to Create the tables
	
	public void createTables()
	{
		JDBCCreate codeCreate = new JDBCCreate(c);
		

		codeCreate.createTables();
		
		
	}
	public void createTableHospital()
	{
		JDBCCreate codeCreate = new JDBCCreate(c);
		
		
		codeCreate.createTableHospital();
		
		
	}
	public void createTableOrder()
	{
		JDBCCreate codeCreate = new JDBCCreate(c);
		
		
		codeCreate.createTableOrder();
		
	}
	public void createTableCompany()
	{
		JDBCCreate codeCreate = new JDBCCreate(c);
		
		
		codeCreate.createTableCompany();
		
	}
	public void createTableEmployee()
	{
		JDBCCreate codeCreate = new JDBCCreate(c);
		
		
		codeCreate.createTableEmployee();
		
	}
	public void createTableInstrument()
	{
		JDBCCreate codeCreate = new JDBCCreate(c);
		
		
		codeCreate.createTableInstrument();
		
	}
	public void createTableMachinery()
	{
		JDBCCreate codeCreate = new JDBCCreate(c);
		
		
		codeCreate.createTableMachinery();
		
	}
	public void createTableMaterial()
	{
		JDBCCreate codeCreate = new JDBCCreate(c);
		
		
		codeCreate.createTableMaterial();
		
	}
	public void createTableWarehouse()
	{
		JDBCCreate codeCreate = new JDBCCreate(c);
		
		
		codeCreate.createTableWarehouse();
		
	}
		//Relational Tables
	public void createTableHospitalOrder()
	{
		JDBCCreate codeCreate = new JDBCCreate(c);
		
		codeCreate.createTableHospitalOrders();
	}
	
	public void createTableInstrumentOrder()
	{
		JDBCCreate codeCreate = new JDBCCreate(c);
		
		codeCreate.createTableInstrumentOrders();
		
	}
	
	//Methods to Insert
	
	//Pablo
	public void insert(Hospital obj)
	{
		JDBCInsert codeInsert = new JDBCInsert(c);
		String selQuery = "SELECT * FROM hospital WHERE name LIKE ?";
		if(valExist(selQuery,-1,obj.getName()))
		{
			System.out.println("This Hospital exist");
		}else
		{
	
			codeInsert.insert(obj);
		
		}
		
	}
	public void insert(Order obj)
	{
		JDBCInsert codeInsert = new JDBCInsert(c);
		
	
		codeInsert.insert(obj);
		
	}
	
	//Charo
	
	public void insert(Instrument obj)
	{
		JDBCInsert codeInsert = new JDBCInsert(c);
		
		
		codeInsert.insert(obj);
		
	}
	
	public void insert(Warehouse obj)
	{
		JDBCInsert codeInsert = new JDBCInsert(c);
		
		
		codeInsert.insert(obj);
		
	}
	//Alex
	
	public void insert(Company obj)
	{
		JDBCInsert codeInsert = new JDBCInsert(c);
		
		
		codeInsert.insert(obj);
	}
	
	public void insert(Material obj)
	{
		JDBCInsert codeInsert = new JDBCInsert(c);
		
		
		codeInsert.insert(obj);
	}

	
	
	//Celia
	
	public void insert(Employee obj)
	{
		JDBCInsert codeInsert = new JDBCInsert(c);

		
			codeInsert.insert(obj);
			
		
	}
	public void insert(Machinery obj)
	{
		JDBCInsert codeInsert = new JDBCInsert(c);
		
		
		codeInsert.insert(obj);
		
	}
	
	//Methods to Delete
	
	//Pablo
	public void deleteHospital(int primaryKey)
	{
		
		String sqlQuery = "SELECT * FROM hospital WHERE hospital_ID = ?";
		if(valExist(sqlQuery,primaryKey,null))
		{
			JDBCDelete sqlDelete = new JDBCDelete(c);
			
			sqlDelete.deleteHospital(primaryKey);
			
			//We delate the corresponding orders related with the hospital
			
			String relationalTable = "hospital_orders";
			String pk1AttributeSearch = "order_ID";
			String pkAttributeCompere = "hospital_ID";
			int pkValueCompare = primaryKey;
			
			ArrayList<Integer> orderPkRelationFound = new ArrayList<Integer>();
			orderPkRelationFound = foundRelation(relationalTable,pk1AttributeSearch,pkAttributeCompere,pkValueCompare);
			
			Iterator<Integer> iter = orderPkRelationFound.iterator();
			while(iter.hasNext())
			{
				int i = iter.next();
				deleteOrder(i);
				deleteRelationHospitalOrder(i,pk1AttributeSearch);
			}
			
			
		}
		else
		{
			System.out.println("\n The hospital does not exist \n");
		}
		
	}
	
	public void deleteOrder(int primaryKey)
	{
		String sqlQuery = "SELECT * FROM orders WHERE order_ID == ?";
		if(valExist(sqlQuery,primaryKey,null))
		{
			JDBCDelete sqlDelete = new JDBCDelete(c);
			
			sqlDelete.deleteOrder(primaryKey);
			
			
			//We also delete the relation
			String table = "hospital_orders";
			String  pk1AtrivuteSearch = "order_ID";
			String pkAtrivuteCompere = "order_ID";
			int pkValueCompere = primaryKey;
			if(!sharedRelation(table, pk1AtrivuteSearch, pkAtrivuteCompere, pkValueCompere))
			{
				String colPk = "order_ID";
				deleteRelationHospitalOrder(primaryKey,colPk);
			}
			

		}
		else
		{
			System.out.println("\n The Order does not exist \n");
		}
	}
	
	//Celia
	public void deleteEmployee(int primaryKey)
	{
		
		String sqlQuery = "SELECT * FROM employee WHERE employee_ID == ?";
		if(valExist(sqlQuery,primaryKey,null))
		{
			JDBCDelete sql = new JDBCDelete(c);
			sql.deleteEmployee(primaryKey);
		}
		else
		{
			System.out.println("\n The Employee does not exist \n");
		}
		
	}
	
	public void deleteMachinery(int primaryKey)
	{
		String sqlQuery = "SELECT * FROM orders WHERE machineryID == ?";
		if(valExist(sqlQuery,primaryKey,null))
		{
			JDBCDelete sql = new JDBCDelete(c);
			sql.deleteMachinery(primaryKey);
		}
		else
		{
			System.out.println("\n The Machinery does not exist \n");
		}
	}

	
	//Charo
	
	public void deleteInstrument(int primaryKeyInstrument)
	{
		
		String sqlQuery = "SELECT * FROM instrument WHERE instrumentID = ?";
		if(valExist(sqlQuery,primaryKeyInstrument,null))
		{
			JDBCDelete sqlDelete = new JDBCDelete(c);
			
			sqlDelete.deleteHospital(primaryKeyInstrument);
			
			
			// We also delete the relation with the order in which it is contained
			//We also need to delete the relation between the order just deleted and the hospital which orders it
			
			// hago el metodo que me de la pk de los orders relacionados con ese instrument, elimino la relacion intrument-order
			
		}
		else
		{
			System.out.println("\n The instrument does not exist \n");
		}
		
	}
	
	public void deleteWarehouse(int primaryKeyWarehouse)
	{
		
		String sqlQuery = "SELECT * FROM instrument WHERE instrumentID = ?";
		if(valExist(sqlQuery,primaryKeyWarehouse,null))
		{
			JDBCDelete sqlDelete = new JDBCDelete(c);
			
			sqlDelete.deleteHospital(primaryKeyWarehouse);
			
		}
		else
		{
			System.out.println("\n The warehouse does not exist \n");
		}
		
	}
	
	//Alex
	public void deleteCompany(int primaryKey){
		String sqlQuery = "SELECT * FROM company WHERE company_ID == ?";
		if(valExist(sqlQuery,primaryKey,null))
		{
			JDBCDelete sqlDelete = new JDBCDelete(c);
			sqlDelete.deleteCompany(primaryKey);

		}
		else
		{
			System.out.println("\n The company does not exist \n");
		}
	}

	public void deleteMaterial(int primaryKey){
		String sqlQuery = "SELECT * FROM material WHERE material_ID == ?";
		if(valExist(sqlQuery,primaryKey,null))
		{
			JDBCDelete sqlDelete = new JDBCDelete(c);
			sqlDelete.deleteMaterial(primaryKey);

		}
		else
		{
			System.out.println("\n The material does not exist \n");
		}
	}

	
	
	//Method to Select
	public ArrayList<Hospital> selectHospitals()
	{
		ArrayList<Hospital> hosp = new ArrayList<Hospital>();
		JDBCSelect sqlSelect = new JDBCSelect(c);
		
		
		hosp = sqlSelect.selectAllHospitals();
		
		
		return hosp;
	}
 	public Hospital selectHospital(int primaryKey)
	{
		String table = "hosital";
		String selQuery = "SELECT name FROM "+table+" WHERE hospital_ID = ?";
		
		
		if(valExist(selQuery,primaryKey,null))
		{
			JDBCSelect sqlSelect = new JDBCSelect(c);
	 		Hospital hosp = new Hospital();
	 		
			
			hosp = sqlSelect.selectHospital(selQuery,primaryKey);
		
			
			return hosp;
		}else
		{
			System.out.println("/nWe dont find the primary key/n");
			
			return null;
		}
	}
 	public Hospital selectHospital(String nameHospital)
 	{
		String selQuarry = "SELECT location FROM hospital WHERE name LIKE ?";
		
		if(valExist(selQuarry,-1,nameHospital))
		{
			JDBCSelect sqlSelect = new JDBCSelect(c);
	 		Hospital hosp = new Hospital();
	 		
			
			hosp = sqlSelect.selectHospital(selQuarry,nameHospital);
			
			
			return hosp;
		}else
		{
			System.out.println("/nWe dont find the primary key/n");
			
			return null;
		}
 		
 	}
 	
 	public ArrayList<Order> selectAllOrders()
 	{
 		ArrayList<Order> orderList = new ArrayList<Order>();
 		JDBCSelect sqlSelect = new JDBCSelect(c);
 		
 		
		orderList = sqlSelect.selectAllOrders();
		
 		return orderList;
 	}
 	public Order selectOrder(int primaryKey)
	{
		String selQuery = "SELECT * FROM orders WHERE order_ID = ?";
		
		if(valExist(selQuery,primaryKey,null))
		{
			JDBCSelect sqlSelect = new JDBCSelect(c);
	 		Order order = new Order();
	 		
	
			order= sqlSelect.selectOrder(selQuery,primaryKey);
			
			
			return order;
		}else
		{
			System.out.println("/nWe dont find the primary key/n");
			return null;
		}
	}
 	//Alex
 	
 	//tengo que hacerlo tambien con String?
 	public ArrayList<Company> selectAllCompanies(){
 		ArrayList<Company> companiesList = new ArrayList<Company>();
 		JDBCSelect sqlSelect = new JDBCSelect(c);
 		
		companiesList = sqlSelect.selectAllCompanies();
		
 		return companiesList;
 	}

 	public Company selectCompany(int primaryKey){
 		String selQuery = "SELECT * FROM company WHERE company_ID = ?";
		
		if(valExist(selQuery,primaryKey,null))
		{
			JDBCSelect sqlSelect = new JDBCSelect(c);
	 		Company comp = new Company();
	 	
			comp= sqlSelect.selectCompany(selQuery,primaryKey);
			
			return comp;
		}else
		{
			System.out.println("/nWe dont find the primary key/n");
			return null;
		}
 	}

 	public ArrayList<Material> selectAllMaterials(){
 		ArrayList<Material> materialList = new ArrayList<Material>();
 		JDBCSelect sqlSelect = new JDBCSelect(c);
 		
		materialList = sqlSelect.selectAllMaterials();

		
 		return materialList;
 	}
 	public Material selectMaterial(int primaryKey){
 		String selQuery = "SELECT * FROM material WHERE material_ID = ?";
		
		if(valExist(selQuery,primaryKey,null))
		{
			JDBCSelect sqlSelect = new JDBCSelect(c);
	 		Material mat = new Material();
	 		
			mat= sqlSelect.selectMaterial(selQuery,primaryKey);

			
			return mat;
		}else
		{
			System.out.println("/nWe dont find the primary key/n");
			return null;
		}
 	}
 	//Celia
 	//Employee
 	
 	 	public ArrayList<Employee> selectAllEmployees(){
 	 		
 			ArrayList<Employee> emp = new ArrayList<Employee>();
 			JDBCSelect sqlSelect = new JDBCSelect(c);
 			
 			emp = sqlSelect.selectAllEmployee();
 			
 			return emp;
 		}
 	 	public Employee selectEmployee(int primaryKey)
 		{
 			String table = "employee";
 			String selQuarry = "SELECT name FROM "+table+" WHERE employee_ID == ?";
 			
 			
 			if(valExist(selQuarry,primaryKey,null))
 			{
 				JDBCSelect sqlSelect = new JDBCSelect(c);
 		 		Employee emp = new Employee();
 		 		
 				emp = sqlSelect.selectEmployee(selQuarry,primaryKey);
 				
 				return emp;
 			}else
 			{
 				System.out.println("/nWe dont find the primary key/n");
 				
 				return null;
 			}
 		}
 	 	public Employee selectEmployee(String nameEmployee)
 	 	{
 			String selQuarry = "SELECT location FROM employee WHERE name LIKE ?";
 			
 			if(valExist(selQuarry,-1,nameEmployee))
 			{
 				JDBCSelect sqlSelect = new JDBCSelect(c);
 		 		Employee emp = new Employee();
 		 		
 				emp = sqlSelect.selectEmployee(selQuarry,nameEmployee);
 				
 				return emp;
 			}else
 			{
 				System.out.println("/nWe dont find the primary key/n");
 				
 				return null;
 			}
 	 		
 	 	}
 	 	
 	 	
 	 //Machinery
 	 	public ArrayList<Machinery> selectAllMachineries()
 		{
 			ArrayList<Machinery> mach = new ArrayList<Machinery>();
 			JDBCSelect sqlSelect = new JDBCSelect(c);
 			
 			mach = sqlSelect.selectAllMachinery();
 			
 			return mach;
 		}
 	 	public Machinery selectMachinery(int primaryKey)
 		{
 			String table = "machinery";
 			String selQuarry = "SELECT name FROM "+table+" WHERE machinery_ID == ?";
 			
 			
 			if(valExist(selQuarry,primaryKey,null))
 			{
 				JDBCSelect sqlSelect = new JDBCSelect(c);
 				Machinery mach = new Machinery();
 		 		
 				mach = sqlSelect.selectMachinery(selQuarry,primaryKey);
 				
 				return mach;
 			}else
 			{
 				System.out.println("/nWe dont find the primary key/n");
 				
 				return null;
 			}
 		}
 	 	public Machinery selectMachinery(String nameMachinery)
 	 	{
 			String selQuarry = "SELECT location FROM machinery WHERE name LIKE ?";
 			
 			if(valExist(selQuarry,-1,nameMachinery))
 			{
 				JDBCSelect sqlSelect = new JDBCSelect(c);
 				Machinery mach = new Machinery();
 		 		
 				mach = sqlSelect.selectMachinery(selQuarry,nameMachinery);
 				
 				return mach;
 			}else
 			{
 				System.out.println("/nWe dont find the primary key/n");
 				
 				return null;
 			}
 	 		
 	 	}

 	 	
 	 	public Instrument selectInstrument(int primaryKey)
 		{
 			String table = "instrument";
 			String selQuarry = "SELECT name FROM "+table+" WHERE instrumentID == ?";
 			
 			
 			if(valExist(selQuarry,primaryKey,null))
 			{
 				JDBCSelect sqlSelect = new JDBCSelect(c);
 		 		Instrument instrument = new Instrument();
 		 		
 				
 				instrument = sqlSelect.selectInstrument(selQuarry,primaryKey);
 				
 				
 				return instrument;
 			}else
 			{
 				System.out.println("/nWe dont find the primary key/n");
 				
 				return null;
 			}
 		}

 	 	public ArrayList<Instrument> selectAllInstruments()
 	 	{
 	 		ArrayList<Instrument> instrumentList = new ArrayList<Instrument>();
 	 		JDBCSelect sqlSelect = new JDBCSelect(c);
 	 		
 	 	
 	 		instrumentList = sqlSelect.selectAllInstruments();
 			
 			
 	 		return instrumentList;
 	 	}
 	 	
 	 	
 	 	public Warehouse selectWarehouse(int primaryKey)
 		{
 			String table = "warehouse";
 			String selQuarry = "SELECT name FROM "+table+" WHERE warehouseID == ?";
 			
 			
 			if(valExist(selQuarry,primaryKey,null))
 			{
 				JDBCSelect sqlSelect = new JDBCSelect(c);
 				Warehouse warehouse = new Warehouse();
 		 		
 				
 				warehouse = sqlSelect.selectWarehouse(selQuarry,primaryKey);
 			
 				
 				return warehouse;
 			}else
 			{
 				System.out.println("/nWe dont find the primary key/n");
 				
 				return null;
 			}
 		}
 	 	
 	
 	// Update
 	
 	//Pablo
 	public void updateHospital(String colChange,String stringChange,int intChange,String colSearch,int pkSearch)
 	{
 		String table = "hospital";
 		String selQuery = "SELECT name FROM "+table+" WHERE hospital_ID = ?";
 		if(valExist(selQuery,pkSearch,null))
 		{
 			
 			JDBCUpdate sqlUpdate= new JDBCUpdate(c);
 			sqlUpdate.update(table,colChange,stringChange,intChange,colSearch,pkSearch);
 			
 			
 		}
 		
 	}
 	public void updateOrder(String colChange,String stringChange,int intChange,String colSearch,int pkSearch)
 	{
 		String table = "orders";
 		String selQuery = "SELECT * FROM orders WHERE order_ID = ?";
 		if(valExist(selQuery,pkSearch,null))
 		{
 		
 			JDBCUpdate sqlUpdate= new JDBCUpdate(c);
 			sqlUpdate.update(table,colChange,stringChange,intChange,colSearch,pkSearch);
 		
 			
 		}
 		
 	}
 	
 	//Charo
 	
 	//Celia
 	
 	//Alex
 	public void updateMaterial(String colChange,String stringChange,int intChange,String colSearch,int pkSearch)
 	{
 		String table = "material";
 		String selQuery = "SELECT name FROM "+table+" WHERE material_ID = ?";
 		if(valExist(selQuery,pkSearch,null))
 		{
 			
 			JDBCUpdate sqlUpdate= new JDBCUpdate(c);
 			sqlUpdate.update(table,colChange,stringChange,intChange,colSearch,pkSearch);
 			
 			
 		}
 		
 	}
 	public void updateCompany(String colChange,String stringChange,int intChange,String colSearch,int pkSearch)
 	{
 		String table = "company";
 		String selQuery = "SELECT * FROM o WHERE company_ID = ?";
 		if(valExist(selQuery,pkSearch,null))
 		{
 		
 			JDBCUpdate sqlUpdate= new JDBCUpdate(c);
 			sqlUpdate.update(table,colChange,stringChange,intChange,colSearch,pkSearch);
 		
 			
 		}
 		
 	}
 	
	//Charo

 	
 	//DB management Methods
	
	private Date dateConverterSQL(String a,String b,String c) 
	{
		java.sql.Date finalDate = null;
		try
		{
			String input = "/"+a+"/"+b+"/"+c+"";
			
			java.sql.Date apptDay = null;
		    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		    
		    apptDay = (Date) df.parse(input);
		    
		    finalDate = new Date(apptDay.getTime());
		}catch(ParseException e)
		{
			e.printStackTrace();
			System.out.println("Error in conversion");
		}
		
	    
		return finalDate;
	}
	
	public boolean valExist (String query, int pkInt, String pkString)
	{
		
		boolean a;
		
		 JDBCSearch sel = new JDBCSearch(c);
		
		
		if(pkString == null)
		{
			a = sel.valPKInt(query,pkInt);
		}
		else
		{
			a = sel.valPKString(query,pkString);
		}
		
	
		return a;
		
	}
		
	//Relations
	public void setRelationHospitalOrder(int hosp, int order, int tao)
	{
		
		JDBCInsert sqlInsert = new JDBCInsert(c);
		sqlInsert.insertHospitalOrderRelation( hosp, order, tao);
		
	}
	
	public void deleteRelationHospitalOrder(int pkCol,String colPk)
	{
		String table = "hospital_orders";
	
		JDBCDelete sqlDelete = new JDBCDelete(c);
		sqlDelete.deleteRelationNtoN( table, colPk, pkCol);
		
	}
	
	
	
	public Hospital setHospitalRelations(Hospital hosp)
	{
		String relationalTable = "hospital_orders";
		String pk1AtrivuteSearch = "order_ID";
		String pkAtrivuteCompere = "hospital_ID";
		int pkValueCompere = hosp.getHospitalID();
		
		
		ArrayList<Integer> orderPkRelationFound = new ArrayList<Integer>();
		orderPkRelationFound = foundRelation(relationalTable,pk1AtrivuteSearch,pkAtrivuteCompere,pkValueCompere);
		Iterator<Integer> iter = orderPkRelationFound.iterator();
		while(iter.hasNext())
		{
			int i = iter.next();
			hosp.addOrder(selectOrder(i));
		}
		return hosp;
	}
	
	public Order setOrderRelations(Order ord)
	{
	//Hacer create the tabla asociada instruments/order	
		String pkAtrivuteCompere = "order_ID";
		int pkValueCompere = ord.getOrderID();
		
		//Hospital List
		String relationalTable = "hospital_orders";
		String pk1AtrivuteSearch = "hospital_ID";
		
		ArrayList<Integer> hospitalPkRelationFound = new ArrayList<Integer>();
		hospitalPkRelationFound = foundRelation(relationalTable,pk1AtrivuteSearch,pkAtrivuteCompere,pkValueCompere);
		
		Iterator<Integer> iter = hospitalPkRelationFound.iterator();
		while(iter.hasNext())
		{
			int i = iter.next();
			ord.addHospital(selectHospital(i));
		}
		
		//Instrument List
		
		relationalTable = "instrument_orders";
		pk1AtrivuteSearch = "instrument_ID";
		
		ArrayList<Integer> instrumentPkRelationFound = new ArrayList<Integer>();
		instrumentPkRelationFound = foundRelation(relationalTable,pk1AtrivuteSearch,pkAtrivuteCompere,pkValueCompere);
		Iterator<Integer> iter2 = instrumentPkRelationFound.iterator();
		while(iter2.hasNext())
		{
			int i = iter2.next();
			ord.addInstrument(selectInstrument(i));
		}
	return ord;
	}
		
	public Instrument setInstrumentRelations(Instrument inst){
		
		
		//relation instrument-orders
		String relationalTable = "instrument_orders";
		String pk1AttSearchOrder = "order_ID";
		
		String pkAttCompare = "instrument_ID";
		int pkValueCompare = inst.getInstrumentID();
		
		ArrayList<Integer> instPkRelationFound = new ArrayList<Integer>();
		instPkRelationFound = foundRelation(relationalTable, pk1AttSearchOrder, pkAttCompare, pkValueCompare);
		Iterator<Integer> iter = instPkRelationFound.iterator();
		while(iter.hasNext()){
			int i = iter.next();
			inst.addOrder(selectOrder(i));
		}
		
		//relation instrument-machinery
		relationalTable = "instrument_machinery";
		String pkAttSearchMach = "machinery_ID";
		
		instPkRelationFound = foundRelation(relationalTable, pkAttSearchMach, pkAttCompare, pkValueCompare);
		iter = instPkRelationFound.iterator();
		while(iter.hasNext()){
			int i = iter.next();
			inst.addMachinery(selectMachinery(i));
		}
		
		//relation instrument-warehouse
		
		inst.addWarehouse(selectWarehouse(1));
		
		return inst;		
	}

	public Material setMaterialRelations(Material mat){
		//relation material-company
				String relationalTable = "instrument_orders";
				String pk1AttSearchOrder = "order_ID";
				
				String pkAttCompare = "instrument_ID";
				int pkValueCompare = inst.getInstrumentID();
				
				ArrayList<Integer> instPkRelationFound = new ArrayList<Integer>();
				instPkRelationFound = foundRelation(relationalTable, pk1AttSearchOrder, pkAttCompare, pkValueCompare);
				Iterator<Integer> iter = instPkRelationFound.iterator();
				while(iter.hasNext()){
					int i = iter.next();
					inst.addOrder(selectOrder(i));
				}
				
				//relation instrument-machinery
				//relation material-company
				relationalTable = "Company";
				String pkAttSearchMach = "material_ID";
				
				instPkRelationFound = foundRelation(relationalTable, pkAttSearchMach, pkAttCompare, pkValueCompare);
				iter = instPkRelationFound.iterator();
				while(iter.hasNext()){
					int i = iter.next();
					inst.addMachinery(selectMachinery(i));
				}
				
				//relation instrument-warehouse
				
				inst.addWarehouse(selectWarehouse(1));
				
				return inst;		
			

	}
	
	//Relation Help Methods
	public ArrayList<Integer> foundRelation(String table,String pk1AttributeSearch,String pkAttributeCompere ,int pkValueCompere)
	{
		String query = "SELECT "+pk1AttributeSearch+" FROM "+table+" WHERE "+pkAttributeCompere+" = ?";
		
		ArrayList<Integer> pkArray = new ArrayList<Integer>();
		
		JDBCSearch sqlSearch = new JDBCSearch(c);
		
		pkArray=sqlSearch.searchPkRelation(query, pkValueCompere, pk1AttributeSearch);
		
		return pkArray;
		
	}
	
	public boolean sharedRelation(String table,String pk1AtrivuteSearch,String pkAtrivuteCompere ,Integer pkValueCompere)
	{
		boolean a = false;
		ArrayList<Integer> b = foundRelation(table,pk1AtrivuteSearch,pkAtrivuteCompere,pkValueCompere);
		
		if(b.size()<=1)
		{
			a = false;
		}else
		{
			a = true;
		}
		return a;
	}
	
	
}