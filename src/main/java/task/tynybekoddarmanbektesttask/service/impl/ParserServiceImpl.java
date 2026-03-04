package task.tynybekoddarmanbektesttask.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import task.tynybekoddarmanbektesttask.dto.RequestAd;
import task.tynybekoddarmanbektesttask.service.HttpFetcher;
import task.tynybekoddarmanbektesttask.service.IJsonParser;
import task.tynybekoddarmanbektesttask.service.ParserService;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ParserServiceImpl implements ParserService {
    private final HttpFetcher fetcher;
    private final IJsonParser parser;

    @Value("${fakestore.url}")
    private String url;
    @Override
    public List<RequestAd> parseAds(int limit) {

        List<RequestAd> products = new ArrayList<>();
        try {
            String json = fetcher.fetch(url);
            return parser.parse(json, limit);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // пустой список при ошибке
        }
    }
}
