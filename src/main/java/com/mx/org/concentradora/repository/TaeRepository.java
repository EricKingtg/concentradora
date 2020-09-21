package com.mx.org.concentradora.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mx.org.concentradora.entity.Request;

public interface TaeRepository extends PagingAndSortingRepository<Request, Long> {

	public Optional<Request> findById(Long folio);
	
}
