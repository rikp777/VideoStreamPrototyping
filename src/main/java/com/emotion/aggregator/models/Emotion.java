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
public class Emotion {
    private BigDecimal happiness;
    private BigDecimal anger;
    private BigDecimal neutrality;
    private BigDecimal fear;
    private BigDecimal sadness;
}
