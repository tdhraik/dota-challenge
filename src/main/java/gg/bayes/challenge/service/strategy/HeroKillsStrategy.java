package gg.bayes.challenge.service.strategy;

import gg.bayes.challenge.domain.entity.HeroKills;
import gg.bayes.challenge.domain.repository.HeroKillsRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class HeroKillsStrategy implements EventStrategy {

    private final Pattern HERO_KILLS_REGIX =
            Pattern.compile("\\[(?<timestamp>.*)] npc_dota_hero_(?<heroKilled>.*) is killed by npc_dota_hero_(?<hero>.*)");

    private final HeroKillsRepository repository;

    public HeroKillsStrategy(HeroKillsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String eventLine, Long matchId) {
        HeroKills heroKills = new HeroKills();
        Matcher matcher = HERO_KILLS_REGIX.matcher(eventLine);
        if(matcher.matches()) {
            heroKills.setMatchId(matchId);
            heroKills.setHero(matcher.group("hero"));
            heroKills.setHeroKilled(matcher.group("heroKilled"));
            repository.save(heroKills);
        }
    }
}
