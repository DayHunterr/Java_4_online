package ua.com.alevel.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.api.TransactionApiService;
import ua.com.alevel.model.TransactionModel;
import ua.com.alevel.model.TransactionPostModel;

import java.awt.*;


@Controller
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionControllerTl {

    private final TransactionApiService transactionApiService;

    @GetMapping("/new")
    public String createTransactionMenu(Model model){
        model.addAttribute("transaction", new TransactionPostModel());
        return "new/transaction_new";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute TransactionPostModel transaction){
        if(!transactionApiService.create(transaction)){
            return "errors/something_going_wrong";
        }
        return "redirect:/transactions";
    }

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("transactions", transactionApiService.findAll());
        return "profile/transactions";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){
        if(transactionApiService.findById(id).isPresent()){
            model.addAttribute("transaction",transactionApiService.findById(id).get());
            return "profile_details/transaction_details";
        }
        return "errors/something_going_wrong";
    }

   /* @GetMapping("/{id}")
    public String getDownload(@PathVariable Long id, Model model){
            model.addAttribute("download",transactionApiService.getCheck(id));
            return "profile_details/transaction_details";
    }*/
}
