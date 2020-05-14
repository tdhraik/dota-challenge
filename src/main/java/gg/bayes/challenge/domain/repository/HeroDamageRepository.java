package gg.bayes.challenge.domain.repository;

import gg.bayes.challenge.domain.dto.HeroDamageDTO;
import gg.bayes.challenge.domain.entity.HeroDamage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroDamageRepository extends CrudRepository<HeroDamage, Long> {

    @Query("SELECT new gg.bayes.challenge.domain.dto.HeroDamageDTO(hd.heroDamaged, COUNT(hd), sum(hd.damageCount)) " +
            "FROM HeroDamage hd GROUP BY hd.matchId, hd.hero, hd.heroDamaged HAVING hd.hero = :heroName AND hd.matchId = :matchId")
    List<HeroDamageDTO> getDamage(@Param("matchId") Long matchId, @Param("heroName") String heroName);
}
