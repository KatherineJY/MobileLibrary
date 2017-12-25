package com.fin.moblibrary.repository;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fin.moblibrary.domain.PayRecord;
import com.fin.moblibrary.model.PayRecordMultiKeysClass;

public interface PayRecordCrudRepository extends PagingAndSortingRepository<PayRecord, PayRecordMultiKeysClass>{

	public PayRecord[] findAllByAccountId(Integer accountId,Order order);
}
