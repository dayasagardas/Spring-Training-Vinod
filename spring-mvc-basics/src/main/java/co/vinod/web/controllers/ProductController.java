package co.vinod.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.vinod.dao.DaoException;
import co.vinod.dao.ProductDao;

@Controller
public class ProductController {

	@Autowired
	ProductDao dao;
	
	// @RequestMapping(path = "/view-product-details", method = RequestMethod.GET)
	@GetMapping("/view-product-details")
	public String viewProductDetailsHandler(Model model, @RequestParam Integer id) throws DaoException {
		model.addAttribute("product", dao.getProduct(id));
		return "product-details";
	}
	
	@RequestMapping(path = "/view-all-products", method = RequestMethod.GET)
	public String viewAllProductsHandler(Model model) throws DaoException {
		model.addAttribute("products", dao.getAllProducts());
		model.addAttribute("title", "List of all products");
		return "show-products";
	}
}





