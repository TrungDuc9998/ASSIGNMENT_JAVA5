package ASSIGNMENT_JAVA5.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ASSIGNMENT_JAVA5.entities.Order;
import ASSIGNMENT_JAVA5.entities.OrderDetail;
import ASSIGNMENT_JAVA5.entities.Product;
import ASSIGNMENT_JAVA5.repositories.OrderDetailRepository;
import ASSIGNMENT_JAVA5.repositories.OrderRepository;

@Controller
public class OrderDetailsController {
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private HttpServletRequest request;
	
	
	@GetMapping("deleteOrderDetail/{id}/{idPro}")
	private String deleteOderDetal(
			@PathVariable("id")OrderDetail od,
			@PathVariable("idPro")Integer idPro,
			OrderDetail orderDetail) {
		orderDetail.setId(od.getId());
		orderDetail.setOrder(od.getOrder());
		orderDetail.setStatus(4);
		orderDetail.setProduct(od.getProduct());
		this.orderDetailRepo.save(orderDetail);
		List<OrderDetail>listOrderDetail=this.orderDetailRepo.FindListOrderDetailByOrderId(idPro, 0);
		double tong=0;
		for (OrderDetail orderDetail2 : listOrderDetail) {
			tong+=orderDetail2.getPrice();
		}
		System.out.println("--------------- tổng tiền sau khi huỷ một đơn hàng trong hoá đơn:"+tong);
		Order order=this.orderRepo.getOne(idPro);
		order.setTotal(tong);
		this.orderRepo.save(order);
		//
		return "redirect:/orderedProduct";
	}
	
	@GetMapping("admin/ReturnRequest")
	public String returnRequest(Model model) {
		List<OrderDetail>listOrderDetail=this.orderDetailRepo.FindListOrderDetailByStatus(6);
		model.addAttribute("listOrder", listOrderDetail);
		model.addAttribute("views", "/views/admin/orderDetail/ReturnRequest.jsp");
		//yêu cầu trả hàng và hoàn tiền
		return "index";
	}
	
	@RequestMapping("admin/verifyRequest/{id}/{idOrder}")
	public String verifyRequest(
			@PathVariable("id")Integer id,
			@PathVariable("idOrder")Integer idOrder,
			Order order1
			) {
	
		List<OrderDetail>listOdl=this.orderDetailRepo.FindListOrderDetailByOrderId(idOrder);
		
		
		System.out.println("số phần tử trong mảng:"+listOdl.size());
		
		
		
	
			
			
			
				OrderDetail orderDetail=this.orderDetailRepo.getOne(id);
				orderDetail.setId(orderDetail.getId());
				orderDetail.setOrder(orderDetail.getOrder());
				orderDetail.setQuantity(orderDetail.getQuantity());
				orderDetail.setPrice(orderDetail.getPrice());
				orderDetail.setStatus(5);
				orderDetail.setNote(orderDetail.getNote());
				orderDetail.setImage(orderDetail.getImage());
				this.orderDetailRepo.save(orderDetail);
				
				
				
				List<OrderDetail>listOrderDetail=this.orderDetailRepo.FindListOrderDetailByOrderId(idOrder, 2);
				List<OrderDetail>listod5=this.orderDetailRepo.FindListOrderDetailByOrderId(idOrder,3);
				
				double sum=0;
				for (OrderDetail orderDetail2 : listod5) {
					sum+=orderDetail2.getPrice();
				}
				
				double tong=0;
				for (OrderDetail orderDetail2 : listOrderDetail) {
					tong+=orderDetail2.getPrice();
				}
				System.out.println("--------------- tổng tiền sau khi huỷ một đơn hàng trong hoá đơn:"+tong);
				Order order=this.orderRepo.getOne(idOrder);
				order.setTotal(tong+sum);
				this.orderRepo.save(order);
				List<OrderDetail>listOrderDetail1=this.orderDetailRepo.FindListOrderDetailByOrderId(idOrder, 5);
				System.out.println("------------------------------- số phần tử trong mảng od:"+listOrderDetail1.size());
		if(listOrderDetail1.size()==listOdl.size()) {
			System.out.println("----- vào sau tổng tiền --------");
			Order order2=this.orderRepo.getOne(idOrder);
			order2.setTotal(0);
			order2.setStatus(5);
			this.orderRepo.save(order2);
//			OrderDetail orderDetail2=this.orderDetailRepo.getOne(id);
//			orderDetail2.setId(orderDetail2.getId());
//			orderDetail2.setOrder(orderDetail2.getOrder());
//			orderDetail2.setQuantity(orderDetail2.getQuantity());
//			orderDetail2.setPrice(orderDetail2.getPrice());
//			orderDetail2.setStatus(5);
//          xác nhận yêu cầu trả hàng
//			orderDetail2.setNote(orderDetail2.getNote());
//			orderDetail2.setImage(orderDetail2.getImage());
//			this.orderDetailRepo.save(orderDetail2);
	}else if(listOdl.size()==1){
			System.out.println("--------------------------- vào rồi bạn ơi ------------------");
			System.out.println("---------------------- huỷ còn 1 sản phẩm trong hoá đơn");
			System.out.println("----------------------------------"+idOrder);
			Order order3=this.orderRepo.getOne(idOrder);
			order3.setTotal(0);
			order3.setStatus(5);
			this.orderRepo.save(order3);
			OrderDetail orderDetai=this.orderDetailRepo.getOne(id);
			orderDetai.setId(orderDetai.getId());
			orderDetai.setOrder(orderDetai.getOrder());
			orderDetai.setQuantity(orderDetai.getQuantity());
			orderDetai.setPrice(orderDetai.getPrice());
			orderDetai.setStatus(5);
			orderDetai.setNote(orderDetai.getNote());
			orderDetai.setImage(orderDetai.getImage());
			this.orderDetailRepo.save(orderDetai);
			
		}
		
		return "redirect:/home";
	}
	
	@GetMapping("admin/orderIsReturned")
	public String orderIsReturned(Model model) {
		List<OrderDetail>listOrderDetail=this.orderDetailRepo.FindListOrderDetailByStatus(5);
		model.addAttribute("listOrder", listOrderDetail);
		model.addAttribute("views", "/views/admin/orderDetail/OrderIsReturn.jsp");
		return "index";
	}
	
	@GetMapping("admin/deleteOrderIsReturned/{id}")
	public String deleteOrderIsReturned(@PathVariable("id")Integer id) {
		OrderDetail orderDetail=this.orderDetailRepo.getOne(id);
		this.orderDetailRepo.delete(orderDetail);
		return "redirect:/admin/orderIsReturned";
	}
	
	@GetMapping("receiveDetail/{id}/{idDetail}/{idPro}")
	public String receiveDetail(
			@PathVariable("id")Order order,
			@PathVariable("idDetail")OrderDetail orderDetail1,
			@PathVariable("idPro")Product product,
			OrderDetail orderDetail
			) {
		
		
		
		orderDetail.setId(orderDetail1.getId());
		orderDetail.setPrice(orderDetail1.getPrice());
		orderDetail.setProduct(product);
		orderDetail.setOrder(order);
		orderDetail.setStatus(3);
		
		this.orderDetailRepo.save(orderDetail);
		
		
		order.setStatus(3);
		order.setCreatedDate(order.getCreatedDate());
		order.setUser(order.getUser());
		this.orderRepo.save(order);
		//nhận một đơn hàng trong nhiều đơn hàng đặt
		return "redirect:/orderedProduct3";
	}
}
