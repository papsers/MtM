package mtm.db.jdbc;

import mtm.db.pojos.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInsert 
{	
	public void insert(Hospital hosp,Connection c) 
	{
		try
		{
			Statement stm = c.createStatement();
			
			//////////////////////////////////////////////////////////////////////
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
	}
	public void insert(Order ord,Connection c)
	{
		try
		{
			Statement stm = c.createStatement();
			
			//////////////////////////////////////////////////////////////////////
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void insert(Company com, Connection c){
		try{

						Statement stmt = c.createStatement();
						String sql;
						sql = "INSERT INTO company(resource,location,company_name) VALUES ('"+com.getResource()+"','"+com.getLocation()+",'"+com.getCompany_name()+"')"; 
						stmt.executeUpdate(sql);					
						stmt.close();
						// End of transaction
						c.commit();
						System.out.println("Records inserted.");
						// Insert new records: end

						// Close database connection
						c.close();
						System.out.println("Database connection closed.");
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	public void insert(Material mat, Connection c){
		try{

						Statement stmt = c.createStatement();
						String sql;
						sql = "INSERT INTO materials(weight,volume,material_provided,machinery_type) VALUES('"+mat.getWeight()+","+mat.getVolume()+","+mat.getMaterial_provided()+","+mat.getMachinery_type()+"')";
						stmt.executeUpdate(sql);
						stmt.close();
						// End of transaction
						c.commit();
						System.out.println("Records inserted.");
						// Insert new records: end

						// Close database connection
						c.close();
						System.out.println("Database connection closed.");
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
