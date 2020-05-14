package gg.bayes.challenge.service.strategy;

import gg.bayes.challenge.domain.entity.HeroSpells;
import gg.bayes.challenge.domain.repository.HeroSpellsRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class HeroSpellsStrategy implements EventStrategy {

    private final Pattern HERO_SPELLS_REGIX =
            Pattern.compile("\\[(.*)] npc_dota_hero_(?<hero>.*) casts ability (?<spell>.*) \\(lvl (?<level>.*)\\) on (?<targetHero>.*)");

    private final HeroSpellsRepository repository;

    public HeroSpellsStrategy(HeroSpellsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String eventLine, Long matchId) {
        HeroSpells heroSpells = new HeroSpells();
        Matcher matcher = HERO_SPELLS_REGIX.matcher(eventLine);
        if(matcher.matches()) {
            heroSpells.setMatchId(matchId);
            heroSpells.setHero(matcher.group("hero"));
            heroSpells.setSpell(matcher.group("spell"));
            heroSpells.setTargetHero(matcher.group("targetHero"));
            repository.save(heroSpells);
        }
    }
}
