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
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name="products")
@Data
public class Product {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
//	@Column(name="name")
	@NotBlank(message="vui lòng nhập tên sản phẩm")
	private String name;
	
	@Column(name="image")
	private String image;
	
	@Column(name="price")
	private double price;
	
	
	
	@Column(name="description")
	private String description;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="available")
	private int available;
	
	@Column(name="color")
	private String color;
	
	@Column(name="size")
	private String size;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@OneToMany(mappedBy = "product")
	private List<Card>cards;
	
	@OneToMany(mappedBy="product")
	List<OrderDetail>orderDetail;
	
	@OneToMany(mappedBy="product")
	List<Favorite>favorites;
	
	@OneToMany(mappedBy="product")
	List<Comment>comments;
	
	
	
}
