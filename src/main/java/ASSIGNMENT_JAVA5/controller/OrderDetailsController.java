package ASSIGNMENT_JAVA5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ASSIGNMENT_JAVA5.entities.Order;
import ASSIGNMENT_JAVA5.entities.OrderDetail;
import ASSIGNMENT_JAVA5.repositories.OrderDetailRepository;
import ASSIGNMENT_JAVA5.repositories.OrderRepository;

@Controller
public class OrderDetailsController {
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	
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
		
		return "redirect:/orderedProduct";
	}
	
	@GetMapping("admin/ReturnRequest")
	public String returnRequest(Model model) {
		List<OrderDetail>listOrderDetail=this.orderDetailRepo.FindListOrderDetailByStatus(6);
		model.addAttribute("listOrder", listOrderDetail);
		model.addAttribute("views", "/views/admin/orderDetail/ReturnRequest.jsp");
		return "index";
	}
	
	@RequestMapping("admin/verifyRequest/{id}/{idOrder}")
	public String verifyRequest(
			@PathVariable("id")Integer id,
			@PathVariable("idOrder")Integer idOrder
			) {
		List<OrderDetail>listOdl=this.orderDetailRepo.FindListOrderDetailByOrderId(idOrder);
		if(listOdl.size()>1) {
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
			double tong=0;
			for (OrderDetail orderDetail2 : listOrderDetail) {
				tong+=orderDetail2.getPrice();
			}
			System.out.println("--------------- tổng tiền sau khi huỷ một đơn hàng trong hoá đơn:"+tong);
			Order order=this.orderRepo.getOne(idOrder);
			order.setTotal(tong);
			this.orderRepo.save(order);
		}else {
			OrderDetail orderDetail=this.orderDetailRepo.getOne(id);
			orderDetail.setId(orderDetail.getId());
			orderDetail.setOrder(orderDetail.getOrder());
			orderDetail.setQuantity(orderDetail.getQuantity());
			orderDetail.setPrice(orderDetail.getPrice());
			orderDetail.setStatus(5);
			orderDetail.setNote(orderDetail.getNote());
			orderDetail.setImage(orderDetail.getImage());
			this.orderDetailRepo.save(orderDetail);
			Order order=this.orderRepo.getOne(idOrder);
			order.setTotal(0);
			order.setStatus(5);
			this.orderRepo.save(order);
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
		return "index";
	}
}
