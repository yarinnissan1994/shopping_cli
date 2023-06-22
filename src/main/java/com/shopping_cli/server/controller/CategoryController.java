package com.shopping_cli.server.controller;

import com.shopping_cli.server.model.Category;
import com.shopping_cli.server.model.User;
import com.shopping_cli.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoryController {

     @Autowired
     private CategoryService categoryService;


    @GetMapping("")
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id){
        return categoryService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,  "Category not found!"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createCategory(@RequestBody Category category){
        categoryService.save(category);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateCategory(@PathVariable int id, @RequestBody Category category) {
        if (!categoryService.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!");
        else
            categoryService.save(category);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {
        if (!categoryService.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!");
        else
            categoryService.deleteById(id);
    }
}
