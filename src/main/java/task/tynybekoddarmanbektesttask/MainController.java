package task.tynybekoddarmanbektesttask;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import task.tynybekoddarmanbektesttask.dto.RequestAd;
import task.tynybekoddarmanbektesttask.service.ParserService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final ParserService service;
    @GetMapping("/test-result")
    public String showProducts(@RequestParam(defaultValue = "100") int limit, Model model) {
        List<RequestAd> products = service.parseAds(limit);
        log.info("Загружено {} продуктов", products.size());
        model.addAttribute("products", products);
        return "products"; // thymeleaf-шаблон products.html
    }
}
