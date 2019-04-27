package fr.grapidee.application.services.entite.grappe;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.grapidee.application.services.commun.CommunBodyDTO;
import fr.grapidee.application.services.commun.Options;
import fr.grapidee.application.services.commun.TypeChargement;
import fr.grapidee.application.services.commun.dto.CommunDTO;
import fr.grapidee.application.services.entite.AccueilGrappeDto;
import fr.grapidee.application.services.entite.idee.IdeeDTO;

@RestController
@RequestMapping(value = "/grapidee/grappe")
public class ControllerGrappe {

	@Autowired
	private GrappeService grappeService;

	@RequestMapping(method = RequestMethod.GET)
	@Transactional
	public AccueilGrappeDto recupererToutesGrappes() {
		return this.grappeService.findAll();
	}

	@RequestMapping(value = "/options", method = RequestMethod.GET)
	@Transactional
	public List<Options> findAllOptions() {
		return this.grappeService.findAllOptions();
	}
	
	@RequestMapping(value = "/maitres", method = RequestMethod.GET)
	public List<GrappeDTO> recupererIdeesMaitres() {
		return this.grappeService.recupererGrappesMaitres();
	}

	
	@RequestMapping(value = "/orphelines", method = RequestMethod.GET)
	public List<GrappeDTO> recupererIdeesOrphelines() {
		return this.grappeService.recupererGrappesOrphelines();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public GrappeDTO recupererGrappeWithIdee(
			@PathVariable(value = "id") Long id) {
		return this.grappeService.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@Transactional
	public GrappeDTO creerGrappe(@RequestBody GrappeBodyDTO grappe)
			throws Exception {
		return this.grappeService.postOne(grappe);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@Transactional
	public GrappeDTO majGrappe(@RequestBody GrappeBodyDTO grappe)
			throws Exception {
		return this.grappeService.putOne(grappe);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	@Transactional
	public GrappeDTO deleteGrappe(@RequestBody GrappeBodyDTO grappe)
			throws Exception {
		return this.grappeService.deleteOne(grappe);
	}

}
