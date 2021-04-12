package com.emotion.aggregator.models;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmotionSet {
    private EmotionServiceType emotionServiceType;
    private Emotion emotion;
    private String mainEmotion;
    private Timestamp timestamp;
}
