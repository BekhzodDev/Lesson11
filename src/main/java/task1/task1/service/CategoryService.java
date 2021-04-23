package task1.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task1.task1.entity.Category;
import task1.task1.payload.Categorydto;
import task1.task1.payload.Result;
import task1.task1.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategory(Categorydto categorydto) {
        Category category = new Category();
        category.setName(categorydto.getName());
        if (categorydto.getParentCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categorydto.getParentCategoryId());
            if (!optionalCategory.isPresent())
                return new Result("Bunday ota category mavjud emas", false);
            category.setParentCategory(optionalCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Category saqlandi", true);
    }

    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return null;
        return optionalCategory.get();
    }

    public Result editCategory(Integer id, Categorydto categorydto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new Result("Bunday Category ID mavjud emas", false);
        Category category = optionalCategory.get();
        category.setName(categorydto.getName());
        if (categorydto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categorydto.getParentCategoryId());
            if (!optionalCategory.isPresent())
                return new Result("Bunday ota category mavjud emas", false);
            category.setParentCategory(optionalParentCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Category taxrirlandi", true);
    }

    public Result deleteCategory(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new Result("Bunday Category ID mavjud emas", false);
        categoryRepository.deleteById(id);
        return new Result("Category o'chirildi", true);
    }


}
