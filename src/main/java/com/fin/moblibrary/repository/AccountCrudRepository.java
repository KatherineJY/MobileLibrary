package com.fin.moblibrary.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fin.moblibrary.domain.Account;

public interface AccountCrudRepository extends CrudRepository<Account, Integer>{
	
	public Account findByPhone(String phone);
	
	@Modifying
	@Query("update Account set password = :password where id =:id")
	void updatePwd(@Param("password")String password,@Param("id")Integer id);
	
	@Modifying
	@Query("update Account set phone = :newphone where id =:id")
	void updatePhone(@Param("newphone")String newphone,@Param("id")Integer id);
	
	@Modifying
	@Query("update Account set deposit = :deposit where id =:id")
	void updateDeposit(@Param("deposit")boolean deposit,@Param("id")Integer id);
	 
	@Modifying
	@Query("update Account set balance = :balance where id =:id")
	void updateBalance(@Param("balance")double balance,@Param("id")Integer id);
}
