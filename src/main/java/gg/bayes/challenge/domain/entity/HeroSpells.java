package gg.bayes.challenge.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HeroSpells {

    @Id
    @GeneratedValue
    Long id;

    Long matchId;

    String hero;

    String spell;

    Integer level;

    String targetHero;
}
