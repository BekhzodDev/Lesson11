package task1.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task1.task1.entity.Client;
import task1.task1.entity.Supplier;
import task1.task1.payload.Result;
import task1.task1.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;
    @PostMapping
    public Result addClient(@RequestBody Client client){
        return clientService.addclient(client);
    }
    @GetMapping
    public List<Client> getClients(){
        return clientService.getclientList();
    }
    @GetMapping("/{id}")
    public Client getSupplier(@PathVariable Integer id){
        return clientService.getclient(id);
    }
    @PutMapping("/{id}")
    public Result editClient(@PathVariable Integer id, @RequestBody Client client){
        return clientService.editclient(id, client);
    }
    @DeleteMapping("/{id}")
    public Result deleteSupplier(@PathVariable Integer id){
        return clientService.deleteclient(id);
    }
}
