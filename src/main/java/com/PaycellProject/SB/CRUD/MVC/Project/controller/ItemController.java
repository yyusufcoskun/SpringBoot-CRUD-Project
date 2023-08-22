package com.PaycellProject.SB.CRUD.MVC.Project.controller;

import com.PaycellProject.SB.CRUD.MVC.Project.service.ItemService;
import com.PaycellProject.SB.CRUD.MVC.Project.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "itemName", "asc", model);
    }

    @GetMapping("/add")
    public String showNewCourseForm(Model model) {
        Items item = new Items();
        model.addAttribute("item", item);
        return "new_item";
    }

    @PostMapping("/save")
    public String saveItem(@ModelAttribute("item") Items item) {
        itemService.saveItem(item);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        Items item = itemService.getItemById(id);
        model.addAttribute("item", item);

        return "update_item";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable (value = "id") long id) {

        this.itemService.deleteItemById(id);
        return "redirect:/";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Items> page = itemService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Items> listItems = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listItems", listItems);
        return "index";
    }
}