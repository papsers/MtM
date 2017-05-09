package mtm.db.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCCreate 
{
	private Connection c;
	
	public JDBCCreate(Connection c)
	{
		this.c = c;
	}
	////
	//Create
	public void createTables()
	{
		try
		{
			c.setAutoCommit(false);
			//Pablo
			// fPt -->firstPablotable , sqlp1 --> sqlpablo1//
			Statement fPt = c.createStatement();
			String sqlp1 = "CREATE TABLE hospital("
				+ "hospital_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "name TEXT NOT NULL,"
				+ "location TEXT NOT NULL,"
				+ "medical_specialization TEXT NOT NULL)";
			fPt.executeUpdate(sqlp1);
			fPt.close();
				Statement sPt = c.createStatement();
			String sqlp2 = "CREATE TABLE orders("
				+ "order_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "total_amount_instruments INTEGER NOT NULL,"
				+ "order_date DATE NOT NULL,"
				+ "delivery_date DATE NOT NULL)";
			sPt.executeUpdate(sqlp2);
			sPt.close();
			
			Statement tPt = c.createStatement();
			String sqlp3 = "CREATE TABLE hospital_orders("
					+ "hospital_ID INTEGER REFERENCES hospital(hospital_ID),"
					+ "order_ID INTEGER REFERENCES orders(order_ID),"
					+ "PRIMARY KEY(hospital_ID,order_ID))";
			tPt.executeUpdate(sqlp3);
			tPt.close();
			
			Statement fPt2 = c.createStatement();
			String sqlp4 = "CREATE TABLE instrument_orders("
					+ "order_ID INTEGER REFERENCES orders(order_ID),"
					+ "instrument_ID INTEGER REFERENCES instrument(instrument_ID),"
					+ "amount_order INTEGER,"
					+ "PRIMARY KEY(order_ID,instrument_ID))";
			fPt2.executeUpdate(sqlp4);
			fPt2.close();
			
			//Alex
			// fAt -->firstAlextable // sqla1 --> sqlalex1
			Statement fAt = c.createStatement();
			String sqla1 = "CREATE TABLE company ("
					+ "company_ID INTEGER PRIMARY KEY,"
					+ "location TEXT,"
					+"company_name TEXT);";
			fAt.executeUpdate(sqla1);
			fAt.close();
			
			
			
			
			//Charo
			// fCht -->firstCharotable //sqlch1 --> sqlcharo1
			Statement fCht = c.createStatement();
			String sqlch1 = "CREATE TABLE instrument("
					+ "instrument_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL,"
					+ "model TEXT NOT NULL,"
					+ "purpose TEXT NOT NULL,"
					+ "amount INTEGER NOT NULL,"
					+ "number_uses INTEGER NOT NULL,"
					+ "body_location TEXT NOT NULL,"
					+ "price INTEGER NOT NULL,"
					+ "warehouse_location INTEGER REFERENCES warehouse (warehouse_ID))";
			fCht.executeUpdate(sqlch1);
			fCht.close();
			
			Statement rCht = c.createStatement();
			String sqlch3 = "CREATE TABLE instrument_machinery("
					+ "instrument_ID INTEGER NOT NULL REFERENCES instrument(instrument_ID),"
					+ "machinery_ID INTEGER NOT NULL REFERENCES machinery(machinery_ID),"
					+ "timeofMade INTEGER,"
					+ "PRIMARY KEY (instrument_ID,machinery_ID))";
			rCht.executeUpdate(sqlch3);
			rCht.close();
			
			Statement sCht = c.createStatement();
			String sqlch2 = "CREATE TABLE warehouse("
					+ "warehouse_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "warehouse_location TEXT NOT NULL,"
					+ "capacity INTEGER NOT NULL,"
					+ "filled_space INTEGER NOT NULL)";
			sCht.executeUpdate(sqlch2);
			sCht.close();
			
			//Celia
			Statement fCt = c.createStatement();
			String sqlc1= "CREATE TABLE employee("
					+ "employee_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL,"
					+ "typeofContract TEXT NOT NULL,"
					+ "specializationType TEXT NOT NULL,"
					+ "machineryType TEXT,"
					+ "FOREIGN KEY (machineryType) REFERENCES machinery(machineryType) )";
			fCt.executeUpdate(sqlc1);
			fCt.close();
				
			Statement sCt = c.createStatement();
				String sqlc2= "CREATE TABLE machinery("
				+ "machinery_ID INTEGER PRIMARY KEY AUTOINCREMENT,"							
				+ "machineryType TEXT,"
				+ "stateofMachinery TEXT NOT NULL,"
				+ "dateofInstallation DATE,"
				+ "sizeofMachinery INTEGER)";
					sCt.executeUpdate(sqlc2);
				sCt.close();	
				
				
			//Alex
				Statement sAt = c.createStatement();
				String sqla2 =  "CREATE TABLE material ("
								+"material_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
								+"weight INTEGER,"
								+"volume INTEGER,"
								+"type TEXT,"
								+"company_ID  TEXT REFERENCES company(company_ID),"
								+"machinery_ID TEXT REFERENCES machinery(machinery_ID),"
								+"warehouse_ID TEXT REFERENCES warehouse(warehouse_ID))";
				sAt.executeUpdate(sqla2);
				sAt.close();
			
			c.commit();
			
			
			
		}catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Conection Error, Ask Rodrigo for Help");
		
		}
		
	}
	
			//Pablo
	public void createTableHospital()
	{
		try
		{
			Statement fPt = c.createStatement();
			String sqlp1 = "CREATE TABLE hospital("
				+ "hospital_ID    INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "name TEXT NOT NULL,"
				+ "location TEXT NOT NULL,"
				+ "medical_specialization TEXT NOT NULL)";
				fPt.executeUpdate(sqlp1);
			fPt.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	
	}
	public void createTableOrder()
	{
		try
		{
			Statement sPt = c.createStatement();
			String sqlp2 = "CREATE TABLE orders("
				+ "order_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "total_amount_instruments INTEGER NOT NULL,"
				+ "order_date DATE NOT NULL,"
				+ "delivery_date DATE NOT NULL)";
			sPt.executeUpdate(sqlp2);
			sPt.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
		//Alex
	public void createTableMaterial(){
	try
	{
		Statement sAt = c.createStatement();
		String sqla2 =  "CREATE TABLE material("
						+"material_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
						+"weight INTEGER,"
						+"volume INTEGER,"
						+"type TEXT,"
						+"company_ID  TEXT REFERENCES company(company_ID),"
						+"machinery_ID TEXT REFERENCES machinery(machinery_ID),"
						+"warehouse_ID TEXT REFERENCES warehouse(warehouse_ID))";
		sAt.executeUpdate(sqla2);
		sAt.close();
		}catch (Exception e)
	{
			e.printStackTrace();
			System.out.println("Conection Error, Ask Rodrigo for Help");
		
		}
	}	
	public void createTableCompany(){
		try
		{
			Statement fAt = c.createStatement();
			String sqla1 = "CREATE TABLE company ("
					+ "company_ID INTEGER PRIMARY KEY,"
					+ "location TEXT,"
					+"company_name TEXT);";
			fAt.executeUpdate(sqla1);
			fAt.close();
				
			}catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("Conection Error, Ask Rodrigo for Help");
			
			}
		}
		
		//Charo
	
	public void createTableInstrument()
	{
		try
		{
			Statement fCht = c.createStatement();
			String sqlch1 = "CREATE TABLE instrument("
					+ "instrument_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL,"
					+ "model TEXT NOT NULL,"
					+ "purpose TEXT NOT NULL,"
					+ "amount INTEGER NOT NULL,"
					+ "number_uses INTEGER NOT NULL,"
					+ "body_location TEXT NOT NULL,"
					+ "price INTEGER NOT NULL,"
					+ "warehouse_ID INTEGER REFERENCES warehouse (warehouse_ID))";
			fCht.executeUpdate(sqlch1);
			fCht.close();
			
		}catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Conection Error, Ask Rodrigo for Help");
		
		}
	}	
	public void createTableWarehouse()
	{
		try
		{
			Statement sCht = c.createStatement();
			String sqlch2 = "CREATE TABLE warehouse("
					+"warehouse_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "warehouse_location TEXT NOT NULL,"
					+ "capacity INTEGER NOT NULL,"
					+ "filledSpace INTEGER NOT NULL)";
			sCht.executeUpdate(sqlch2);
			sCht.close();
		
		}catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Conection Error, Ask Rodrigo for Help");
		
		}
	}
		
			//Celia
	public void createTableEmployee()
	{
		try
		{
			Statement fCt = c.createStatement();
			String sqlc1 =  "CREATE TABLE employee("
					+ "employee_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL,"
					+ "typeofContract TEXT NOT NULL,"
					+ "specializationType TEXT NOT NULL,"
					+ "machineryType TEXT,"
					+ "FOREIGN KEY (machineryType) REFERENCES machinery(machineryType) )";
			fCt.executeUpdate(sqlc1);
			fCt.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
			
	}
	public void createTableMachinery()
	{
		try
		{
		Statement sCt = c.createStatement();
			String sqlc2 = "CREATE TABLE machinery("
				+ "machineryType TEXT PRIMARY KEY,"
				+ "stateofMachinery TEXT NOT NULL,"
				+ "dateofInstallation DATE,"
				+ "sizeofMachinery INTEGER)";
			sCt.executeUpdate(sqlc2);
			sCt.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
			
			//Relational Tables 
		
	public void createTableHospitalOrders()
	{
		try
		{
			Statement tPt = c.createStatement();
			String sqlp3 = "CREATE TABLE hospital_orders("
					+ "hospital_ID INTEGER REFERENCES hospital(hospital_ID),"
					+ "order_ID INTEGER REFERENCES orders(order_ID),"
					+ "PRIMARY KEY(hospital_ID,order_ID))";
			tPt.executeUpdate(sqlp3);
			tPt.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
		
	public void createTableInstrumentOrders()
	{
		try
		{
			Statement fPt = c.createStatement();
			String sqlp4 = "CREATE TABLE instrument_orders("
					+ "order_ID INTEGER REFERENCES orders(order_ID),"
					+ "instrument_ID INTEGER REFERENCES instrument(instrument_ID),"
					+ "amount_order INTEGER,"
					+ "PRIMARY KEY(order_ID,instrument_ID))";
			fPt.executeUpdate(sqlp4);
			fPt.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
		
			
}
