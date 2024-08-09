package alura.screenmatchfrases.controller;

import alura.screenmatchfrases.dto.PhraseDTO;
import alura.screenmatchfrases.service.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhraseController {
	@Autowired
	private PhraseService phraseService;
	
	@GetMapping("/all")
	public List<PhraseDTO> getAll() {
		return phraseService.getAll();
	}
	
	@GetMapping("/series/phrases")
	public PhraseDTO getRandom() {
		return phraseService.getRandom();
	}
}
