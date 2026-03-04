package task.tynybekoddarmanbektesttask.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import task.tynybekoddarmanbektesttask.dto.RequestAd;
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
import java.util.List;
@Service
public class ParserServiceImpl implements ParserService {

    @Value("${fakestore.url}")
    private String url;
    @Override
    public List<RequestAd> parseAds(int limit) {

        List<RequestAd> products = new ArrayList<>();
        try {
            // SSLContext, доверяем всем сертификатам для теста
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{ new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
            }}, new SecureRandom());

            HttpClient client = HttpClient.newBuilder()
                    .sslContext(sslContext)
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode array = mapper.readTree(response.body());

            if (!array.isArray()) return products;

            for (int i = 0; i < Math.min(limit, array.size()); i++) {
                JsonNode node = array.get(i);
                String title = node.get("title").asText();
                double price = node.get("price").asDouble();
                String image = node.get("image").asText();
                String category = node.get("category").asText();
                String date = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().toString();

                products.add(new RequestAd(image, title, price, category, date));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
