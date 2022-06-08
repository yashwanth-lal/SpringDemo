
package com.app.netflixdemoapp.controller;

import com.app.netflixdemoapp.entity.Customer;
import com.app.netflixdemoapp.exceptionhandlers.UserNameAlreadyExists;
import com.app.netflixdemoapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/customerApi")
public class CustomerRestController {
    private CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public String getCustomers(Model model){
        model.addAttribute("customer",customerService.findAll());
        return "list-customers";
    }

    @GetMapping("/customers/{costId}")
    public Customer getCustomer(@PathVariable int costId){

        Customer c = customerService.findById(costId);
        if(c == null){
            throw new RuntimeException("Customer Id not found- "+costId);
        }
        return c;
    }

    @PostMapping("/addCustomer")
    public String addCustomer (@Valid @ModelAttribute Customer c, BindingResult bindingResult){
            if (bindingResult.hasErrors()) {
                return "errorPage";
            }
            if (customerService.findByEmailId(c)) {
                throw new UserNameAlreadyExists("User Already exists with this username");
            }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(c.getPassword());
        c.setPassword(encoded);
        c.setUserId(0);
        c.setRole("User");
        customerService.save(c);
        return "redirect:/movieApi/moviesView";
    }

    @GetMapping("/success")
    public String registerSuccess(){
        return "register-success";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute Customer c){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(c.getPassword());
        c.setPassword(encoded);
        customerService.save(c);
        return "redirect:/customerApi/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable(value="id") int id,Model model){
        Customer c = customerService.findById(id);

        if(c == null){
            throw new RuntimeException("Customer Id not found- "+id);
        }
        customerService.deleteById(id);
        return "redirect:/customerApi/customers";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateFunc(@PathVariable(value="id") int id, Model model){
        Customer c = customerService.findById(id);
        model.addAttribute("customer",c);
        return "update-a-customer";
    }

    @GetMapping("/showFormForRegistration")
    public String addFunc(Model model){
        Customer c = new Customer();
        model.addAttribute("customer",c);
        return "register";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/customerApi/showFormForRegistration";
    }

    @GetMapping("")
    public String viewHome(){
        return "index";
    }

    @GetMapping("/adminPage")
    public String adminHome(){
        return "redirect:/movieApi/movies";
    }

    @GetMapping("/errorPage")
    public String error(){
        return "errorPage";
    }



}
