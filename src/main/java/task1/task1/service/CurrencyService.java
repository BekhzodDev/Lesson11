package task1.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task1.task1.entity.Currency;
import task1.task1.payload.Result;
import task1.task1.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrency(Currency currency){
     if (currencyRepository.existsByName(currency.getName()))
         return new Result("Bunday currency uje mavjud", false);
     Currency currency1 = new Currency();
     currency1.setName(currency.getName());
     currency1.setActive(currency.isActive());
     currencyRepository.save(currency1);
     return new Result("Currency saqlandi", true);
    }
    public List<Currency> getCurrencyList(){
        return currencyRepository.findAll();
    }
    public Currency getCurrency(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()) return null;
        return optionalCurrency.get();
    }
    public Result editCurrency(Integer id, Currency currency){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent())
            return new Result("Bunday currency mavjud emas", false);
        if (currencyRepository.existsByName(currency.getName()))
            return new Result("Bunday currency uje mavjud", false);
        Currency currency1 = optionalCurrency.get();
        if (currency.getName()!=null)
        currency1.setName(currency.getName());
        currency1.setActive(currency.isActive());
        currencyRepository.save(currency1);
        return new Result("Currency taxrirlandi", true);
    }
    public Result deleteCurrency(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent())
            return new Result("Bunday currency mavjud emas", false);
        currencyRepository.deleteById(id);
        return new Result("Currency o'chirildi", false);
    }
}
