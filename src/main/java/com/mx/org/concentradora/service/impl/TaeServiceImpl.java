package com.mx.org.concentradora.service.impl;

import java.net.InetAddress;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mx.org.concentradora.client.GeneradorPeticionesMock;
import com.mx.org.concentradora.client.MockSolicitudSaldoClient;
import com.mx.org.concentradora.convert.Converter;
import com.mx.org.concentradora.entity.Request;
import com.mx.org.concentradora.entity.Response;
import com.mx.org.concentradora.model.RequestModel;
import com.mx.org.concentradora.repository.TaeRepository;
import com.mx.org.concentradora.service.TaeService;

@Service("taeService")
public class TaeServiceImpl implements TaeService {

	@Autowired
	@Qualifier("converter")
	private Converter converter;

	@Autowired
	private TaeRepository repoTae;

	@Autowired
	GeneradorPeticionesMock mock;

	@Autowired
	MockSolicitudSaldoClient saldo;

	@Override
	public Response taeTelcel(RequestModel model) {
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {

		}
		Request entity = converter.modelToEntity(model);
		saldo.startConnection(ip, 9898);
		System.out.println(saldo.sendMessage(mock.generarPeticionSolicitudSaldo(model.getTclave(), model.getCaja(),
				model.getReferencia(), model.getMonto().toString())));
		saldo.stopConnection();
		return new Response(repoTae.save(entity).getId().intValue(), "Se realiza la peticion");
	}

	@Override
	public Optional<RequestModel> consultaTae(Long idtrx) {
		Optional<Request> entity = repoTae.findById(idtrx);
		if (entity.isPresent()) {
			Request rq = entity.get();
			Optional<RequestModel> out = Optional.of(converter.EntityToModel(rq));
			return out;
		}
		Optional<RequestModel> sal = Optional.empty();
		return sal;
	}

}
