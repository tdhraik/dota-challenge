package gg.bayes.challenge.domain.repository;

import gg.bayes.challenge.domain.entity.HeroItemsUsed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroItemsUsedRepository extends CrudRepository<HeroItemsUsed, Long> {
}
