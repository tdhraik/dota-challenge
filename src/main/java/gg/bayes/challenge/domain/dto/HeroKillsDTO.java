package gg.bayes.challenge.domain.dto;

import gg.bayes.challenge.rest.model.HeroKills;
import lombok.Data;

@Data
public class HeroKillsDTO {
    private String hero;
    private Long kills;

    public HeroKillsDTO(String hero, Long kills) {
        this.hero = hero;
        this.kills = kills;
    }

    public static HeroKills mapToHeroKillsView(HeroKillsDTO heroKillsDTO) {
        HeroKills heroKills = new HeroKills();
        heroKills.setHero(heroKillsDTO.getHero());
        heroKills.setKills(heroKillsDTO.getKills());
        return heroKills;
    }
}
