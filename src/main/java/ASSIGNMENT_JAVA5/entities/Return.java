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
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="returns")
@Data
public class Return {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="note")
	private String note;
	
	
	@Column(name="image")
	private String image;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
}
