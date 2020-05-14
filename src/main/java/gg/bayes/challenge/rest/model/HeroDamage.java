package gg.bayes.challenge.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HeroDamage {
    private String target;
    @JsonProperty("damage_instances")
    private Long damageInstances;
    @JsonProperty("total_damage")
    private Long totalDamage;
}
