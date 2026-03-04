package task.tynybekoddarmanbektesttask.service;

public interface HttpFetcher {
    /**
     * Интерфейс для отключения SSL сертификата .
     * */
    String fetch(String url) throws Exception;
}
