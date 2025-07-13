package com.study.Spring.controller;

import com.study.Spring.dto.ProductResponseDto;
import com.study.Spring.entity.Product;
import com.study.Spring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductViewController {

    private final ProductService productService;

    @GetMapping("/products")
    public String showProductList(Model model) {
        List<ProductResponseDto> products = productService.getAll();
        model.addAttribute("products", products);
        return "products"; // templates/products.html
    }

    @GetMapping("/products/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new ProductResponseDto(null, "", 0));
        return "product_form"; // templates/product_form.html
    }

    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ProductResponseDto product = productService.findById(id);
        model.addAttribute("product", product);
        return "product_edit_form"; // templates/product_edit_form.html
    }
}