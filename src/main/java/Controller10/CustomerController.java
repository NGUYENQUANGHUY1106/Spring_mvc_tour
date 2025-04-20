package Controller10;


import Dto.Request.CustomerRequest;
import Dto.Response.CustomerResponse;
import Entity.Customer;
import MapperData.CustomerMapper;
import Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {



    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public CustomerResponse add(@RequestBody CustomerRequest customerRequest) {
        return customerService.add(customerRequest);
    }
    @GetMapping("/{idUser}")
    public CustomerResponse get(@PathVariable Long idUser) {
        return customerService.get(idUser);
    }
}
