package ASSIGNMENT_JAVA5.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotBlank(message="lỗi")
	@Column(name="username")
	private String username;
	
	@NotBlank(message="lỗi")
	@Column(name="password")
	private String password;
	
	@NotBlank(message="lỗi")
	@Column(name="fullname")
	private String fullname;

//	@NotBlank
//	@Email
	@Column(name="email")
	private String email;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="address")
	private String address;

	@Column(name="photo")
	private String photo;

	@Column(name="activated")
	private int activated;

//	@NotNull
	@Column(name="admin")
	private int admin;
	
	@OneToMany(mappedBy = "account")
	private List<Category>categores;
	
	@OneToMany(mappedBy="user")
	private List<Order>orders;
	
	@OneToMany(mappedBy = "account")
	private List<Card>cards;
	
	@OneToMany(mappedBy = "account")
	private List<Favorite>favorites;
	
	@OneToMany(mappedBy = "account")
	private List<Comment>comments;
	
}
