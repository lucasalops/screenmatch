package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dto.EpisodeDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {
	
	@Autowired
	private SerieService serieService;
	
	@GetMapping
	public List<SerieDTO> getAll() {
		return serieService.getAll();
	}
	
	@GetMapping("/top5")
	public List<SerieDTO> getTop5() {
		return serieService.getTop5();
	}
	
	@GetMapping("/{id}")
	public SerieDTO get(@PathVariable Long id) {
		return serieService.getById(id);
	}
	
	@GetMapping("/lancamentos")
	public List<SerieDTO> getReleases() {
		return serieService.getReleases();
	}
	
	@GetMapping("/{id}/temporadas/todas")
	public List<EpisodeDTO> getAllSeasons(@PathVariable Long id) {
		return serieService.getAllSeasons(id);
	}
	
	@GetMapping("/{id}/temporadas/{number}")
	public List<EpisodeDTO> getSeasonByNumber(@PathVariable Long number, @PathVariable Long id) {
		return serieService.getSeasonByNumber(id, number);
	}
	
	@GetMapping("/categoria/{genero}")
	public List<SerieDTO> getByCategory(@PathVariable String genero) {
		return serieService.getByCategory(genero);
	}
}
