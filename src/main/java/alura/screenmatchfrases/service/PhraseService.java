package alura.screenmatchfrases.service;

import alura.screenmatchfrases.dto.PhraseDTO;
import alura.screenmatchfrases.models.Phrase;
import alura.screenmatchfrases.repository.PhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PhraseService {
	@Autowired
	private PhraseRepository phraseRepository;
	
	private Random random;
	
	public List<PhraseDTO> getAll() {
		return listToDTO(phraseRepository.findAll());
	}
	
	public PhraseDTO toDTO(Phrase phrase) {
		return new PhraseDTO(phrase.getTitle(), phrase.getPhrase(), phrase.getCharacter(), phrase.getPoster());
	}
	
	public List<PhraseDTO> listToDTO(List<Phrase> phrases) {
		return phrases.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	public List<PhraseDTO> getByCharacter(String character) {
		return listToDTO(phraseRepository.findByCharacter(character));
	}
	
	public PhraseDTO getRandom() {
		return toDTO(phraseRepository.getRandomPhrase());
	}
}
