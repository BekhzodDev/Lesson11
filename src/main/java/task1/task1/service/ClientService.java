package task1.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task1.task1.entity.Client;
import task1.task1.payload.Result;
import task1.task1.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
@Autowired
ClientRepository clientRepository;

    public Result addclient(Client client){
        if (clientRepository.existsByPhoneNumber(client.getPhoneNumber()))
            return new Result("Bunday Telefon raqamli taminotchi uje mavjud", false);
        Client client1=new Client();
        client1.setName(client.getName());
        client1.setActive(client.isActive());
        client1.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(client1);
        return new Result("Yangi taminotchi saqlandi", true);
    }
    public List<Client> getclientList(){
        return clientRepository.findAll();
    }
    public Client getclient(Integer id){
        Optional<Client> optionalclient = clientRepository.findById(id);
        if (!optionalclient.isPresent()) return null;
        return optionalclient.get();
    }
    public Result editclient(Integer id, Client client){
        Optional<Client> optionalclient = clientRepository.findById(id);
        if (!optionalclient.isPresent())
            return new Result("Bunday taminotchi mavjud emas", false);
        if (clientRepository.existsByPhoneNumber(client.getPhoneNumber()))
            return new Result("Bunday Telefon raqamli taminotchi uje mavjud", false);
        Client client1= optionalclient.get();
        if (client.getName()!=null)
            client1.setName(client.getName());
        client1.setActive(client.isActive());
        if (client.getPhoneNumber()!=null)
            client1.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(client1);
        return new Result("Taminotchi taxrirlandi", true);
    }
    public Result deleteclient(Integer id){
        Optional<Client> optionalclient = clientRepository.findById(id);
        if (!optionalclient.isPresent())
            return new Result("Bunday taminotchi mavjud emas", false);
        clientRepository.deleteById(id);
        return new Result("Taminotchi o'chirildi", true);
    }


}
