package task1.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task1.task1.entity.Supplier;
import task1.task1.payload.Result;
import task1.task1.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @PostMapping
    public Result addSupplier(@RequestBody Supplier supplier){
        return supplierService.addSupplier(supplier);
    }
    @GetMapping
    public List<Supplier> getSuppliers(){
        return supplierService.getSupplierList();
    }
    @GetMapping("/{id}")
    public Supplier getSupplier(@PathVariable Integer id){
        return supplierService.getSupplier(id);
    }
    @PutMapping("/{id}")
    public Result editSupplier(@PathVariable Integer id, @RequestBody Supplier supplier){
        return supplierService.editSupplier(id, supplier);
    }
    @DeleteMapping("/{id}")
    public Result deleteSupplier(@PathVariable Integer id){
        return supplierService.deleteSupplier(id);
    }
}
