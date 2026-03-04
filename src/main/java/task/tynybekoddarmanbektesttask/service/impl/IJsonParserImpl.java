package task.tynybekoddarmanbektesttask.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import task.tynybekoddarmanbektesttask.dto.RequestAd;
import task.tynybekoddarmanbektesttask.service.IJsonParser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class IJsonParserImpl implements IJsonParser {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public List<RequestAd> parse(String json, int limit) throws JsonProcessingException {
        List<RequestAd> products = new ArrayList<>();
        JsonNode array = mapper.readTree(json);

        if (!array.isArray()) return products;

        for (int i = 0; i < Math.min(limit, array.size()); i++) {
            JsonNode node = array.get(i);
            products.add(RequestAd.builder()
                    .title(node.path("title").asText())
                    .image(node.path("image").asText())
                    .category(node.path("category").asText())
                    .price(node.path("price").asDouble())
                    .date(LocalDate.now().toString())
                    .build());
        }

        return products;
    }
}
