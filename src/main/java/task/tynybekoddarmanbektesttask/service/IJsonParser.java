package task.tynybekoddarmanbektesttask.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import task.tynybekoddarmanbektesttask.dto.RequestAd;

import java.util.List;

public interface IJsonParser {
    List<RequestAd> parse(String json, int limit) throws JsonProcessingException;
}
