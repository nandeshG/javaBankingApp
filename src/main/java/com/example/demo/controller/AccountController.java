package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AccountDto;
import com.example.demo.services.AccountService;

@RestController
@RequestMapping("account")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("")
	public ResponseEntity<List<AccountDto>> getAccounts(){
		return accountService.getAccounts();
	}
	
	@PostMapping("add")
	public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
		return accountService.createAccount(accountDto);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<AccountDto> getAccount(@PathVariable Long id){
		return accountService.getAccount(id);
	}
	
	@PutMapping("deposit/{id}")
	public ResponseEntity<AccountDto> deposite(@PathVariable Long id,@RequestBody Map<String, Double> request){
		Double amount = request.get("amount");
		return accountService.deposit(id, amount);
	}
	
	@PutMapping("withdraw/{id}")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String, Double> request){
		Double amount = request.get("amount");
		return accountService.withdraw(id, amount);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id){
		return accountService.deleteAccount(id);
	}

}
