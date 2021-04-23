package task1.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task1.task1.entity.Warehouse;
import task1.task1.payload.Result;
import task1.task1.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addWarehouse(Warehouse warehouse){
      if (warehouseRepository.existsByName(warehouse.getName()))
          return new Result("Bunday nomli ombor uje mavjud", false);
      Warehouse warehouse1=new Warehouse();
      warehouse1.setName(warehouse.getName());
      warehouse1.setActive(warehouse1.isActive());
      warehouseRepository.save(warehouse1);
      return new Result("Yangi ombor saqlandi", true);
    }
    public List<Warehouse> getWarehouseList(){
        return warehouseRepository.findAll();
    }
    public Warehouse getWarehouse(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()) return null;
        return optionalWarehouse.get();
    }
    public Result editWarehouse(Integer id, Warehouse warehouse){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor mavjud emas", false);
        if (warehouseRepository.existsByName(warehouse.getName()))
            return new Result("Bunday nomli ombor uje mavjud", false);
        Warehouse warehouse1= optionalWarehouse.get();
        if (warehouse.getName()!=null)
        warehouse1.setName(warehouse.getName());
        warehouse1.setActive(warehouse1.isActive());
        warehouseRepository.save(warehouse1);
        return new Result("Ombor taxrirlandi", true);
    }
    public Result deleteWarehouse(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor mavjud emas", false);
        warehouseRepository.deleteById(id);
        return new Result("Ombor o'chirildi", true);
    }
}
