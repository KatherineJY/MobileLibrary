package com.fin.moblibrary.repository;

import java.util.Date;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.fin.moblibrary.domain.Reserve;
import com.fin.moblibrary.model.ReserveMultiKeysClass;
import com.fin.moblibrary.view.ViewReserveBook;

public interface ReserveCrudRepository extends CrudRepository<Reserve, ReserveMultiKeysClass>,PagingAndSortingRepository<Reserve, ReserveMultiKeysClass>{

	@Modifying
	@Query("update Reserve set reserveDate = :reserveDate, expire =:expire and where accountId = :accountId and bookCategoryId = :bookCategoryId")
	public void updateReserveDateAndExpire(@Param("reserveDate")Date reserveDate,@Param("expire")Date expire,@Param("accountId")Integer accountId,@Param("bookCategoryId")Integer bookCategoryId);

	@Query("SELECT ViewReserveRecord(reserveBook,bookCategory))FROM "
	        + "com.fin.moblibrary.domain.BookCategory bookCategory, ReserveBook reserveBook "
	        + "WHERE reserveBook.accountId = :accountId and bookCategory.bookCategoryId = reserveBook.bookCategoryId ")
	ViewReserveBook[] findViewReserveBookByAccountId(Integer accountId);

	public Reserve findByAccountIdAndBookCategoryId(Integer accountId, Integer bookCategoryId);

	public Reserve findByAccountIdAndBookCategoryIdAndLibraryId(Integer accountId, Integer bookCategoryId,
			Integer libraryId);

	public Reserve[] findByExpire();

	public Reserve findByBookCategoryIdAndLibraryIdAndExpire(Integer bookCategoryId, Integer newLibraryId,
			Date expire,Order order);

}
