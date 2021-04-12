package com.emotion.aggregator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = ItemDeserializer.class)
public class Emotion {
    private double happiness;
    private double anger;
    private double neutrality;
    private double fear;
    private double sadness;
}
