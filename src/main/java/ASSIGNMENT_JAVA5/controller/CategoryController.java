package ASSIGNMENT_JAVA5.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ASSIGNMENT_JAVA5.entities.Category;
import ASSIGNMENT_JAVA5.entities.Product;
import ASSIGNMENT_JAVA5.repositories.CategoryRepository;
import ASSIGNMENT_JAVA5.repositories.ProductRepository;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
	@Autowired
	private CategoryRepository cateRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("create")
	public String create(Model model,@ModelAttribute("category") Category category) {
		model.addAttribute("views", "/views/admin/category/create.jsp");
		return "index";
	}
	
	@GetMapping("show")
	public String show(Model model) {
		List<Category>listCategory=cateRepo.findAll();
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("views", "/views/admin/category/show.jsp");
		return"index";
	}
	
	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id")Integer id,Model model,@ModelAttribute("category") Category cate) {
		cate=cateRepo.getOne(id);
		
		model.addAttribute("views", "/views/admin/category/edit.jsp");
		model.addAttribute("category",cate);
		return "index";
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Category category) {
		this.cateRepo.delete(category);
		 return "redirect:/admin/categories/show";
	}
	
	@PostMapping("store")
	public String store(Model model,Category category) {
		this.cateRepo.save(category);
		
		return "redirect:/admin/categories/show";
	}
	
	@PostMapping("update/{id}")
	public String update(Category category,@PathVariable("id") int id) {
		System.out.println(category.getId());
		System.out.println(category.getName());
		category.setId(id);
		cateRepo.save(category);
		return "redirect:/admin/categories/show";
	
	}
	
	
	
}