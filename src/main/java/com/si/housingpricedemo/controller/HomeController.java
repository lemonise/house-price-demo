package com.si.housingpricedemo.controller;

import com.si.housingpricedemo.domain.HouseDetails;
import com.si.housingpricedemo.service.PredictPriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
public class HomeController {

    private final PredictPriceService predictPriceService;

    HomeController(PredictPriceService predictPriceService) {
        this.predictPriceService = predictPriceService;
    }

    @GetMapping("/")
    String home() {
        return "home";
    }

    @GetMapping("/house")
    HouseDetails houseDetails() {
        return new HouseDetails(70, 4, 50.5f, 45.0f, 777, "CA", true);
    }

    @PostMapping("/predict")
    BigDecimal predict(@RequestBody HouseDetails houseDetails) throws IOException {
        return predictPriceService.predictPrice(houseDetails);
    }

}
