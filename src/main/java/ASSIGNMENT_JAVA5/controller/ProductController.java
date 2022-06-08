package ASSIGNMENT_JAVA5.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ASSIGNMENT_JAVA5.entities.Category;

import ASSIGNMENT_JAVA5.entities.Product;

import ASSIGNMENT_JAVA5.repositories.CategoryRepository;

import ASSIGNMENT_JAVA5.repositories.ProductRepository;

import ASSIGNMENT_JAVA5.utils.UploadFileUtils;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
	
	@Autowired
	private ProductRepository proRepo;
	@Autowired
	private CategoryRepository cateRepo;
	
	@Autowired
	private UploadFileUtils upload;

	
	@Autowired
	HttpServletRequest request;
	
	@GetMapping("create")
	public String create(Model model,@ModelAttribute("product")Product product) {
		List<Category>listCategory=this.cateRepo.findAll();
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("views", "/views/admin/product/create.jsp");
		return "index";
	}
	
	@GetMapping("edit/{id}")
	public String edit(Model model,@ModelAttribute("product")Product product,@PathVariable("id") Integer id) {
		product=this.proRepo.getOne(id);
		model.addAttribute("product", product);
//		List<Category>listCategory=this.cateRepo.findAll();
//		model.addAttribute("listCategory", listCategory);
		model.addAttribute("views", "/views/admin/product/edit.jsp");
		return "index";
	}
	
	@RequestMapping("show")
	public String show(Model model) {
		
		String id=request.getParameter("cate_id");
		if(id==null){
			System.out.println("rỗng");
			List<Product>listPro=proRepo.findByIdPro(1);
			model.addAttribute("listProduct", listPro);
		}else {
			System.out.println("---------------------"+id);
			List<Product>listPro=proRepo.findByIdPro(Integer.valueOf(id));
			model.addAttribute("listProduct", listPro);
		}
		
		model.addAttribute("views", "/views/admin/product/show.jsp");
		List<Category>listCategory=this.cateRepo.findAll();
		model.addAttribute("listCategory", listCategory);
		return "index";
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id")Product product) {
		this.proRepo.delete(product);
		return "redirect:/admin/products/show";
	}
	
	@PostMapping("store")
	public String store(@ModelAttribute("product")Product product,@RequestParam("cate_id")Integer id,
			@RequestParam("upload_file_product")MultipartFile file
			
			) {
		
		
		this.upload.handleUpLoadFile(file);
		product.setImage(file.getOriginalFilename());
		Category cate=cateRepo.getOne(id);
		System.out.println(id);
		product.setCategory(cate);
		product.setCreatedDate(new Date());
		this.proRepo.save(product);
		
	
		
		  
		
		
		return "redirect:/admin/products/show";
	}
	
	@PostMapping("update/{id}")
	public String update(Product product,@PathVariable("id") int id,@RequestParam("upload_file_product")MultipartFile file) {
		this.upload.handleUpLoadFile(file);
		System.out.println("--------------"+id);
		System.out.println("--------------"+product.getName());
		System.out.println("--------------"+file.getOriginalFilename());
	
		Product pro=this.proRepo.getOne(id);
		product.setName(product.getName());
		product.setId(id);
		product.setCategory(pro.getCategory());
		product.setCreatedDate(pro.getCreatedDate());
		product.setImage(file.getOriginalFilename());
		this.proRepo.save(product);
		return "redirect:/admin/products/show";
		
	}
}
