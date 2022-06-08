package ASSIGNMENT_JAVA5.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ASSIGNMENT_JAVA5.entities.Account;
import ASSIGNMENT_JAVA5.entities.Product;
import ASSIGNMENT_JAVA5.entities.ProductModel;
import ASSIGNMENT_JAVA5.repositories.AccountRepository;
import ASSIGNMENT_JAVA5.utils.EncryptUtil;
import ASSIGNMENT_JAVA5.utils.UploadFileUtils;

@Controller
@RequestMapping("/admin/accounts")
public class AccountController {

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private UploadFileUtils uploadUtil;
	
	@Autowired
	HttpServletRequest request;

	
	
	
	
	
	@GetMapping("create")
	public String create(Model model, @ModelAttribute("account") Account acc) {
		model.addAttribute("views", "/views/admin/accounts/create.jsp");
		return "index";
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @ModelAttribute("account") Account acc, @PathVariable("id") Integer id) {

		acc = accountRepo.getOne(id);

		System.out.println("----id:" + acc);
		model.addAttribute("views", "/views/admin/accounts/edit.jsp");
		model.addAttribute("account", acc);
//		acc.setPhoto(uploadFile.getOriginalFilename());
//		File a=this.uploadUtil.handleUpLoadFile(uploadFile);
		return "index";
	}

	@GetMapping("show")
	public String show(Model model, Account account,@RequestParam("p") Optional<Integer> p) {
		
		Pageable pageable=PageRequest.of(p.orElse(1), 3);
		Page<Account>page=this.accountRepo.findAll(pageable);
		model.addAttribute("page", page);
		
		
		model.addAttribute("views", "/views/admin/accounts/show.jsp");
		model.addAttribute("page", page);
		return "index";
	}

	@GetMapping("delete/{id}")
	public String delete(Model model, @PathVariable("id") Account account) {
		this.accountRepo.delete(account);
		return "redirect:/admin/accounts/show";
	}

	@RequestMapping("store")
	public String store(Model model, @Valid @ModelAttribute("account") Account account,
			@RequestParam("upload_file") MultipartFile uploadFile,BindingResult result) {
//		if(result.hasErrors()==true) {
//			System.out.println("lỗi");
//			model.addAttribute("message", "lỗi");
////			model.addAttribute("views", "/views/admin/accounts/create.jsp");
//			return "/admin/accounts/create";
//		}else {
//			System.out.println("dúng");
//			return "redirect:/admin/accounts/create";
//
//		}
		account.setPhoto(uploadFile.getOriginalFilename());
		String hashedPassword=EncryptUtil.hash(account.getPassword());
		account.setPassword(hashedPassword);
		File a = this.uploadUtil.handleUpLoadFile(uploadFile);

		this.accountRepo.save(account);

		return "redirect:/admin/accounts/show";
	}

	@PostMapping("update/{id}")
	public String update(@ModelAttribute("account") Account account, @PathVariable("id") int id,
			@RequestParam("upload_file") MultipartFile uploadFile) {
		account.setPhoto(uploadFile.getOriginalFilename());
		Account acc=this.accountRepo.getOne(id);
		File a = this.uploadUtil.handleUpLoadFile(uploadFile);
		account.setId(id);
		account.setPassword(acc.getPassword());
		accountRepo.save(account);
		System.out.println("----------------------------------------------"+acc.getPassword());
		System.out.println(account.getId());
		return "redirect:/admin/accounts/show";
	}
	
	@GetMapping("createAccount")
	public String createAccount(@ModelAttribute("product")ProductModel order) {
		System.out.println("-------------- tạo tài khoản khi thêm hoá đơn -------------");
		System.out.println(order.toString());
		return "redirect:/home";
	}
}
