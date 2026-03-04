package task.tynybekoddarmanbektesttask.service;

import task.tynybekoddarmanbektesttask.dto.RequestAd;

import java.io.IOException;
import java.util.List;

public interface IAdService {
    List<RequestAd> getAds(int limit);
}
