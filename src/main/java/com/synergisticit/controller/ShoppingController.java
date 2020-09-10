package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.synergisticit.domain.Category;
import com.synergisticit.domain.Customer;
import com.synergisticit.domain.Order_Table;
import com.synergisticit.domain.Payment;
import com.synergisticit.domain.Product;
import com.synergisticit.repository.CategoryRepository;
import com.synergisticit.repository.CustomerRepository;
import com.synergisticit.repository.Order_TableRepository;
import com.synergisticit.repository.PaymentRepository;
import com.synergisticit.repository.ProductRepository;

@Controller
@RequestMapping("/shopping")
public class ShoppingController {
	
	private CategoryRepository catRepo;
	private ProductRepository prodRepo;
	private CustomerRepository custRepo;
	private Order_TableRepository ordRepo;
	private PaymentRepository payRepo;
	
	@Autowired
	public ShoppingController(CategoryRepository catRepo, ProductRepository prodRepo, 
								CustomerRepository custRepo, Order_TableRepository ordRepo, 
								PaymentRepository payRepo) {
		this.catRepo = catRepo;
		this.prodRepo = prodRepo;
		this.custRepo = custRepo;
		this.ordRepo = ordRepo;
		this.payRepo = payRepo;
	}
	
	@ModelAttribute(name = "category")
	public Category category() {
		return new Category();
	}
	
	@ModelAttribute(name = "product")
	public Product product() {
		return new Product();
	}
	
	@ModelAttribute(name = "customer")
	public Customer customer() {
		return new Customer();
	}
	
	@ModelAttribute(name = "order_table")
	public Order_Table order_table() {
		return new Order_Table();
	}
	
	@ModelAttribute(name = "payment")
	public Payment payment() {
		return new Payment();
	}
	
	@GetMapping
	public String shopping(Model model) {
		System.out.println("@ShoppingController.shopping( ).................");
		
		refresh(model);
		
		return "shopping";
	}
	
	@PostMapping("/insert_category")
	public String insertCategory(@ModelAttribute Category category, Model model) {
		System.out.println("@ShoppingController.insertCategory( ).................");
		
		catRepo.save(category);
		
		refresh(model);
		
		return "shopping";
	}
	
	@PostMapping("/update_category")
	public String updateCategory(@ModelAttribute Category category, Model model) {
		System.out.println("@ShoppingController.updateCategory( ).................");
		
		catRepo.update(category);
		
		refresh(model);
		
		return "shopping";
	}
	
	@RequestMapping("/delete_category")
	public String deleteCategory(@RequestParam int cat_id, Model model) {
		System.out.println("@ShoppingController.deleteCategory( )................." + cat_id);
		
		catRepo.delete(cat_id);
		
		refresh(model);
		
		return "shopping";
	}
	
	@PostMapping("/insert_product")
	public String insertProduct(@ModelAttribute Product product, Model model) {
		System.out.println("@ShoppingController.insertProduct( ).................");
		
		prodRepo.save(product);
		
		refresh(model);
		
		return "shopping";
	}
	
	@PostMapping("/update_product")
	public String updateProduct(@ModelAttribute Product product, Model model) {
		System.out.println("@ShoppingController.updateProduct( ).................");
		
		prodRepo.update(product);
		
		refresh(model);
		
		return "shopping";
	}
	
	@RequestMapping("/delete_product")
	public String deleteProduct(@RequestParam int prod_id, @RequestParam String prod_name, Model model) {
		System.out.println("@ShoppingController.deleteProduct()................." + prod_id + " " + prod_name);
		
		prodRepo.delete(prod_id, prod_name);
		
		refresh(model);
		
		return "shopping";
	}
	
	@PostMapping("/insert_customer")
	public String insertCustomer(@ModelAttribute Customer customer, Model model) {
		System.out.println("@ShoppingController.insertCustomer( ).................");
		
		custRepo.save(customer);
		
		refresh(model);
		
		return "shopping";
	}
	
	@PostMapping("/update_customer")
	public String updateCustomer(@ModelAttribute Customer customer, Model model) {
		System.out.println("@ShoppingController.updateCustomer( ).................");
		
		custRepo.update(customer);
		
		refresh(model);
		
		return "shopping";
	}
	
	@RequestMapping("/delete_customer")
	public String deleteCustomer(@RequestParam int customer_id, @RequestParam String first_name, @RequestParam String last_name, Model model) {
		System.out.println("@ShoppingController.deleteCustomer()................." + customer_id + " " + first_name + " " + last_name);
		
		custRepo.delete(customer_id, first_name, last_name);
		
		refresh(model);
		
		return "shopping";
	}
	
	@PostMapping("/insert_order_table")
	public String insertOrderTable(@ModelAttribute Order_Table order, Model model) {
		System.out.println("@ShoppingController.insertOrderTable( ).................");
		
		ordRepo.save(order);
		
		refresh(model);
		
		return "shopping";
	}
	
	@PostMapping("/update_order_table")
	public String updateOrderTable(@ModelAttribute Order_Table order, Model model) {
		System.out.println("@ShoppingController.updateOrderTable( ).................");
		
		ordRepo.update(order);
		
		refresh(model);
		
		return "shopping";
	}
	
	@RequestMapping("/delete_order_table")
	public String deleteOrderTable(@RequestParam int order_id, @RequestParam int customer_id, Model model) {
		System.out.println("@ShoppingController.deleteOrderTable()................." + order_id + " " + customer_id);
		
		ordRepo.delete(order_id, customer_id);
		
		refresh(model);
		
		return "shopping";
	}
	
	@PostMapping("/insert_payment")
	public String insertPayment(@ModelAttribute Payment payment, Model model) {
		System.out.println("@ShoppingController.insertPayment( ).................");
		
		payRepo.save(payment);
		
		refresh(model);
		
		return "shopping";
	}
	
	@PostMapping("/update_payment")
	public String updatePayment(@ModelAttribute Payment payment, Model model) {
		System.out.println("@ShoppingController.updatePayment( ).................");
		
		payRepo.update(payment);
		
		refresh(model);
		
		return "shopping";
	}
	
	@RequestMapping("/delete_payment")
	public String deletePayment(@RequestParam int payment_id, @RequestParam int order_id, Model model) {
		System.out.println("@ShoppingController.deleteOrderTable()................." + payment_id + " " + order_id);
		
		payRepo.delete(payment_id, order_id);
		
		refresh(model);
		
		return "shopping";
	}
	
	protected void refresh(Model model) {
		List<Category> listOfCat = catRepo.findAll();
		List<Product> listOfProd = prodRepo.findAll();
		List<Customer> listOfCust = custRepo.findAll();
		List<Order_Table> listOfOrd = ordRepo.findAll();
		List<Payment> listOfPay = payRepo.findAll();
		
		model.addAttribute("categoryList", listOfCat);
		model.addAttribute("productList", listOfProd);
		model.addAttribute("customerList", listOfCust);
		model.addAttribute("orderList", listOfOrd);
		model.addAttribute("paymentList", listOfPay);
	}
}