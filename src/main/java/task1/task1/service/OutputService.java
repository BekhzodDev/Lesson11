package task1.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task1.task1.entity.Currency;
import task1.task1.entity.Output;
import task1.task1.entity.Client;
import task1.task1.entity.Warehouse;
import task1.task1.payload.OutputDto;
import task1.task1.payload.OutputDto;
import task1.task1.payload.Result;
import task1.task1.repository.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public Result addOutput(OutputDto outputDto) {

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor bazada mavjud emas", false);
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("Bunday mijoz bazada mavjud emas", false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyeId());
        if (!optionalCurrency.isPresent())
            return new Result("Bunday pul birligi bazada mavjud emas", false);
        if (outputRepository.existsByClientIdAndFactureNumber(outputDto.getClientId(), outputDto.getFactureNumber()))
            return new Result("Ushbu mijozdan shu faktura nomeri bazada mavjud", false);

        Output output = new Output();
        output.setDate(Timestamp.from(Instant.now()));
        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        output.setFactureNumber(output.getFactureNumber());
        output.setCode(Integer.toString(outputRepository.findAll().size() + 1));
        outputRepository.save(output);
        return new Result("Omborga yangi Chiqim qabul qilindi", true);
    }
    public List<Output> getOutputs(){
        return outputRepository.findAll();
    }
    public Output getOutput(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) return null;
        return optionalOutput.get();
    }
    public Result editOutput(Integer id, OutputDto outputDto){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new Result("Bunday Chiqim bazada mavjud emas", false);
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday ombor bazada mavjud emas", false);
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("Bunday mijoz bazada mavjud emas", false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyeId());
        if (!optionalCurrency.isPresent())
            return new Result("Bunday pul birligi bazada mavjud emas", false);
        if (outputRepository.existsByClientIdAndFactureNumber(outputDto.getClientId(), outputDto.getFactureNumber()))
            return new Result("Ushbu mijozdan shu faktura nomeri bazada mavjud", false);

        Output output = optionalOutput.get();
        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        output.setFactureNumber(output.getFactureNumber());
        outputRepository.save(output);
        return new Result("Chiqim taxrirlandi", true);
    }
    public Result deleteOutput(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new Result("Bunday Chiqim bazada mavjud emas", false);
        outputRepository.deleteById(id);
        return new Result("Chiqim o'chirildi", true);
    }
}
