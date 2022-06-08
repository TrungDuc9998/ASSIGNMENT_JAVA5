package ASSIGNMENT_JAVA5.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Account user;
	
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name="address")
	private String address;
	
	@Column(name="total")
	private double total;
	
	@Column(name="status")
	private int status;
	
	@Column(name="fullname")
	private String fullname;
	
	@Column(name="phone_number")
	private String PhoneNumber;
	
	@OneToMany(mappedBy = "order")
	private List<OrderDetail>orderDetails;
	
	
	
	
}
