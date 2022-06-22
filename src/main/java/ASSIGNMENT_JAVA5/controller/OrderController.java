package ASSIGNMENT_JAVA5.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.engine.query.spi.ParamLocationRecognizer.InFlightOrdinalParameterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ASSIGNMENT_JAVA5.entities.Account;
import ASSIGNMENT_JAVA5.entities.Card;
import ASSIGNMENT_JAVA5.entities.Comment;
import ASSIGNMENT_JAVA5.entities.Favorite;
import ASSIGNMENT_JAVA5.entities.Order;
import ASSIGNMENT_JAVA5.entities.OrderDetail;
import ASSIGNMENT_JAVA5.entities.ProductModel;
import ASSIGNMENT_JAVA5.entities.Product;

import ASSIGNMENT_JAVA5.repositories.AccountRepository;
import ASSIGNMENT_JAVA5.repositories.CartRepository;
import ASSIGNMENT_JAVA5.repositories.CommentRepository;
import ASSIGNMENT_JAVA5.repositories.FavoriteRepository;
import ASSIGNMENT_JAVA5.repositories.OrderDetailRepository;
import ASSIGNMENT_JAVA5.repositories.OrderRepository;
import ASSIGNMENT_JAVA5.repositories.ProductRepository;
import ASSIGNMENT_JAVA5.utils.UploadFileUtils;

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
	private CommentRepository commentRepo;
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UploadFileUtils uploadUtil;

	private Order orderLast;

	@GetMapping("order1/{price}/{id}")
	public String order1(Order order, OrderDetail orderDetail, @PathVariable("price") Double price,
			@PathVariable("id") Integer id, @RequestParam("name") String fullname, @RequestParam("phone") String phone,
			@RequestParam("address") String address, @RequestParam("quantity") Integer quantity) {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		if (account != null) {
			order.setAddress(address);
			order.setCreatedDate(new Date());
			order.setFullname(fullname);
			order.setPhoneNumber(phone);
			order.setUser(account);
			order.setTotal(quantity * price);
			this.orderRepo.save(order);

			List<Order> listOrder = this.orderRepo.findAllByEmail(account.getEmail());

			orderDetail.setOrder(listOrder.get(listOrder.size() - 1));
			orderDetail.setQuantity(quantity);
			Product product = this.productRepo.getOne(id);
			orderDetail.setProduct(product);
			orderDetail.setPrice(quantity * price);
			this.orderDetailRepo.save(orderDetail);
			return "redirect:/orderedProduct";
		} else {
			return "redirect:/home";
		}

	}

	@GetMapping("order")
	public String order(
			Model model,
			Order order, 
			OrderDetail orderDetail,
			@ModelAttribute("product") ProductModel pro
			
			) {

		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");

		List<Card> listCard = this.cartRepo.findAllCartByAccountId(account.getId());

		
		
		List<Product> listPro = this.productRepo.findAll();
		model.addAttribute("listPro", listPro);
		double tong = 0;
		for (Card list : listCard) {
			tong += list.getPrice();

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
		
		HttpSession session=request.getSession();
		Account account=(Account)session.getAttribute("account");
		model.addAttribute("account", account);

		model.addAttribute("views", "/views/productDetails.jsp");
		Product product = this.proRepo.getOne(id);
		model.addAttribute("product", product);

		Favorite favorite = this.favoriteRepo.findFavoriteByProductId(product.getId());
		model.addAttribute("favorite", favorite);

//		Product listPro = this.productRepo.findProductByNameAndSizeAndColor("", "", "");
//		model.addAttribute("listColor", listPro);
		
		
		List<Comment>listComment=this.commentRepo.listCommentByProductID(id);
		model.addAttribute("listComment", listComment);

		return "index";
	}

//	@PostMapping("cardstore")
//	public String cardStore(Model model) {
//		return "redirect:/card";
//	}

	@GetMapping("orderDetails")
	public String orderDetails(Model model, Card cart, @RequestParam("name") String name,
			@RequestParam("color") String color, @RequestParam("size") String size,
			@RequestParam("quantity") Integer quantity) {

		//thua
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");

		Product pro = this.productRepo.findProductByNameAndSizeAndColor("%" + name + "%", "%" + color + "%",
				"%" + size + "%");

		System.out.println("----------------sản phẩm lấy ra từ product ----------");
		System.out.println(name);
		System.out.println(color);
		System.out.println(size);
		if (pro == null) {
			System.out.println("không tìm thấy sản phẩm");
			session.setAttribute("error", "Thêm sản phẩm không thành công!");
			return "redirect:/order";
		} else {

			account = this.accountRepo.getOne(account.getId());
			List<Card> listCard = this.cartRepo.findAllCartByAccountId(account.getId());

			if (listCard.isEmpty()) {
				cart.setAccount(account);
				cart.setPrice(pro.getPrice() * quantity);
				cart.setQuantity(quantity);
				cart.setProduct(pro);
				this.cartRepo.save(cart);
				System.out.println("---------------- không thấy -------------");
			} else {
				boolean kt = true;
				for (Card list : listCard) {
					if (list.getProduct().getId() == pro.getId()) {
						cart.setId(list.getId());
						cart.setAccount(account);
						cart.setPrice(pro.getPrice() * (quantity + list.getQuantity()));
						cart.setQuantity(quantity + list.getQuantity());
						cart.setProduct(pro);
						this.cartRepo.save(cart);
						kt = false;
						break;
					}
				}

				if (kt != false) {
					for (Card list : listCard) {
						if (list.getProduct().getId() != pro.getId()) {

							cart.setAccount(account);
							cart.setPrice(pro.getPrice() * quantity);
							cart.setQuantity(quantity);
							cart.setProduct(pro);
							this.cartRepo.save(cart);
							System.out.println("-----------------------------" + list.getProduct().getPrice());
							System.out.println("-----------------------------" + list.getPrice());
							System.out.println("-----------------------------" + pro.getPrice());
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
	public String adminOrder(Model model, @ModelAttribute("product") ProductModel pro) {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		model.addAttribute("account", account);
		if (account.getAdmin() == 1) {
			List<Product> listProduct = this.productRepo.findAll();
			model.addAttribute("listPro", listProduct);
			model.addAttribute("views", "/views/admin/order/orderAdmin.jsp");
			return "index";
		} else {
			return "redirect:/home";
		}

	}

	@GetMapping("orderStore")
	public String orderStore(@ModelAttribute("producut") ProductModel pro, Card cart, Product product) {

		System.out.println("---------- them san pham khi chuan bi dat hang -----------------");
		System.out.println(pro.toString());
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		List<Integer> listProductId = pro.getProduct_id();
		List<Integer> listQuantity = pro.getQuantity();
		List<Card> listCardAll = this.cartRepo.findAllCartByAccountId(account.getId());

		Boolean kt = false;
		for (int i = 0; i < listProductId.size(); i++) {

			if (listCardAll.get(i).getProduct().getId() != listProductId.get(i)) {

				
					System.out.println("------------------ sản phẩm không có trong giỏ hàng -------------------");
					System.out.println("list card all" + listCardAll.get(i).getProduct().getId());
					System.out.println("list Product id:" + listProductId.get(i));
					List<Integer> listProductNew = new ArrayList<Integer>();
					listProductNew.add(listProductId.get(i));
					List<Card> listCard = new ArrayList<>();
					for (Integer item : listProductNew) {
						cart = new Card();
						cart.setProduct(this.productRepo.getOne(item));
						listCard.add(cart);
					}

					for (int j = 0; j < listCard.size(); j++) {
						double pro_price = this.productRepo.findById1(pro.getProduct_id().get(j)).getPrice();
						listCard.get(j).setQuantity(listQuantity.get(j));
						listCard.get(j).setAccount(account);
						listCard.get(j).setPrice(listQuantity.get(j) * pro_price);
						this.cartRepo.save(listCard.get(j));
					}
				

			} else {

				if (listCardAll.get(i).getProduct().getId() == listProductId.get(i)) {
					
						System.out.println(
								"^^^^^^^^^^^^^^^^^^^^^^^^^^^^ sản phẩm  có trong giỏ hàng -------------------------");
						System.out.println(listProductId.get(i));

						List<Integer> listProductNew = new ArrayList<Integer>();
						listProductNew.add(listProductId.get(i));
						List<Card> listCard = new ArrayList<>();
						for (Integer item : listProductNew) {
							cart = new Card();
							cart.setProduct(this.productRepo.getOne(item));
							listCard.add(cart);
						}

						for (int j = 0; j < listCard.size(); j++) {
							Integer idCard = this.cartRepo.findByProductId(pro.getProduct_id().get(j)).getId();
							double pro_price = this.productRepo.findById1(pro.getProduct_id().get(j)).getPrice();
							Integer pro_quantity = this.cartRepo.findByProductId(pro.getProduct_id().get(j))
									.getQuantity();
							double cart_price = this.cartRepo.findByProductId(pro.getProduct_id().get(j)).getPrice();
							listCard.get(j).setId(idCard);
							listCard.get(j).setQuantity(listQuantity.get(j) + pro_quantity);
							listCard.get(j).setAccount(account);
							listCard.get(j).setPrice((pro_price*listQuantity.get(j)) + cart_price);
							this.cartRepo.save(listCard.get(j));
						}
					}

				

			}

		}

		return "redirect:/order";
	}

	@PostMapping("admin/orderStore")
	public String orderStoreAdmin(@ModelAttribute("product") ProductModel pro, Order order, OrderDetail orderDetail) {

		System.out.println("------ them hoa don va hdct cua admin ----------");
		System.out.println(pro.toString());
		System.out.println(pro.getQuantity());

		order.setFullname(pro.getFullname());
		order.setCreatedDate(new Date());
		order.setStatus(0);
		order.setAddress(pro.getAddress());
		order.setPhoneNumber(pro.getPhone());
		order.setTotal(0);

		if (!pro.getEmail().isEmpty()) {
			Account account = this.accountRepo.findByEmail(pro.getEmail());

			System.out.println("------- account not null----------");
			order.setUser(account);
			this.orderRepo.save(order);

			List<Order> listOrderLast = this.orderRepo.findAllByEmail(pro.getEmail());
			if (listOrderLast.equals(" ")) {
				System.out.println("------ list orderLast Null ----");
			} else {
				orderLast = listOrderLast.get(listOrderLast.size() - 1);
				System.out.println("------ list orderLast not null");
			}

			List<OrderDetail> listOrderDetail = new ArrayList<>();
			for (Integer item : pro.getProduct_id()) {
				orderDetail = new OrderDetail();
				orderDetail.setProduct(this.productRepo.getOne(item));
				listOrderDetail.add(orderDetail);

			}

			System.out.println("-------- order last:" + orderLast.getId());
			double price = 0;

			for (int i = 0; i < listOrderDetail.size(); i++) {

				System.out.println("================= vào thực hiện orderDetail ===============");
				System.out.println(orderLast.getId());
				listOrderDetail.get(i).setOrder(orderLast);
				System.out.println(listOrderDetail.get(i).getOrder().getId());
				listOrderDetail.get(i).setQuantity(pro.getQuantity().get(i));
				System.out.println();
				double pro_price = this.productRepo.findById1(pro.getProduct_id().get(i)).getPrice();
				int quantity = pro.getQuantity().get(i);
				listOrderDetail.get(i).setPrice(pro_price * quantity);
				price += listOrderDetail.get(i).getPrice();
				this.orderDetailRepo.save(listOrderDetail.get(i));

			}
			System.out.println(listOrderDetail.size());

			//
			order.setTotal(price);
			this.orderRepo.save(order);
		} else {
			System.out.println(" không có email ");
			order.setUser(null);
			this.orderRepo.save(order);
			
			System.out.println("---------------- số điện thoại của hoá đơn cuối cùng"+pro.getPhone());
			List<Order> listOrderLast = this.orderRepo.findAllByPhone(pro.getPhone());
			
			
			if (listOrderLast == null) {
				System.out.println("------ list orderLast Null ----");
			} else {
				orderLast = listOrderLast.get(listOrderLast.size() - 1);
				System.out.println("------ list orderLast not null");
			}

			List<OrderDetail> listOrderDetail = new ArrayList<>();
			for (Integer item : pro.getProduct_id()) {
				orderDetail = new OrderDetail();
				orderDetail.setProduct(this.productRepo.getOne(item));
				listOrderDetail.add(orderDetail);

			}

			System.out.println("-------- order last:" + orderLast.getId());
			double price = 0;

			for (int i = 0; i < listOrderDetail.size(); i++) {

				System.out.println("================= vào thực hiện orderDetail ===============");
				System.out.println(orderLast.getId());
				listOrderDetail.get(i).setOrder(orderLast);
				System.out.println(listOrderDetail.get(i).getOrder().getId());
				listOrderDetail.get(i).setQuantity(pro.getQuantity().get(i));
				System.out.println();
				double pro_price = this.productRepo.findById1(pro.getProduct_id().get(i)).getPrice();
				int quantity = pro.getQuantity().get(i);
				listOrderDetail.get(i).setPrice(pro_price * quantity);
				price += listOrderDetail.get(i).getPrice();
				this.orderDetailRepo.save(listOrderDetail.get(i));

			}
			System.out.println(listOrderDetail.size());

			//
			order.setTotal(price);
			this.orderRepo.save(order);
		}

		return "redirect:/cartManagement";
	}

	@PostMapping("orderStore/{total}")
	public String orderStore(Model model, @ModelAttribute("account") Account account, Order order,
			OrderDetail orderDetail, @PathVariable("total") Double total) {
		HttpSession session = request.getSession();
		Account acc = (Account) session.getAttribute("account");
		System.out.println("------------------account-id:" + acc.getId());

		account = this.accountRepo.getById(acc.getId());
		List<Card> listCard = this.cartRepo.findAllCartByAccountId(acc.getId());
		account.setAddress(account.getAddress());
		order.setCreatedDate(new Date());
		order.setUser(account);
		order.setTotal(total);
		order.setFullname(account.getFullname());
		this.orderRepo.save(order);

		for (Card card : listCard) {

			System.out.println("----------them san pham trong gio hàng sang hoa don chi tiet -------");
			System.out.println(card.getQuantity());
			System.out.println(card.getId());
			orderDetail = new OrderDetail();
			orderDetail.setQuantity(card.getQuantity());
			orderDetail.setProduct(card.getProduct());
			orderDetail.setPrice(card.getProduct().getPrice() * card.getQuantity());
			orderDetail.setOrder(order);
			this.orderDetailRepo.save(orderDetail);
			this.cartRepo.delete(card);
		}
		return "redirect:/orderedProduct";
	}

	@GetMapping("orderedProduct1")
	private String orderProduc1(Model model) {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		if (account != null) {
			List<Order> listsp_dangGiao = this.orderRepo.findAllByAccountIdAndSatus(account.getId(), 1);
			for (Order order : listsp_dangGiao) {
				System.out.println("----------------- sản phẩm chờ lấy hàng ---------------");
				System.out.println(order.getId());
			}
			model.addAttribute("listsp", listsp_dangGiao);
		} else {
			System.out.println("chưa đăng nhập");
		}

		model.addAttribute("views", "/views/order/orderedProduct.jsp");
		return "index";
	}

	@GetMapping("orderedProduct4")
	private String orderProduc4(Model model) {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		if (account != null) {

			List<OrderDetail> listOrderDetai = this.orderDetailRepo.FindListOrderDetailByStatusAndAccountId(4,
					account.getId());
			for (OrderDetail orderDetail : listOrderDetai) {
				System.out
						.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + orderDetail.getProduct().getName());
			}
			model.addAttribute("listsp", listOrderDetai);

		} else {
			System.out.println("chưa đăng nhập");
		}

		model.addAttribute("views", "/views/order/orderedProduct4.jsp");
		return "index";
	}

	@GetMapping("orderedProduct5")
	private String orderProduc5(Model model) {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		if (account != null) {
			List<OrderDetail> listsp_dangGiao = this.orderDetailRepo.FindListOrderDetailByStatusAndAccountId(5,
					account.getId());
			for (OrderDetail orderDetail : listsp_dangGiao) {
				System.out.println("----------------- sản phẩm Trả hàng ---------------");
				System.out.println(orderDetail.getProduct().getName());
			}
			model.addAttribute("listsp", listsp_dangGiao);
		} else {
			System.out.println("chưa đăng nhập");
		}

		model.addAttribute("views", "/views/order/orderedProduct4.jsp");
		return "index";
	}

	@GetMapping("orderedProduct3")
	private String orderProduc3(Model model) {
		
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		if (account != null) {
			List<OrderDetail> listsp_dangGiao = this.orderDetailRepo.FindListOrderDetailByStatusAndAccountId(3,
					account.getId());
			for (OrderDetail orderDetail : listsp_dangGiao) {
				System.out.println("----------------- sản phẩm đã giao ---------------");
				System.out.println(orderDetail.getProduct().getName());
			}
			model.addAttribute("listsp", listsp_dangGiao);
		} else {
			System.out.println("chưa đăng nhập");
		}

		model.addAttribute("views", "/views/order/orderedProduct4.jsp");
		return "index";
		
		
		
		
//		HttpSession session = request.getSession();
//		Account account = (Account) session.getAttribute("account");
//		if (account != null) {
//			List<Order> listsp_dangGiao = this.orderRepo.findAllByAccountIdAndSatus(account.getId(), 3);
//			for (Order order : listsp_dangGiao) {
//				System.out.println("----------------- sản phẩm đã giao ---------------");
//				System.out.println(order.getId());
//			}
//			model.addAttribute("listsp", listsp_dangGiao);
//		} else {
//			System.out.println("chưa đăng nhập");
//		}
//
//		model.addAttribute("views", "/views/order/orderedProduct.jsp");
//		return "index";
	}

	@GetMapping("orderedProduct2")
	private String orderProduc2(Model model) {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		if (account != null) {
			List<Order> listsp_daGiao = this.orderRepo.findAllByAccountIdAndSatus(account.getId(), 2);
			for (Order order : listsp_daGiao) {
				System.out.println("----------------- sản phẩm đang giao ---------------");
				System.out.println(order.getId());
			}
			model.addAttribute("listsp", listsp_daGiao);
		} else {
			System.out.println("chưa đăng nhập");
		}

		model.addAttribute("views", "/views/order/orderedProduct.jsp");
		return "index";
	}

	@GetMapping("orderedProduct")
	private String orderProduct(Model model) {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		if (account != null) {
			List<Order> listsp = this.orderRepo.findAllByAccountIdAndSatus(account.getId(), 0);
			for (Order order : listsp) {
				System.out.println("----------------- sản phẩm được chờ xác nhận ---------------");
				System.out.println(order.getId());
			}
			model.addAttribute("listsp", listsp);
//			

		} else {
			System.out.println("------- bạn chưa đăng nhập ---------");
		}

		model.addAttribute("views", "/views/order/orderedProduct.jsp");
		return "index";
	}

	@GetMapping("orderCancellation/{id}")
	private String orderCancellation(@PathVariable("id") Order order, Order order1) {
		System.out.println("------- huy dong hàng khi dang ơ trang thai tro xac nhạn ---------" );

		order1.setStatus(4);
		order1.setUser(order.getUser());
		order1.setFullname(order.getFullname());
		order1.setAddress(order.getAddress());
		order1.setPhoneNumber(order.getPhoneNumber());
		order1.setTotal(0);
		order1.setCreatedDate(order.getCreatedDate());
		this.orderRepo.save(order1);
		List<OrderDetail> list = this.orderDetailRepo.FindListOrderDetailByOrderId(order.getId());
		for (OrderDetail orderDetail : list) {
			orderDetail.setStatus(4);
			this.orderDetailRepo.save(orderDetail);
		}
		return "redirect:/orderedProduct";
	}

	@GetMapping("admin/deleteOrder/{id}")
	private String deleteOrder(@PathVariable("id") Order order1, Order order) {
		//huy don hang phia admin
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		if (account != null) {
			account = this.accountRepo.getOne(account.getId());
			System.out.println("------CreateDate:" + order1.getCreatedDate());
			System.out.println("------Account:" + account.getId());
			order.setCreatedDate(order1.getCreatedDate());
			order.setStatus(4);
			order.setUser(account);
			order.setTotal(order1.getTotal());
			order.setAddress(order1.getAddress());
			order.setFullname(order.getFullname());
			order.setPhoneNumber(order.getPhoneNumber());
			this.orderRepo.save(order);
			List<OrderDetail> listOrderDetail = this.orderDetailRepo.FindListOrderDetailByOrderId(order1.getId());
			for (OrderDetail orderDetail : listOrderDetail) {
				orderDetail.setStatus(4);
				this.orderDetailRepo.save(orderDetail);
			}
			return "redirect:/cartManagement";
		} else {
			return "redirect:/home";
		}

	}

	@GetMapping("updateOrder/{id}")
	private String updateOrder(@PathVariable("id") Order order1, Order order) {
		HttpSession session = request.getSession();
		Account acc = (Account) session.getAttribute("account");
		Account account = this.accountRepo.getOne(acc.getId());
		System.out.println("------CreateDate:" + order1.getCreatedDate());
		System.out.println("------Account:" + account.getId());
		order.setCreatedDate(order1.getCreatedDate());
		order.setStatus(1);
		order.setUser(account);
		order.setFullname(account.getFullname());
		order.setPhoneNumber(account.getPhoneNumber());
		order.setTotal(order1.getTotal());
		order.setAddress(order1.getAddress());
		List<OrderDetail> listOrderDetail = this.orderDetailRepo.FindListOrderDetailByOrderId(order1.getId(), 0);
		for (OrderDetail orderDetail : listOrderDetail) {
			//xác nhận đơn hàng phía admin
			System.out.println(orderDetail.getProduct().getName());
			orderDetail.setStatus(1);
			this.orderDetailRepo.save(orderDetail);
		}
		this.orderRepo.save(order);
		return "redirect:/cartManagement";
	}

	@RequestMapping("admin/order/deliveryConfirmation/{id}")
	private String deliveryConfirmation(@PathVariable("id") Order order1, Order order) {
		//xac nhận giao hang phía admin
		HttpSession session = request.getSession();
		Account acc = (Account) session.getAttribute("account");
		Account account = this.accountRepo.getOne(acc.getId());
		System.out.println("------CreateDate:" + order1.getCreatedDate());
		System.out.println("------Account:" + account.getId());
		order.setCreatedDate(order1.getCreatedDate());
		order.setStatus(2);
		order.setUser(account);
		order.setFullname(account.getFullname());
		order.setPhoneNumber(account.getPhoneNumber());
		order.setTotal(order1.getTotal());
		order.setAddress(order1.getAddress());
		this.orderRepo.save(order);
		List<OrderDetail> list = this.orderDetailRepo.FindListOrderDetailByOrderId(order1.getId(), 1);
		for (OrderDetail orderDetail : list) {
			orderDetail.setStatus(2);
			this.orderDetailRepo.save(orderDetail);
		}

		return "redirect:/cartManagement";
	}

	
	@GetMapping("returnsOrder/{id}")
	public String returnsOrder(
			@PathVariable("id")Integer id
			) {
//		Order order=this.orderRepo.getOne(id);
//		order.setAddress(order.getAddress());
//		order.setId(order.getId());
//		order.setCreatedDate(order.getCreatedDate());
//		order.setFullname(order.getFullname());
//		order.setStatus(5);
//		order.set
		
		return "redirect:/orderedProduct2";
	}
	
	@RequestMapping("returns/{id}")
	public String returns(@PathVariable("id") Integer id, @RequestParam("upload_file") MultipartFile uploadFile,
			@RequestParam("note") String note) {
		System.out.println(" ------------ yeu cau tra hang ------------");
//		System.out.println("id để trả hàng:" + note + uploadFile.getOriginalFilename());
		this.uploadUtil.handleUpLoadFile(uploadFile);
		OrderDetail orderDetail = this.orderDetailRepo.getOne(id);
		orderDetail.setId(orderDetail.getId());
		orderDetail.setOrder(orderDetail.getOrder());
		orderDetail.setQuantity(orderDetail.getQuantity());
		orderDetail.setPrice(orderDetail.getPrice());
		orderDetail.setStatus(6);
		orderDetail.setNote(note);
		orderDetail.setImage(uploadFile.getOriginalFilename());
		this.orderDetailRepo.save(orderDetail);
		return "redirect:/orderedProduct2";
	}

	@GetMapping("receive/{id}")
	public String receive(@PathVariable("id") Order order1, Order order, OrderDetail orderDetail) {
		System.out.println("-------------- nhan hang ------------");
		order.setId(order1.getId());
		order.setStatus(3);
		order.setPhoneNumber(order1.getPhoneNumber());
		order.setAddress(order1.getAddress());
		order.setTotal(order1.getTotal());
		order.setCreatedDate(order1.getCreatedDate());
		order.setFullname(order1.getFullname());
		order.setUser(order1.getUser());
		this.orderRepo.save(order);
		List<OrderDetail> list = this.orderDetailRepo.FindListOrderDetailByOrderId(order1.getId(), 2);
		for (OrderDetail orderDetail2 : list) {
			orderDetail2.setStatus(3);
			this.orderDetailRepo.save(orderDetail2);
		}
		return "redirect:/orderedProduct3";
	}

}