package gg.bayes.challenge.service.strategy;

import gg.bayes.challenge.domain.entity.HeroItemsPurchased;
import gg.bayes.challenge.domain.repository.HeroItemsPurchasedRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class HeroItemsPurchasedStrategy implements EventStrategy {

    private static final Pattern HERO_ITEM_PURCHASED_REGIX =
            Pattern.compile("\\[(?<timestamp>.*)] npc_dota_hero_(?<hero>.*) buys item item_(?<item>.*)");

    private final HeroItemsPurchasedRepository repository;

    public HeroItemsPurchasedStrategy(HeroItemsPurchasedRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String eventLine, Long matchId) {
        HeroItemsPurchased heroItemsPurchased = new HeroItemsPurchased();
        Matcher matcher = HERO_ITEM_PURCHASED_REGIX.matcher(eventLine);
        if(matcher.matches()) {
            heroItemsPurchased.setMatchId(matchId);
            heroItemsPurchased.setHero(matcher.group("hero"));
            heroItemsPurchased.setItem(matcher.group("item"));
            heroItemsPurchased.setTimestamp(getTimestampInMillis(matcher.group("timestamp")));
            repository.save(heroItemsPurchased);
        }
    }
}
