package alura.screenmatchfrases.repository;

import alura.screenmatchfrases.models.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhraseRepository extends JpaRepository<Phrase, Long>{
	
	@Query("SELECT p FROM Phrase p WHERE p.character = :character")
	List<Phrase> findByCharacter(String character);
	
	@Query("SELECT p FROM Phrase p ORDER BY function('RANDOM') LIMIT 1")
	Phrase getRandomPhrase();
}
