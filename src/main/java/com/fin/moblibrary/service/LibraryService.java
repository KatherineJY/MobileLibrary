package com.fin.moblibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.moblibrary.domain.Library;
import com.fin.moblibrary.model.ResponseWrapper;
import com.fin.moblibrary.repository.LibrarayCrudRepository;

@Service
public class LibraryService {
	
	@Autowired
	private LibrarayCrudRepository librarayCrudRepository;

	public ResponseWrapper showAllLibraries() {
		List<Library> libraries =  librarayCrudRepository.findAllLibrary();
		return new ResponseWrapper(true,"",libraries);
	}
	
	
}
