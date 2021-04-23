package task1.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task1.task1.entity.InputProduct;
import task1.task1.payload.InputProductDto;
import task1.task1.payload.Result;
import task1.task1.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping("/inputproduct")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;

    @PostMapping
    public Result addInputProduct(@RequestBody InputProductDto inputProductDto) {
        return inputProductService.addInputProduct(inputProductDto);
    }

    @GetMapping
    public List<InputProduct> getInputProducts() {
        return inputProductService.getInputProducts();
    }

    @GetMapping("/{id}")
    public InputProduct getInputProduct(@PathVariable Integer id) {
        return inputProductService.getInputProduct(id);
    }

    @PutMapping("/{id}")
    public Result editInputProduct(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto) {
        return inputProductService.editInputProduct(id, inputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteInputProduct(@PathVariable Integer id) {
        return inputProductService.deleteInputProduct(id);
    }
}
