package com.emotion.aggregator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceivedEmotionVoice {

    private String type;
    private String timeStamp;
    private double neutrality;
    private double happiness;
    private double sadness;
    private double anger;
    private double fear;
    private int emotion;
}
