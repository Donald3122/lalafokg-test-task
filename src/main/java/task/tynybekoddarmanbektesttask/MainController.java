package task.tynybekoddarmanbektesttask;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import task.tynybekoddarmanbektesttask.dto.RequestAd;
import task.tynybekoddarmanbektesttask.service.IAdService;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller

public class MainController {
    private final IAdService service;

    public MainController(IAdService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public String showProducts(@RequestParam(defaultValue = "20") int limit, Model model) throws IOException {
        List<RequestAd> products = service.getAds(limit);
        log.info("Что внутри products = {}",products.toString());
        model.addAttribute("products", products);
        return "products"; // шаблон products.html
    }
}
