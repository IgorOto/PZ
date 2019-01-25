package com.igi.kinoteka_api.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.igi.kinoteka_api.model.dto.FreshShowing;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FreshShowingDeserializer extends JsonDeserializer<FreshShowing> {

    @Override
    public FreshShowing deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);

        final Long movieId = node.get("movieId").asLong();
        final Long roomId = node.get("roomId").asLong();
        final String startText = node.get("start").asText();
        final String endText = node.get("end").asText();
        final int price = node.get("ticketPrice").asInt();

        return FreshShowing.builder()
                .movieId(movieId)
                .roomId(roomId)
                .start(convert(startText))
                .end(convert(endText))
                .ticketPrice(price)
                .build();
    }

    private LocalDateTime convert(String source) {
        return LocalDateTime.parse(
                source, DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm"));
    }
}
