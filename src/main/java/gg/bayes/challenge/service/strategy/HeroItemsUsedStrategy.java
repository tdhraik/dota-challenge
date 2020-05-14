package gg.bayes.challenge.service.strategy;

import gg.bayes.challenge.domain.entity.HeroItemsUsed;
import gg.bayes.challenge.domain.repository.HeroItemsUsedRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class HeroItemsUsedStrategy implements EventStrategy {

    private final Pattern HERO_ITEMS_USED_REGIX =
            Pattern.compile("\\[(?<timestamp>.*)] npc_dota_hero_(?<hero>.*) uses item_(?<item>.*)");

    private final HeroItemsUsedRepository repository;

    public HeroItemsUsedStrategy(HeroItemsUsedRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String eventLine, Long matchId) {
        HeroItemsUsed heroItemsUsed = new HeroItemsUsed();
        Matcher matcher = HERO_ITEMS_USED_REGIX.matcher(eventLine);
        if(matcher.matches()) {
            heroItemsUsed.setMatchId(matchId);
            heroItemsUsed.setHero(matcher.group("hero"));
            heroItemsUsed.setItem(matcher.group("item"));
            repository.save(heroItemsUsed);
        }
    }
}
