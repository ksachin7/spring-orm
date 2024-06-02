package com.example.bank.account.controller;

import com.example.bank.account.model.Account;
import com.example.bank.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public String getAllAccounts(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Account> accountPage = accountService.findAll(pageable);
        model.addAttribute("accountPage", accountPage);
        return "accounts";
    }

    @GetMapping("/new")
    public String showNewAccountForm(Model model) {
        model.addAttribute("account", new Account());
        return "account_form";
    }

    @PostMapping
    public String saveAccount(@Valid @ModelAttribute("account") Account account, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "account_form";
        }
        accountService.save(account);
        return "redirect:/accounts";
    }

    @GetMapping("/edit/{id}")
    public String showEditAccountForm(@PathVariable Long id, Model model) {
        Account account = accountService.findById(id);
        model.addAttribute("account", account);
        return "account_form";
    }

    @PostMapping("/{id}")
    public String updateAccount(@PathVariable Long id, @Valid @ModelAttribute("account") Account account, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "account_form";
        }
        account.setId(id);
        accountService.save(account);
        return "redirect:/accounts";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteById(id);
        return "redirect:/accounts";
    }
}
