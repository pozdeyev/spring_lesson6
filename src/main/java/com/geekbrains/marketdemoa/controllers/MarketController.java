package com.geekbrains.marketdemoa.controllers;

import com.geekbrains.marketdemoa.entites.Category;
import com.geekbrains.marketdemoa.entites.Item;
import com.geekbrains.marketdemoa.repositories.specifications.ItemSpecifications;
import com.geekbrains.marketdemoa.services.CategoryService;
import com.geekbrains.marketdemoa.services.ItemService;
import com.geekbrains.marketdemoa.utils.ItemFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MarketController {
    private ItemService itemService;
    private CategoryService categoryService;

    @Autowired
    public MarketController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        int pageIndex = 0;
        if (params.containsKey("p")) {
            pageIndex = Integer.parseInt(params.get("p")) - 1;
        }
        Pageable pageRequest = PageRequest.of(pageIndex, 10);
        ItemFilter itemFilter = new ItemFilter(params);
        Page<Item> page = itemService.findAll(itemFilter.getSpec(), pageRequest);

        List<Category> categories = categoryService.getAll();
        model.addAttribute("filtersDef", itemFilter.getFilterDefinition());
        model.addAttribute("categories", categories);
        model.addAttribute("page", page);
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String editItemForm(Model model, @PathVariable Long id) {
        Item item = itemService.findById(id);
        List<Category> categories = categoryService.getAll();
        model.addAttribute("item", item);
        model.addAttribute("categories", categories);
        return "edit_item";
    }

    @PostMapping("/edit")
    public String saveItem(@ModelAttribute(name = "item") Item item) {
        itemService.save(item);
        return "redirect:/";
    }
}