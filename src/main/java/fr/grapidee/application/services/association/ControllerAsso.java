package fr.grapidee.application.services.association;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.grapidee.application.services.commun.CommunBodyDTO;
import fr.grapidee.application.services.commun.TypeChargement;
import fr.grapidee.application.services.commun.dto.CommunDTO;
import fr.grapidee.application.services.entite.grappe.GrappeDTO;
import fr.grapidee.application.services.entite.grappe.GrappeService;
import fr.grapidee.application.services.entite.idee.IdeeDTO;
import fr.grapidee.application.services.entite.idee.IdeeService;
import fr.grapidee.application.utilitaire.ConstanteIdee;

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
		return ideeService.findOne(dto.getIdMaitre());
	}

	@RequestMapping(value = "/desassocier/idee", method = RequestMethod.POST)
	@Transactional
	public IdeeDTO desassocierIdee(
			@RequestBody AssoIdeeIdeeBodyDto dto) throws Exception {
		this.service.desassocierIdee(dto.getIdMaitre(), dto.getIdEsclave());
		return ideeService.findOne(dto.getIdMaitre());
	}

	@RequestMapping(value = "/associer/grappe", method = RequestMethod.POST)
	@Transactional
	public GrappeDTO associerGrappe(@RequestBody AssoGrappeGrappeBodyDto dto) throws Exception {
		this.service.associerGrappe(dto.getIdMaitre(), dto.getIdEsclave());
		return grappeService.findOne(dto.getIdMaitre());
	}

	@RequestMapping(value = "/desassocier/grappe", method = RequestMethod.POST)
	@Transactional
	public GrappeDTO desassocierGrappe(@RequestBody AssoGrappeGrappeBodyDto dto) throws Exception {
		this.service.desassocierGrappe(dto.getIdMaitre(), dto.getIdEsclave());
		return grappeService.findOne(dto.getIdMaitre());
	}
}
