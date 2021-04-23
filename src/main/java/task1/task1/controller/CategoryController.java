package task1.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task1.task1.entity.Category;
import task1.task1.payload.Categorydto;
import task1.task1.payload.Result;
import task1.task1.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

@PostMapping
    public Result addCategory(@RequestBody Categorydto categorydto){
    return categoryService.addCategory(categorydto);
}
@GetMapping
    public List<Category> getCategories(){
    return categoryService.getCategoryList();
}
@GetMapping("/{id}")
    public Category getCategory(@PathVariable Integer id){
    return categoryService.getCategory(id);
}
@PutMapping("/{id}")
    public Result editCategory(@PathVariable Integer id, @RequestBody Categorydto categorydto){
    return categoryService.editCategory(id,categorydto);
}
@DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id){
    return categoryService.deleteCategory(id);
}
}
