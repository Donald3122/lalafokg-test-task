package task.tynybekoddarmanbektesttask.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import task.tynybekoddarmanbektesttask.dto.RequestAd;
import task.tynybekoddarmanbektesttask.service.IAdService;
import task.tynybekoddarmanbektesttask.service.ParserService;

import java.io.IOException;
import java.util.List;
@Service

public class IAdServiceImpl implements IAdService {
    private final ParserService parser;

    public IAdServiceImpl(ParserService parser) {
        this.parser = parser;
    }

    @Override
    public List<RequestAd> getAds(int limit) {
        return parser.parseAds(limit);
    }
}
