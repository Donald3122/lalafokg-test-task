package task.tynybekoddarmanbektesttask.service;

import task.tynybekoddarmanbektesttask.dto.RequestAd;

import java.io.IOException;
import java.util.List;

public interface ParserService {
    /**
     * Интерфейс парсера объявлений.
     * Собирает необходимые данные с общего тела что приходит нам с Fakestore.
     */
    List<RequestAd> parseAds(int limit);
}
