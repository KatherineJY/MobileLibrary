package com.fin.moblibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.moblibrary.domain.PayRecord;
import com.fin.moblibrary.model.ResponseWrapper;
import com.fin.moblibrary.repository.AccountCrudRepository;
import com.fin.moblibrary.repository.BookRecordCrudRepository;
import com.fin.moblibrary.repository.PayRecordCrudRepository;
import com.fin.moblibrary.view.ViewBookRecord;
import com.fin.moblibrary.view.ViewHotBook;

/**
 * @author KatherineJY
 * 处理关于流水记录、借还书记录的查询操作
 */

@Service
public class RecordService {
	
	@Autowired
	private AccountCrudRepository accountCrudRepository;
	@Autowired
	private PayRecordCrudRepository payRecordCrudRepository;
	@Autowired
	private BookRecordCrudRepository bookRecordCrudRepository;
	
	private Order orderByDateDesc = new Order(Sort.Direction.DESC,"date");
	
	/**
	 * 查看流水记录
	 * @param accountId
	 * */
	@Transactional
	public ResponseWrapper showPayRecord(Integer accountId) {
		if( accountCrudRepository.findOne(accountId) == null )
			return new ResponseWrapper(false,"account isn't exist",null);
		PayRecord[] payRecords = payRecordCrudRepository.findAllByAccountId(accountId,orderByDateDesc);
		return new ResponseWrapper(true,"",payRecords);
	}
	
	/**
	 * 查看借还书记录
	 * @param accountId
	 * */
	@Transactional
	public ResponseWrapper showBookRecord(Integer accountId) {
		if( accountCrudRepository.findOne(accountId) == null )
			return new ResponseWrapper(false,"account isn't exist",null);
		//ViewBookRecord[] bookRecords = bookRecordCrudRepository.findViewBookRecordByAccountId(accountId);
		//return new ResponseWrapper(true,"",bookRecords);
		return null;
	}
	
	/**
	 * 查看未还图书
	 * @param accountId
	 * */
	@Transactional
	public ResponseWrapper showBookBorrow(Integer accountId) {
		if( accountCrudRepository.findOne(accountId) == null )
			return new ResponseWrapper(false, "account isn't exist", null);
		//return new ResponseWrapper(true,"",bookRecordCrudRepository.findViewBookRecordByAccountIdAndBorrow(accountId, true));
		return null;
	}

	/**
	 * 展示热门图书
	 * */
	public ResponseWrapper showHotBook() {
		//ViewHotBook[] viewHotBooks = bookRecordCrudRepository.findViewHotBooks();
		//return new ResponseWrapper(true,"",viewHotBooks);
		return null;
	}
	

	
}
