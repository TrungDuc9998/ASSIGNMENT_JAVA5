package ASSIGNMENT_JAVA5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ASSIGNMENT_JAVA5.entities.OrderDetail;
import ASSIGNMENT_JAVA5.repositories.OrderDetailRepository;

@Controller
public class OrderDetailsController {
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
	@GetMapping("deleteOrderDetail/{id}")
	private String deleteOderDetal(@PathVariable("id")OrderDetail od,OrderDetail orderDetail) {
		orderDetail.setId(od.getId());
		orderDetail.setOrder(od.getOrder());
		orderDetail.setStatus(1);
		orderDetail.setProduct(od.getProduct());
		this.orderDetailRepo.save(orderDetail);
		return "redirect:/cartManagement";
	}
}
