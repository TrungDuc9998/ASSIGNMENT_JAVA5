package ASSIGNMENT_JAVA5.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ASSIGNMENT_JAVA5.entities.Account;
import ASSIGNMENT_JAVA5.entities.Category;
import ASSIGNMENT_JAVA5.entities.OrderDetail;
import ASSIGNMENT_JAVA5.entities.Product;
import ASSIGNMENT_JAVA5.repositories.AccountRepository;
import ASSIGNMENT_JAVA5.repositories.CategoryRepository;
import ASSIGNMENT_JAVA5.repositories.OrderDetailRepository;
import ASSIGNMENT_JAVA5.repositories.ProductRepository;
import ASSIGNMENT_JAVA5.utils.EncryptUtil;
import ASSIGNMENT_JAVA5.utils.UploadFileUtils;

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
	
	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private UploadFileUtils uploadUtil;
	
	
//	public void index(Model model) {
//		HttpSession session=request.getSession();
//		Account account=(Account)session.getAttribute("account");
//		if(account!=null) {
//			model.addAttribute("account", account.getFullname());
//		}
//		
//	}
	
	
	
	
	@RequestMapping("home")
	public String home(Model model,@RequestParam("p") Optional<Integer> p) {
//		index(model);
		HttpSession session=request.getSession();
		Account account=(Account)session.getAttribute("account");
		if(account!=null) {
			model.addAttribute("account", account);
		}
		Pageable pageable = PageRequest.of(p.orElse(0), 8);
		Page<Product> listProduct = this.productRepo.findAll(pageable);
		
		List<Category>listCategoey=this.cateRepo.findAll();
		model.addAttribute("listCategory", listCategoey);
//		List<Product>listProduct=this.productRepo.findAll();
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("views", "/views/home.jsp");
		return "index";
	}
	

	
	@RequestMapping("findProductByCategoryId/{id}")
	public String findProductByCategoryId(@PathVariable("id")Integer id,Model model) {
//		index(model);
		System.out.println("categoryId:"+id);
		
		List<Product>listProduct=this.productRepo.findByIdPro(id);
		
		List<Category>listCategoey=this.cateRepo.findAll();
		model.addAttribute("listCategory", listCategoey);
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("views", "/views/home.jsp");
		return "index";
	}
	
	@GetMapping("register")
	public String register(Model model, @ModelAttribute("account") Account acc) {
		
		return "account/register";
	}
	
	@PostMapping("register")
	public String registerStore(Model model, @Valid @ModelAttribute("account") Account account,
			@RequestParam("upload_file") MultipartFile uploadFile) {
		account.setPhoto(uploadFile.getOriginalFilename());
		String hashedPassword=EncryptUtil.hash(account.getPassword());
		account.setPassword(hashedPassword);
		File a = this.uploadUtil.handleUpLoadFile(uploadFile);

		this.accountRepo.save(account);
		return "redirect:/login";
	}
	
	
}
