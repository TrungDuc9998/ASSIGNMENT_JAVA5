package ASSIGNMENT_JAVA5.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ASSIGNMENT_JAVA5.entities.Account;
import ASSIGNMENT_JAVA5.entities.Card;
import ASSIGNMENT_JAVA5.entities.Order;
import ASSIGNMENT_JAVA5.entities.OrderDetail;
import ASSIGNMENT_JAVA5.entities.ProductModel;
import ASSIGNMENT_JAVA5.entities.Product;
import ASSIGNMENT_JAVA5.repositories.CartRepository;
import ASSIGNMENT_JAVA5.repositories.OrderDetailRepository;
import ASSIGNMENT_JAVA5.repositories.OrderRepository;
import ASSIGNMENT_JAVA5.repositories.ProductRepository;

@Controller
public class CardController {
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private ProductRepository proRepo;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
	@GetMapping("cartManagement")
	private String cartManagement(Model model) {
		List<Order> listOrder=this.orderRepo.findAll();
		model.addAttribute("listOrder", listOrder);
		model.addAttribute("views", "/views/admin/cart/cartManagement.jsp");
		return "index";
	}
	
	@GetMapping("detail/{id}")
	private String detail(Model model,@PathVariable("id")Integer id) {
		System.out.println("----------------idOrder:"+id);
		List<OrderDetail>list=this.orderDetailRepo.FindListOrderDetailByOrderId(id,0);
		for (OrderDetail orderDetail : list) {
			System.out.println(orderDetail.getId());
		}
		model.addAttribute("list", list);
		//chi tiet don hang admin
		return "forward:/cartManagement/";
	}
	
	@GetMapping("detailAccount/{id}")
	private String detailAccount(Model model,@PathVariable("id")Integer id) {
		System.out.println(request.getRequestURL());
		System.out.println(request.getRequestURI());
		Order order=this.orderRepo.getOne(id);
		//chi tiet sp dh
		
		List<OrderDetail>list=this.orderDetailRepo.FindListOrderDetailByOrderId(id);
		for (OrderDetail orderDetail : list) {
			System.out.println(orderDetail.getId());
		}
		model.addAttribute("list", list);
		if(order.getStatus()==0) {
			return "forward:/orderedProduct";
		}if(order.getStatus()==1) {
			return "forward:/orderedProduct1";
		}if(order.getStatus()==2) {
			return "forward:/orderedProduct2";
		}if(order.getStatus()==3) {
			return "forward:/orderedProduct3";
		}if(order.getStatus()==4) {
			return "forward:/orderedProduct4";
		}else{
			return "forward:/orderedProduct5";
		}

		
		
	}
	
	@PostMapping("store/cart/{id}/{price}")
	private String createCart(
			@PathVariable("id")Integer id,
			@PathVariable("price")Double price,			
			@RequestParam("quantity")Integer quantity,
			Card cart
			) {
		HttpSession session=request.getSession();
		Account acc=(Account)session.getAttribute("account");
		if(acc!=null) {
			List<Card>listCart=this.cartRepo.findAll();
			Product pro=this.proRepo.getOne(id);
			boolean kt=true;
			for (Card card : listCart) {
				if(card.getProduct().getId()==id) {
					
					System.out.println("----------- sản phẩm trùng trong giỏ hàng ---------");
					cart.setId(card.getId());
					cart.setPrice(price*(quantity+card.getQuantity()));
					cart.setQuantity(quantity+card.getQuantity());
					cart.setAccount(acc);
					cart.setProduct(pro);
					this.cartRepo.save(cart);
					kt=false;
					break;
				}
			}
			
			if(kt!=false) {
				cart.setPrice(price);
				cart.setQuantity(quantity);
				cart.setAccount(acc);
				cart.setProduct(pro);
				this.cartRepo.save(cart);
			}
			
		}else {
			System.out.println("chưa đăng nhập lấy đâu account");
		}

		
		
		
		return "redirect:/cart";
	}
	
	@GetMapping("cart")
	public String cart(Model model) {
		HttpSession session=request.getSession();
		Account acc=(Account)session.getAttribute("account");
		if(acc!=null) {
			List<Card> Listcart=this.cartRepo.findAllCartByAccountId(acc.getId());
			model.addAttribute("listCart", Listcart);
		}
		
		
		model.addAttribute("views", "/views/cart.jsp");
		return "index";
	}
	
	@GetMapping("deleteCart/{id}")
	public String deleteCart(@PathVariable("id")Card cart) {
		this.cartRepo.delete(cart);
		 return "forward:/cart";
	}
}
