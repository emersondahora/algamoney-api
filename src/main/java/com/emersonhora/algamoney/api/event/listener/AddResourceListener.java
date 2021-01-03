package com.emersonhora.algamoney.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.emersonhora.algamoney.api.event.AddResourceEvent;

@Component
public class AddResourceListener implements ApplicationListener<AddResourceEvent> {

	@Override
	public void onApplicationEvent(AddResourceEvent event) {
		// TODO Auto-generated method stub]
		HttpServletResponse response = event.getResponse();
		Long id = event.getId();
		
		addHeaderLocation(response, id);
	}

	private void addHeaderLocation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}


}
