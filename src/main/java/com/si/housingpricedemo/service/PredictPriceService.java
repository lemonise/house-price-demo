package com.si.housingpricedemo.service;

import com.si.housingpricedemo.domain.HouseDetails;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class PredictPriceService {

    public BigDecimal predictPrice(HouseDetails houseDetails) throws IOException {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(List.of(houseDetails));
        json = json.replace("\n", "").replace("\r", "").replace("\s","").replace("\"","\\\"");

        String scriptFile = "predict_from_json.py";
        String path = "E:\\DEV\\housing-price-demo\\src\\main\\resources\\python\\" + scriptFile;
        String line = "C:\\Windows\\py " + path;

        CommandLine cmdLine = CommandLine.parse(line);
        cmdLine.addArgument(json, false);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);

        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(streamHandler);

        executor.execute(cmdLine);

        String scriptResult = outputStream.toString().trim();

        String[] lines = scriptResult.split(System.lineSeparator());
        String result = lines[lines.length - 1];

        return BigDecimal.valueOf(Double.parseDouble(result)).setScale(2, RoundingMode.HALF_UP);
    }

}
