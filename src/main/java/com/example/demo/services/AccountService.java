package com.example.demo.services;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AccountDto;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepo;
	
	public ResponseEntity<AccountDto> createAccount(AccountDto accountDto){
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepo.save(account);
		return new ResponseEntity<AccountDto>(AccountMapper.mapToAccountDto(savedAccount),HttpStatus.CREATED);
	}
	
	public ResponseEntity<AccountDto> getAccount(Long id){
		Account account = accountRepo.findById(id).orElse(new Account());
		AccountDto accountDto = AccountMapper.mapToAccountDto(account);
		return new ResponseEntity<AccountDto>(accountDto,HttpStatus.OK);		
	}
	
	public ResponseEntity<List<AccountDto>> getAccounts(){
		List<Account> account = accountRepo.findAll();
		List<AccountDto> accountDto = new ArrayList<>();
		for(Account a:account) {
			 accountDto.add(AccountMapper.mapToAccountDto(a));
		}
		
		return new ResponseEntity<List<AccountDto>>(accountDto,HttpStatus.OK);		
	}
	
	public ResponseEntity<AccountDto> deposit(Long id,double amount){
		Account account = accountRepo.findById(id).orElse(new Account());
		double bal = account.getBalance()+amount;
		account.setBalance(bal);
		accountRepo.save(account);
		AccountDto accountDto = AccountMapper.mapToAccountDto(account);		
		return new ResponseEntity<AccountDto>(accountDto,HttpStatus.OK);		
	}
	
	public ResponseEntity<AccountDto> withdraw(Long id,double amount){
		Account account = accountRepo.findById(id).orElse(new Account());
		if(account.getBalance()<amount)
			  throw new RuntimeException("Insuffiunicent balance");		
		double bal = account.getBalance()-amount;
		account.setBalance(bal);
		accountRepo.save(account);
		AccountDto accountDto = AccountMapper.mapToAccountDto(account);		
		return new ResponseEntity<AccountDto>(accountDto,HttpStatus.OK);		
	}
	
	public ResponseEntity<String> deleteAccount(Long id){
		accountRepo.deleteById(id);
		return new ResponseEntity<String>("Deleted",HttpStatus.OK);
	}
	
}
