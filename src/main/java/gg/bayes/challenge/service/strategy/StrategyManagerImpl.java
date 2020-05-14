package gg.bayes.challenge.service.strategy;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StrategyManagerImpl implements StrategyManager {

    private final HeroDamageStrategy heroDamageStrategy;
    private final HeroKillsStrategy heroKillsStrategy;
    private final HeroItemsPurchasedStrategy heroItemsPurchasedStrategy;
    private final HeroItemsUsedStrategy heroItemsUsedStrategy;
    private final HeroSpellsStrategy heroSpellsStrategy;

    private List<EventStrategy> strategies = new ArrayList<>();

    public StrategyManagerImpl(HeroDamageStrategy heroDamageStrategy, HeroKillsStrategy heroKillsStrategy,
                               HeroItemsPurchasedStrategy heroItemsPurchasedStrategy, HeroItemsUsedStrategy heroItemsUsedStrategy,
                               HeroSpellsStrategy heroSpellsStrategy) {
        this.heroDamageStrategy = heroDamageStrategy;
        this.heroKillsStrategy = heroKillsStrategy;
        this.heroItemsPurchasedStrategy = heroItemsPurchasedStrategy;
        this.heroItemsUsedStrategy = heroItemsUsedStrategy;
        this.heroSpellsStrategy = heroSpellsStrategy;
        strategies.addAll(Arrays.asList(this.heroDamageStrategy,
                                this.heroKillsStrategy,
                                this.heroItemsPurchasedStrategy,
                                this.heroItemsUsedStrategy,
                                this.heroSpellsStrategy));
    }

    @Override
    public void apply(String line, Long matchId) {
        for(EventStrategy eventStrategy : strategies) {
            eventStrategy.execute(line, matchId);
        }
    }
}
