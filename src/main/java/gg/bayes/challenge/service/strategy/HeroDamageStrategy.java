package gg.bayes.challenge.service.strategy;

import gg.bayes.challenge.domain.entity.HeroDamage;
import gg.bayes.challenge.domain.repository.HeroDamageRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class HeroDamageStrategy implements EventStrategy {

    private final Pattern DAMAGE_DONE_REGIX =
            Pattern.compile("\\[(?<timestamp>.*)] npc_dota_hero_(?<hero>.*) hits npc_dota_hero_(?<heroDamaged>.*) with (?<ignored1>.*) for (?<damageCount>\\d+) damage (?<ignored2>.*)");

    private final HeroDamageRepository repository;

    public HeroDamageStrategy(HeroDamageRepository heroDamageRepository) {
        this.repository = heroDamageRepository;
    }

    @Override
    public void execute(String eventLine, Long matchId) {
        HeroDamage heroDamage = new HeroDamage();
        Matcher matcher = DAMAGE_DONE_REGIX.matcher(eventLine);
        if(matcher.matches()) {
            heroDamage.setMatchId(matchId);
            heroDamage.setHero(matcher.group("hero"));
            heroDamage.setHeroDamaged(matcher.group("heroDamaged"));
            heroDamage.setDamageCount(Integer.valueOf(matcher.group("damageCount")));
            repository.save(heroDamage);
        }
    }
}
