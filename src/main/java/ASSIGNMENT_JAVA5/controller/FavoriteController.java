package ASSIGNMENT_JAVA5.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ASSIGNMENT_JAVA5.entities.Account;
import ASSIGNMENT_JAVA5.entities.Favorite;
import ASSIGNMENT_JAVA5.entities.Product;
import ASSIGNMENT_JAVA5.repositories.FavoriteRepository;
import ASSIGNMENT_JAVA5.repositories.ProductRepository;

@Controller
public class FavoriteController {
	
	@Autowired
	private FavoriteRepository favoriteRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping("favorite/{id}")
	public String favorite(
			@PathVariable("id")Integer id,
			Favorite favorite
			) {
		HttpSession session=request.getSession();
		Account account=(Account)session.getAttribute("account");
		if(account!=null) {
			Product product=this.productRepo.getOne(id);
			favorite.setIsLike(1);
			favorite.setAccount(account);
			favorite.setLikeDate(new Date());
			favorite.setProduct(product);
			this.favoriteRepo.save(favorite);
			return "forward:/productDetails/"+id;
		}else {
			return "redirect:/home";
		}
		
	}
	
	@GetMapping("favoriteinsert/{id}")
	public String favoriteInsert(@PathVariable("id")Integer id,Favorite favorite) {
		HttpSession session=request.getSession();
		Account acc=(Account)session.getAttribute("account");
		if(acc!=null) {
			Product product=this.productRepo.getOne(id);
			favorite.setIsLike(1);
			
			 favorite.setLikeDate(new Date());
			 favorite.setAccount(acc);
			 favorite.setProduct(product);
			 this.favoriteRepo.save(favorite);
		}else {
			System.out.println("---  chưa đăng nhập ------");
		}
		 
		
		
		return "forward:/productDetails/"+id;
	}
	
	@GetMapping("favoriteupdate/{id}/{like}")
	public String favoriteUpdate(@PathVariable("id")Integer id,Favorite favorite,@PathVariable("like")Integer like) {
		HttpSession session=request.getSession();
		Account acc=(Account)session.getAttribute("account");
		if(acc!=null) {
			Product product=this.productRepo.getOne(id);
			Favorite favorites=this.favoriteRepo.findFavoriteByProductId(id);
			favorite.setId(favorites.getId());
			if(like==0) {
				favorite.setIsLike(1);
			}else {
				favorite.setIsLike(0);
			}
			
			
			 favorite.setLikeDate(new Date());
			 favorite.setAccount(acc);
			 favorite.setProduct(product);
			 this.favoriteRepo.save(favorite);
		}else {
			System.out.println("---  chưa đăng nhập ------");
		}
		 
		
		
		return "forward:/productDetails/"+id;
	}
	
	@GetMapping("listfavoritebyaccountid")
	public String listfavoritebyaccountid(Model model) {
		HttpSession session=request.getSession();
		Account acc=(Account) session.getAttribute("account");
		if(acc!=null) {
			List<Favorite>favorites=this.favoriteRepo.findFavoriteByAccountId(acc.getId());
			model.addAttribute("listFavorite", favorites);
		}else {
			System.out.println("bạn chưa đăng nhập");
		}
		
		model.addAttribute("views", "/views/favorite/favoriteProduct.jsp");
		return "index";
	}
}
