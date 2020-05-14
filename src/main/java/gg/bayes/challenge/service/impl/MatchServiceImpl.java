package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.domain.dto.HeroDamageDTO;
import gg.bayes.challenge.domain.dto.HeroItemsPurchasedDTO;
import gg.bayes.challenge.domain.dto.HeroKillsDTO;
import gg.bayes.challenge.domain.dto.HeroSpellsDTO;
import gg.bayes.challenge.domain.repository.HeroDamageRepository;
import gg.bayes.challenge.domain.repository.HeroItemsPurchasedRepository;
import gg.bayes.challenge.domain.repository.HeroKillsRepository;
import gg.bayes.challenge.domain.repository.HeroSpellsRepository;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.service.MatchService;
import gg.bayes.challenge.service.strategy.StrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private static Long MATCH_ID = 0L;

    private final StrategyManager strategyManager;
    private final HeroKillsRepository killsRepository;
    private final HeroItemsPurchasedRepository itemsPurchasedRepository;
    private final HeroSpellsRepository spellsRepository;
    private final HeroDamageRepository damageRepository;

    @Override
    public Long ingestMatch(String payload) {
        MATCH_ID++;
        try (BufferedReader reader = new BufferedReader(new StringReader(payload))) {
            String eventLine = reader.readLine();
            while (null != eventLine) {
                strategyManager.apply(eventLine, MATCH_ID);
                eventLine = reader.readLine();
            }
        } catch(IOException e) {
            log.error("Error reading log file");
            throw new IllegalStateException();
        }
        return MATCH_ID;
    }

    @Override
    public List<HeroKills> getMatch(Long matchId) {
        return killsRepository.getKills(matchId).stream()
                .map(HeroKillsDTO::mapToHeroKillsView)
                .collect(Collectors.toList());
    }

    @Override
    public List<HeroItems> getItems(Long matchId, String heroName) {
        return itemsPurchasedRepository.getItems(matchId, heroName).stream()
                .map(HeroItemsPurchasedDTO::mapToHeroItemsView)
                .collect(Collectors.toList());
    }

    @Override
    public List<HeroSpells> getSpells(Long matchId, String heroName) {
        return spellsRepository.getSpells(matchId, heroName).stream()
                .map(HeroSpellsDTO::mapToHeroSpellsView)
                .collect(Collectors.toList());
    }

    @Override
    public List<HeroDamage> getDamage(Long matchId, String heroName) {
        return damageRepository.getDamage(matchId, heroName).stream()
                .map(HeroDamageDTO::mapToHeroDamageView)
                .collect(Collectors.toList());
    }
}
