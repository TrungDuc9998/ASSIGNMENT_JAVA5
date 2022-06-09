package ASSIGNMENT_JAVA5.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ASSIGNMENT_JAVA5.entities.Account;
import ASSIGNMENT_JAVA5.repositories.AccountRepository;
import ASSIGNMENT_JAVA5.utils.EncryptUtil;

@Controller
public class LoginController {
	
	@Autowired
	private AccountRepository accRepo;
	
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping("/login")
	public String getLoginForm(Model model) {
		return "admin/accounts/login";
	}
	
	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email,
			@RequestParam("password")String password
			) {
		HttpSession session=request.getSession();
		Account account=this.accRepo.findByEmail(email);
		
		System.out.println("-----------------email:"+email);
		boolean checkPW=EncryptUtil.verify(password, account.getPassword());
		if(!checkPW) {
			System.out.println("sai email hoặc mật khẩu");
			return "redirect:/login";
		}else {
			session.setAttribute("account", account);
			return "redirect:/home";
		}
		
		
		
	}
	
	@GetMapping("/logout")
	public String logout() {
		HttpSession session=request.getSession();
		Account user=(Account) session.getAttribute("account");	
		if (user != null) {  
		    session.invalidate();
		    return "redirect:/login";
		}else {
			System.out.println("Đăng xuất không thành công!");
			return "redirect:/home";
		}
		
	}
}
