package com.example.demo.mapper;

import com.example.demo.dto.AccountDto;
import com.example.demo.model.Account;

public class AccountMapper {

	public static Account mapToAccount(AccountDto accountDto) {
		Account account = new Account(
				accountDto.getId(),
				accountDto.getAccountHolderName(),
				accountDto.getBalance()
				);
				
				return account;
	}
	
	public static AccountDto mapToAccountDto(Account account) {
		AccountDto acccoutDto=new AccountDto(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
				);
		return acccoutDto;
	}
}
