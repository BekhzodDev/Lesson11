package task1.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;
import task1.task1.entity.Supplier;
import task1.task1.payload.Result;
import task1.task1.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public Result addSupplier(Supplier supplier){
        if (supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber()))
            return new Result("Bunday Telefon raqamli taminotchi uje mavjud", false);
        Supplier supplier1=new Supplier();
        supplier1.setName(supplier.getName());
        supplier1.setActive(supplier.isActive());
        supplier1.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(supplier1);
        return new Result("Yangi taminotchi saqlandi", true);
    }
    public List<Supplier> getSupplierList(){
        return supplierRepository.findAll();
    }
    public Supplier getSupplier(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()) return null;
        return optionalSupplier.get();
    }
    public Result editSupplier(Integer id, Supplier supplier){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return new Result("Bunday taminotchi mavjud emas", false);
        if (supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber()))
            return new Result("Bunday Telefon raqamli taminotchi uje mavjud", false);
        Supplier supplier1= optionalSupplier.get();
        if (supplier.getName()!=null)
        supplier1.setName(supplier.getName());
        supplier1.setActive(supplier.isActive());
        if (supplier.getPhoneNumber()!=null)
        supplier1.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(supplier1);
        return new Result("Taminotchi taxrirlandi", true);
    }
    public Result deleteSupplier(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return new Result("Bunday taminotchi mavjud emas", false);
        supplierRepository.deleteById(id);
        return new Result("Taminotchi o'chirildi", true);
    }

}
