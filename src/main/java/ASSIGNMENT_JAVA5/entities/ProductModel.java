package ASSIGNMENT_JAVA5.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
	private String fullname;
	private String address;
	private String email;
	private String phone;
	
	private List<Integer> product_id;
	private List<Integer> quantity;
}
