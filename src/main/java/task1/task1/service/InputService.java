package task1.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task1.task1.entity.Currency;
import task1.task1.entity.Input;
import task1.task1.entity.Supplier;
import task1.task1.entity.Warehouse;
import task1.task1.payload.InputDto;
import task1.task1.payload.Result;
import task1.task1.repository.CurrencyRepository;
import task1.task1.repository.InputRepository;
import task1.task1.repository.SupplierRepository;
import task1.task1.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public Result addInput(InputDto inputDto) {

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor bazada mavjud emas", false);
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Bunday taminotchi bazada mavjud emas", false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyeId());
        if (!optionalCurrency.isPresent())
            return new Result("Bunday pul birligi bazada mavjud emas", false);
        if (inputRepository.existsBySupplierIdAndFactureNumber(inputDto.getSupplierId(), inputDto.getFactureNumber()))
            return new Result("Ushbu taminotchidan shu faktura nomeri bazada mavjud", false);

        Input input = new Input();
        input.setDate(Timestamp.from(Instant.now()));
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setFactureNumber(input.getFactureNumber());
        input.setCode(Integer.toString(inputRepository.findAll().size() + 1));
        inputRepository.save(input);
        return new Result("Omborga yangi kirim qabul qilindi", true);
    }
    public List<Input> getInputs(){
        return inputRepository.findAll();
    }
    public Input getInput(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) return null;
        return optionalInput.get();
    }
    public Result editInput(Integer id, InputDto inputDto){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new Result("Bunday kirim bazada mavjud emas", false);
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor bazada mavjud emas", false);
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Bunday taminotchi bazada mavjud emas", false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyeId());
        if (!optionalCurrency.isPresent())
            return new Result("Bunday pul birligi bazada mavjud emas", false);
        if (inputRepository.existsBySupplierIdAndFactureNumber(inputDto.getSupplierId(), inputDto.getFactureNumber()))
            return new Result("Ushbu taminotchidan shu faktura nomeri bazada mavjud", false);

        Input input = optionalInput.get();
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setFactureNumber(input.getFactureNumber());
        inputRepository.save(input);
        return new Result("Kirim taxrirlandi", true);
    }
    public Result deleteInput(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new Result("Bunday kirim bazada mavjud emas", false);
        inputRepository.deleteById(id);
        return new Result("Kirim o'chirildi", true);
    }


}
