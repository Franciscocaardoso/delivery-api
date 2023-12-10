package br.com.senior.delivery.controller;

import br.com.senior.delivery.domain.customer.CustomerRegistrationData;
import br.com.senior.delivery.domain.customer.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    CustomerService clientService;

    @PostMapping
    public void register(@RequestBody @Valid CustomerRegistrationData data){
        clientService.addCustomer(data);
    }
}