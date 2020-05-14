package gg.bayes.challenge.service;

import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;

import java.util.List;

public interface MatchService {
    Long ingestMatch(String payload);
    List<HeroKills> getMatch(Long matchId);
    List<HeroItems> getItems(Long matchId, String heroName);
    List<HeroSpells> getSpells(Long matchId, String heroName);
    List<HeroDamage> getDamage(Long matchId, String heroName);
}
