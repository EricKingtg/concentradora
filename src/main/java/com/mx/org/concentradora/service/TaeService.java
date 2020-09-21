package com.mx.org.concentradora.service;

import java.util.Optional;

import com.mx.org.concentradora.entity.Response;
import com.mx.org.concentradora.model.RequestModel;

public interface TaeService {

	public abstract Response taeTelcel(RequestModel in);
	public abstract Optional<RequestModel> consultaTae(Long idtrx);
	
}
