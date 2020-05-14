package gg.bayes.challenge.domain.repository;

import gg.bayes.challenge.domain.dto.HeroItemsPurchasedDTO;
import gg.bayes.challenge.domain.entity.HeroItemsPurchased;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroItemsPurchasedRepository extends CrudRepository<HeroItemsPurchased, Long> {

    @Query("SELECT new gg.bayes.challenge.domain.dto.HeroItemsPurchasedDTO(item, timestamp) " +
            "FROM HeroItemsPurchased hip WHERE hip.hero = :heroName AND hip.matchId = :matchId")
    List<HeroItemsPurchasedDTO> getItems(@Param("matchId") Long matchId, @Param("heroName") String heroName);
}
