package mtm.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "hospital")
@XmlAccessorType(XmlAccessType.FIELD) //Be able to use XML
@XmlRootElement(name = "Hospital")
@XmlType(propOrder = { "hospitalID", "name", "location", "medicalSpecialization", "orderList"})//Set the order of the attributes in the XML
public class Hospital implements Serializable
{

	private static final long serialVersionUID = -5262203984422829331L;

	@Id 
	@GeneratedValue(generator="hospital")
	@TableGenerator(name="hospital", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq", pkColumnValue="hospital")
	
	@XmlAttribute
	private int hospitalID; 
	@XmlAttribute
	private String name;
	@XmlElement
	private String location;
	@XmlElement
	private String medicalSpecialization;
	 
	@ManyToMany
	@JoinTable(name="hospital_orders",
	joinColumns={@JoinColumn(name="hospitalID", referencedColumnName="hospitalID")}, //points to my class
    inverseJoinColumns={@JoinColumn(name="orderID", referencedColumnName="orderID")}) //points to the class in the next line
	
	@XmlElement(name = "Order")//This name doesnt make reference to the attribute element.
								// Name the employees tag(next attribute) as Order
	@XmlElementWrapper(name = "Orders")//make an Orders tag that stores Order
	
	private List<Order> orderList;
	//Primary Key --> hospitalID
	//Foreign Key --> orderList
	
	//Gets and Sets
	
	public int getHospitalID() 
	{
		return hospitalID;
	}
	public void setHospitalID(Integer hospitalID) {
		this.hospitalID = hospitalID;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getLocation()
	{
		return location;
	}
	public void setLocation(String location)
	{
		this.location = location;
	}

	public String getMedicalSpecialization()
	{
		return medicalSpecialization;
	}
	public void setMedicalSpecialization(String medicalSpecialization)
	{
		this.medicalSpecialization = medicalSpecialization;
	}
	
	public List<Order> getOrderList()
	{
		return orderList;
	}
	public void setOrderList(List<Order> orderList)
	{
		this.orderList = orderList;
	}
	
	// Constructors
	
	
	public Hospital()
	{
		super();
		this.orderList = new ArrayList<Order>();
	}

	public Hospital(String name, String location, String medical_specialization)
	{
		super();
		this.name = name;
		this.location = location;
		this.medicalSpecialization = medical_specialization;
		
		this.orderList = new ArrayList<Order>();
	}
	public Hospital(Integer hospitalID, String name, String location, String medicalSpecialization)
	{
		super();
		this.hospitalID = hospitalID;
		this.name = name;
		this.location = location;
		this.medicalSpecialization = medicalSpecialization;
		
		this.orderList = new ArrayList<Order>();
	}
	public Hospital(Integer hospitalID, String name, String location, String medicalSpecialization, List<Order> orderList)
	{
		super();
		this.hospitalID = hospitalID;
		this.name = name;
		this.location = location;
		this.medicalSpecialization = medicalSpecialization;
		
		this.orderList = orderList;
	}
	// Methods
	
	public void addOrder (Order order)
	{
		if(!orderList.contains(order))
		{
			this.orderList.add(order);
		}
	}
	
	public void removeOrder (Order order)
	{
		if(orderList.contains(order))
		{
			this.orderList.remove(order);
		}
	}
	
	public void printHospital(boolean relat)
	{
		System.out.printf("Hospital Information:\n"
				+ "Id: %d\n"
				+ "Name: %s\n"
				+ "Location: %s\n"
				+ "Medical Specialization:%s\n\n",this.getHospitalID(),this.getName(),this.getLocation(),this.getMedicalSpecialization());
		
		if(relat)
		{
			//Relations
			Iterator <Order> iter = this.getOrderList().iterator();
			while(iter.hasNext())
			{
				Order ord = iter.next();
				System.out.printf("Orders Related:\n"
						+ "Id:%d\n"
						+ "Total Amount Instruments:%d\n\n",ord.getOrderID(),ord.getTotalAmountInstruments());
			}
			
		}
		
	}
	
	@Override
	public String toString() 
	{
		return "Hospital [hospitalID=" + hospitalID + ", name=" + name + ", location=" + location
				+ ", medicalSpecialization=" + medicalSpecialization + "]";
	}
	
	
	//hashCode and  equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hospitalID;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hospital other = (Hospital) obj;
		if (hospitalID != other.hospitalID)
			return false;
		return true;
	}
	
	
}
