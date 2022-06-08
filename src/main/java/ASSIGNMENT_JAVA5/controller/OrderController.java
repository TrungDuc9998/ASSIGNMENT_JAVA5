package ASSIGNMENT_JAVA5.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ASSIGNMENT_JAVA5.entities.Account;
import ASSIGNMENT_JAVA5.entities.Card;
import ASSIGNMENT_JAVA5.entities.Favorite;
import ASSIGNMENT_JAVA5.entities.Order;
import ASSIGNMENT_JAVA5.entities.OrderDetail;
import ASSIGNMENT_JAVA5.entities.ProductModel;
import ASSIGNMENT_JAVA5.entities.Product;

import ASSIGNMENT_JAVA5.repositories.AccountRepository;
import ASSIGNMENT_JAVA5.repositories.CartRepository;
import ASSIGNMENT_JAVA5.repositories.FavoriteRepository;
import ASSIGNMENT_JAVA5.repositories.OrderDetailRepository;
import ASSIGNMENT_JAVA5.repositories.OrderRepository;
import ASSIGNMENT_JAVA5.repositories.ProductRepository;



@Controller
public class OrderController {

	@Autowired
	private ProductRepository proRepo;

	@Autowired
	private ProductRepository productRepo;
	
	
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private FavoriteRepository favoriteRepo;
	
	@Autowired
	private HttpServletRequest request;
	
	private Order orderLast;
	
	
	@GetMapping("order")
	public String order(Model model,
			
			Order order,OrderDetail orderDetail) {
		
		HttpSession session=request.getSession();
		Account account=(Account)session.getAttribute("account");
		List<Card>listCard=this.cartRepo.findAllCartByAccountId(account.getId());
		double tong=0;
		for (Card list : listCard) {
			tong+=list.getPrice();
			
			model.addAttribute("total", tong);
			System.out.println(list.getProduct().getName());
		}
			
		
		model.addAttribute("listCard", listCard);
		model.addAttribute("account", account);
		
		model.addAttribute("views", "/views/order.jsp");
		return "index";
	}

	

	@RequestMapping("productDetails/{id}")
	public String productDetails(Model model, @PathVariable("id") Integer id) {
		System.out.println("--------------------------" + id);

		model.addAttribute("views", "/views/productDetails.jsp");
		Product product = this.proRepo.getOne(id);
		model.addAttribute("product", product);

		Favorite favorite=this.favoriteRepo.findFavoriteByProductId(product.getId());
		model.addAttribute("favorite", favorite);
		
		Product listPro=this.productRepo.findProductByNameAndSizeAndColor("", "", "");
		model.addAttribute("listColor", listPro);
		
		
		

		return "index";
	}
	
	

	@PostMapping("cardstore")
	public String cardStore(Model model) {
		return "redirect:/card";
	}

	@GetMapping("orderDetails")
	public String orderDetails(Model model,Card cart,
			@RequestParam("name")String name,
			@RequestParam("color")String color,
			@RequestParam("size")String size,
			@RequestParam("quantity")Integer quantity) {
		
		HttpSession session=request.getSession();
		Account account=(Account) session.getAttribute("account");
		
		
		Product pro=this.productRepo.findProductByNameAndSizeAndColor("%"+name+"%", "%"+color+"%", "%"+size+"%");
	
		System.out.println("----------------sản phẩm lấy ra từ product ----------");
		System.out.println(name);
		System.out.println(color);
		System.out.println(size);
		if(pro==null) {
			System.out.println("không tìm thấy sản phẩm");
			session.setAttribute("error", "Thêm sản phẩm không thành công!");
			return "redirect:/order";
		}else {
			
			account=this.accountRepo.getOne(account.getId());
////			
			List<Card>listCard=this.cartRepo.findAllCartByAccountId(account.getId());
			
				if(listCard.isEmpty()) {
					cart.setAccount(account);
					cart.setPrice(pro.getPrice()*quantity);
					cart.setQuantity(quantity);
					cart.setProduct(pro);
					this.cartRepo.save(cart);
					System.out.println("---------------- không thấy -------------");
				}else {
					boolean kt=true;
					for (Card list : listCard) {
						if(list.getProduct().getId()==pro.getId()) {
							cart.setId(list.getId());
							cart.setAccount(account);
							cart.setPrice(pro.getPrice()*(quantity+list.getQuantity()));
							cart.setQuantity(quantity+list.getQuantity());
							cart.setProduct(pro);
							this.cartRepo.save(cart);
							kt=false;
							break;
						}
					}
					
					if(kt!=false) {
						for (Card list : listCard) {
							if(list.getProduct().getId()!=pro.getId()) {
								
								cart.setAccount(account);
								cart.setPrice(pro.getPrice()*quantity);
								cart.setQuantity(quantity);
								cart.setProduct(pro);
								this.cartRepo.save(cart);
								System.out.println("-----------------------------"+list.getProduct().getPrice());
								System.out.println("-----------------------------"+list.getPrice());
								System.out.println("-----------------------------"+pro.getPrice());
								System.out.println("--------  thêm một sản phẩm mới không có trong giỏ hàng --------");
								break;
							}
						}
					}
				}
			System.out.println("sản phẩm đã được thêm vào giỏ hàng");
			return "redirect:/order";
		}
			
		

		
		
		
	}
	
	@RequestMapping("/admin/order")
	public String adminOrder(Model model,@ModelAttribute("product")ProductModel pro) {
//		HttpSession session=request.getSession();
//		Account account=(Account)session.getAttribute("account");
//		model.addAttribute("account", account);
		List<Product>listProduct=this.productRepo.findAll();
		model.addAttribute("listPro", listProduct);
		model.addAttribute("views", "/views/admin/order/orderAdmin.jsp");
		return "index";
	}
	
	@PostMapping("admin/orderStore")
	public String orderStoreAdmin(@ModelAttribute("product")ProductModel pro,Order order,OrderDetail orderDetail) {
		System.out.println(pro.toString());
		System.out.println(pro.getQuantity());
	
		order.setFullname(pro.getFullname());
		order.setCreatedDate(new Date());
		order.setStatus(0);
		order.setAddress(pro.getAddress());
		order.setPhoneNumber(pro.getPhone());
		order.setTotal(0);
		
		
		Account account=this.accountRepo.findByEmail(pro.getEmail());
		if(account==null) {
			order.setUser(null);
		}else {
			System.out.println("------- account not null----------");
			order.setUser(account);
		}
		this.orderRepo.save(order);
		
		List<Order>listOrderLast=this.orderRepo.findAllByEmail(pro.getEmail());
		if(listOrderLast==null) {
			System.out.println("------ list orderLast Null ----");
		}else {
			orderLast=listOrderLast.get(listOrderLast.size()-1);
			System.out.println("------ list orderLast not null");
		}
		
		List<OrderDetail>listOrderDetail=new ArrayList<>();
		for (Integer item : pro.getProduct_id()) {
			orderDetail=new OrderDetail();
			orderDetail.setProduct(this.productRepo.getOne(item));
			listOrderDetail.add(orderDetail);
			
		}
		
		System.out.println("-------- order last:"+orderLast.getId());
		double price=0;
		
		for (int i = 0; i < listOrderDetail.size(); i++) {
			
			System.out.println("================= vào thực hiện orderDetail ===============");
			System.out.println(orderLast.getId());
			listOrderDetail.get(i).setOrder(orderLast);
			System.out.println(listOrderDetail.get(i).getOrder().getId());
			listOrderDetail.get(i).setQuantity(pro.getQuantity().get(i));
			System.out.println();
			double pro_price=this.productRepo.findById1(pro.getProduct_id().get(i)).getPrice();
			int quantity=pro.getQuantity().get(i);
			listOrderDetail.get(i).setPrice(pro_price*quantity);
			price+=listOrderDetail.get(i).getPrice();
			this.orderDetailRepo.save(listOrderDetail.get(i));
			
		}
		System.out.println(listOrderDetail.size());
		
		
		
		
//
		order.setTotal(price);
		this.orderRepo.save(order);
		
		return "redirect:/home";
	}
	
	@PostMapping("orderStore/{total}")
	public String orderStore(Model model,@ModelAttribute("account")Account account,
			Order order,OrderDetail orderDetail,
			@PathVariable("total")Double total) {
		HttpSession session=request.getSession();
		Account acc=(Account)session.getAttribute("account");
		System.out.println("------------------account-id:"+acc.getId());
		
		account=this.accountRepo.getById(acc.getId());
		List<Card>listCard=this.cartRepo.findAllCartByAccountId(acc.getId());
		account.setAddress(account.getAddress());
		order.setCreatedDate(new Date());
		order.setUser(account);
		order.setTotal(total);
		order.setFullname(account.getFullname());
		this.orderRepo.save(order);
		
		
//		for (int i = 0; i < listCard.size(); i++) {
//			System.out.println("----------them san pham trong gio hàng sang hoa don chi tiet -------");
//			System.out.println(listCard.get(i).getQuantity());
//			System.out.println(listCard.get(i).getProduct());
//			System.out.println(listCard.get(i).getPrice());
//			orderDetail.setQuantity(listCard.get(i).getQuantity());
//			orderDetail.setProduct(listCard.get(i).getProduct());
//			orderDetail.setPrice(listCard.get(i).getPrice());
//			orderDetail.setOrder(order);
//			this.orderDetailRepo.save(orderDetail);
//		}
		
		for (Card card : listCard) {
			
			System.out.println("----------them san pham trong gio hàng sang hoa don chi tiet -------");
			System.out.println(card.getQuantity());
			System.out.println(card.getId());
//			System.out.println(card.getProduct());
//			System.out.println(card.getPrice());
			orderDetail=new OrderDetail();
			orderDetail.setQuantity(card.getQuantity());
			orderDetail.setProduct(card.getProduct());
			orderDetail.setPrice(card.getProduct().getPrice()*card.getQuantity());
			orderDetail.setOrder(order);
			this.orderDetailRepo.save(orderDetail);
		}
		return "redirect:/home";
	}
	
	@GetMapping("orderedProduct1")
	private String orderProduc1(Model model) {
		HttpSession session=request.getSession();
		Account account=(Account) session.getAttribute("account");
		if(account!=null) {
			List<Order>listsp_dangGiao=this.orderRepo.findAllByAccountIdAndSatus(account.getId(),1);
			for (Order order : listsp_dangGiao) {
				System.out.println("----------------- sản phẩm đang giao ---------------");
				System.out.println(order.getId());
			}
			model.addAttribute("listsp", listsp_dangGiao);
		}else {
			System.out.println("chưa đăng nhập");
		}
		
		model.addAttribute("views", "/views/order/orderedProduct.jsp");
		return "index";
	}
	
	@GetMapping("orderedProduct2")
	private String orderProduc2(Model model) {
		HttpSession session=request.getSession();
		Account account=(Account) session.getAttribute("account");
		if(account!=null) {
			List<Order>listsp_daGiao=this.orderRepo.findAllByAccountIdAndSatus(account.getId(),2);
			for (Order order : listsp_daGiao) {
				System.out.println("----------------- sản phẩm đã giao ---------------");
				System.out.println(order.getId());
			}
			model.addAttribute("listsp", listsp_daGiao);
		}else {
			System.out.println("chưa đăng nhập");
		}
		
		model.addAttribute("views", "/views/order/orderedProduct.jsp");
		return "index";
	}
	
	@GetMapping("orderedProduct3")
	private String orderProduc3(Model model) {
		HttpSession session=request.getSession();
		Account account=(Account) session.getAttribute("account");
		if(account!=null) {
			List<Order>listsp_daHuy=this.orderRepo.findAllByAccountIdAndSatus(account.getId(),3);
			for (Order order : listsp_daHuy) {
				System.out.println("----------------- sản phẩm đã huỷ ---------------");
				System.out.println(order.getId());
			}
			model.addAttribute("listsp", listsp_daHuy);
		}else {
			System.out.println("chưa đăng nhập");
		}
		
		model.addAttribute("views", "/views/order/orderedProduct.jsp");
		return "index";
	}
	
	@GetMapping("orderedProduct")
	private String orderProduct(Model model) {
		HttpSession session=request.getSession();
		Account account=(Account) session.getAttribute("account");
		if(account!=null) {
			List<Order>listsp=this.orderRepo.findAllByAccountIdAndSatus(account.getId(),0);
			for (Order order : listsp) {
				System.out.println("----------------- sản phẩm được lấy ra ---------------");
				System.out.println(order.getId());
			}
			model.addAttribute("listsp", listsp);
//			
			
			
			
			
			
			
		}else {
			System.out.println("------- bạn chưa đăng nhập ---------");
		}
		
		model.addAttribute("views", "/views/order/orderedProduct.jsp");
		return "index";
	}
	
	@GetMapping("orderCancellation/{id}")
	private String orderCancellation(@PathVariable("id")Order order) {
		System.out.println("-------id:"+order.getId());
//		this.orderDetailRepo.delete(orderDetail);
		this.orderRepo.delete(order);
		return "redirect:/orderedProduct";
	}
	
	@GetMapping("deleteOrder/{id}")
	private String deleteOrder(@PathVariable("id")Order order1,Order order) {
		Account account=this.accountRepo.getOne(13);
		System.out.println("------CreateDate:"+order1.getCreatedDate());
		System.out.println("------Account:"+account.getId());
		order.setCreatedDate(order1.getCreatedDate());
		order.setStatus(2);
		order.setUser(account);
		order.setTotal(order1.getTotal());
		order.setAddress(order1.getAddress());
		this.orderRepo.save(order);
		return "redirect:/cartManagement";
	}
	
	@GetMapping("updateOrder/{id}")
	private String updateOrder(@PathVariable("id")Order order1,Order order) {
		HttpSession session=request.getSession();
		Account acc=(Account)session.getAttribute("account");
		Account account=this.accountRepo.getOne(acc.getId());
		System.out.println("------CreateDate:"+order1.getCreatedDate());
		System.out.println("------Account:"+account.getId());
		order.setCreatedDate(order1.getCreatedDate());
		order.setStatus(1);
		order.setUser(account);
		order.setTotal(order1.getTotal());
		order.setAddress(order1.getAddress());
		this.orderRepo.save(order);
		return "redirect:/cartManagement";
	}

}