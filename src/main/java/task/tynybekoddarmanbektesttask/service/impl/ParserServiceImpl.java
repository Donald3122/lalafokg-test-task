package task.tynybekoddarmanbektesttask.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import task.tynybekoddarmanbektesttask.dto.RequestAd;
import task.tynybekoddarmanbektesttask.service.HttpFetcher;
import task.tynybekoddarmanbektesttask.service.IJsonParser;
import task.tynybekoddarmanbektesttask.service.ParserService;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class ParserServiceImpl implements ParserService {
    private final HttpFetcher fetcher;
    private final IJsonParser parser;

    @Value("${fakestore.url}")
    private String url;
    @Override
    public List<RequestAd> parseAds(int limit) {
        try {
            String json = fetcher.fetch(url);
            List<RequestAd> product = parser.parse(json, limit);
            log.info("Успешно получено {} обьявлений", product.size());
            return product;
        } catch (IOException e) {
            log.error("Ошибка при получении данных с API: {}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("Неожиданная ошибка при парсинге данных: {}", e.getMessage(), e);
        }
        // Возвращаем пустой список в случае ошибки, чтобы контроллер не упал
        return Collections.emptyList();
    }
}
