package ASSIGNMENT_JAVA5.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ASSIGNMENT_JAVA5.entities.Account;
import ASSIGNMENT_JAVA5.entities.Category;
import ASSIGNMENT_JAVA5.entities.OrderDetail;
import ASSIGNMENT_JAVA5.entities.Product;
import ASSIGNMENT_JAVA5.repositories.CategoryRepository;
import ASSIGNMENT_JAVA5.repositories.OrderDetailRepository;
import ASSIGNMENT_JAVA5.repositories.ProductRepository;

@Controller
public class HomeController {
	
	@Autowired
	private CategoryRepository cateRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
	@Autowired
	private HttpServletRequest request;
	
	
	
	private void getAttribute(Account acc) {
		HttpSession session=request.getSession();
		 acc=(Account)session.getAttribute("account");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+acc.getFullname());
	}
	
	
	@RequestMapping("home")
	public String home(Model model) {
		
		
	
			List<Category>listCategoey=this.cateRepo.findAll();
			model.addAttribute("listCategory", listCategoey);
			List<Product>listProduct=this.productRepo.findAll();
			model.addAttribute("listProduct", listProduct);
			
			

		
		
		
		model.addAttribute("views", "/views/home.jsp");
		return "index";
	}
	
//	@RequestMapping("invoiceDetails")
//	public String invoiceDetails(Model model) {
//		List<OrderDetail>listOrderDetail=this.orderDetailRepo.FindListOrderDetailByAccountId();
//		model.addAttribute("listOrderDetail", listOrderDetail);
//		 return "invoiceDetails";
//	}
	
	@RequestMapping("findProductByCategoryId/{id}")
	public String findProductByCategoryId(@PathVariable("id")Integer id,Model model) {
		System.out.println("categoryId:"+id);
		List<Product>listProduct=this.productRepo.findByIdPro(id);
		
		List<Category>listCategoey=this.cateRepo.findAll();
		model.addAttribute("listCategory", listCategoey);
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("views", "/views/home.jsp");
		return "index";
	}
	
	
}
