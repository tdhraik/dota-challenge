package gg.bayes.challenge.domain.dto;

import gg.bayes.challenge.rest.model.HeroItems;
import lombok.Data;

@Data
public class HeroItemsPurchasedDTO {
    private String item;
    private Long timestamp;

    public HeroItemsPurchasedDTO(String item, Long timestamp) {
        this.item = item;
        this.timestamp = timestamp;
    }

    public static HeroItems mapToHeroItemsView(HeroItemsPurchasedDTO heroItemsPurchasedDTO) {
        HeroItems heroItems = new HeroItems();
        heroItems.setItem(heroItemsPurchasedDTO.getItem());
        heroItems.setTimestamp(heroItemsPurchasedDTO.getTimestamp());
        return heroItems;
    }
}
