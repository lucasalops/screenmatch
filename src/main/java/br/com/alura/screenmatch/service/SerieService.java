package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.EpisodeDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {
	@Autowired
	private SerieRepository serieRepository;
	
	public List<SerieDTO> getAll() {
		return listToDTO(serieRepository.findAll());
	}
	
	private List<SerieDTO> listToDTO(List<Serie> series) {
		return series.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	private SerieDTO toDTO(Serie series) {
		return new SerieDTO(series.getId(), series.getTitulo(), series.getTotalTemporadas(), series.getAvaliacao(), series.getGenero(), series.getAtores(), series.getPoster(), series.getSinopse());
	}
	
	public List<SerieDTO> getTop5() {
		return listToDTO(serieRepository.findTop5ByOrderByAvaliacaoDesc());
	}
	
	public SerieDTO getById(Long id) {
		Optional<Serie> s = serieRepository.findById(id);
		return s.map(this::toDTO).orElse(null);
	}
	
	public List<SerieDTO> getReleases() {
		return listToDTO(serieRepository.lancamentosRecentes());
	}
	
	public List<EpisodeDTO> getAllSeasons(Long id) {
		Optional<Serie> serie = serieRepository.findById(id);
		
		if (serie.isPresent()) {
			Serie s = serie.get();
			s.getEpisodios().stream().map(e -> new EpisodeDTO(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo())).collect(Collectors.toList());
		}
		return null;
	}
	
	public List<EpisodeDTO> getSeasonByNumber(Long id, Long number) {
		
		return serieRepository.getSeasonByNumber(id, number).stream().map(e -> new EpisodeDTO(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo())).collect(Collectors.toList());
	}
	
	public List<SerieDTO> getByCategory(String genero) {
		Categoria categoria = Categoria.fromPortugues(genero);
		return listToDTO(serieRepository.getSerieByCategory(categoria));
	}
}
