package fr.grapidee.application.services.association;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.grapidee.application.services.entite.grappe.GrappeDTO;
import fr.grapidee.application.services.entite.grappe.GrappeService;
import fr.grapidee.application.services.entite.idee.IdeeDTO;
import fr.grapidee.application.services.entite.idee.IdeeService;

@RestController
@RequestMapping(value = "/grapidee")
public class ControllerAsso {

	@Autowired
	private AssoService service;

	@Autowired
	private IdeeService ideeService;
	
	@Autowired
	private GrappeService grappeService;


	@RequestMapping(value = "/associer/idee", method = RequestMethod.POST)
	@Transactional
	public IdeeDTO associerIdees(
		@RequestBody AssoIdeeIdeeBodyDto dto) throws Exception {
	
		this.service.associerIdee(dto.getIdMaitre(), dto.getIdEsclave(), dto.getIdGrappe());
		return ideeService.findById(dto.getIdMaitre());
	}

	@RequestMapping(value = "/desassocier/idee", method = RequestMethod.POST)
	@Transactional
	public IdeeDTO desassocierIdee(
			@RequestBody AssoIdeeIdeeBodyDto dto) throws Exception {
		this.service.desassocierIdee(dto.getIdMaitre(), dto.getIdEsclave());
		return ideeService.findById(dto.getIdMaitre());
	}

	@RequestMapping(value = "/associer/grappe", method = RequestMethod.POST)
	@Transactional
	public GrappeDTO associerGrappe(@RequestBody AssoGrappeGrappeBodyDto dto) throws Exception {
		this.service.associerGrappe(dto.getIdMaitre(), dto.getIdEsclave());
		return grappeService.findById(dto.getIdMaitre());
	}

	@RequestMapping(value = "/desassocier/grappe", method = RequestMethod.POST)
	@Transactional
	public GrappeDTO desassocierGrappe(@RequestBody AssoGrappeGrappeBodyDto dto) throws Exception {
		this.service.desassocierGrappe(dto.getIdMaitre(), dto.getIdEsclave());
		return grappeService.findById(dto.getIdMaitre());
	}
}
