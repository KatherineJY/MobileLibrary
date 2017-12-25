package com.fin.moblibrary.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fin.moblibrary.domain.Account;

public interface AccountCrudRepository extends CrudRepository<Account, Integer>{
	
	public Account findByPhone(String phone);
	
	@Modifying
	@Query("update Account set password = :password where accoutId =:accoutId")
	void updatePwd(@Param("password")String password,@Param("accoutId")Integer accoutId);
	
	@Modifying
	@Query("update Account set phone = :newphone where accoutId =:accoutId")
	void updatePhone(@Param("newphone")String newphone,@Param("accoutId")Integer accoutId);
	
	@Modifying
	@Query("update Account set deposit = :deposit where accountId =:accoutId")
	void updateDeposit(@Param("deposit")boolean deposit,@Param("accoutId")Integer accoutId);
	
	@Modifying
	@Query("update Account set balance = :balance where accoutId =:accoutId")
	void updateBalance(@Param("balance")double balance,@Param("accoutId")Integer accoutId);
}
