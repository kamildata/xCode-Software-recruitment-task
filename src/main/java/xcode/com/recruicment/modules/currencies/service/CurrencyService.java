package xcode.com.recruicment.modules.currencies.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import xcode.com.recruicment.modules.currencies.assembler.HistoryAssembler;
import xcode.com.recruicment.modules.currencies.dto.CurrencyDto;
import xcode.com.recruicment.modules.currencies.dto.CurrencyResponse;
import xcode.com.recruicment.modules.currencies.dto.HistoryDto;
import xcode.com.recruicment.modules.currencies.model.History;
import xcode.com.recruicment.modules.currencies.repository.HistoryRepository;

import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final WebClient.Builder webClientBuilder;
    private final HistoryRepository historyRepository;
    private final HistoryAssembler historyAssembler;


    public Mono<ResponseEntity<CurrencyResponse>> getCurrencyValue(CurrencyDto currencyDto) {
        WebClient webClient = webClientBuilder.baseUrl("http://api.nbp.pl").build();

        return webClient.get()
                .uri("/api/exchangerates/rates/A/{code}?format=json", currencyDto.getCurrency())
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(CurrencyResponse.class)
                                .flatMap(currencyResponse -> {
                                    saveToDatabase(currencyResponse.getCode(), currencyDto.getName(), currencyResponse.getRates().get(0).getMid());
                                    return Mono.just(ResponseEntity.ok(currencyResponse));
                                });
                    } else if (clientResponse.statusCode().equals(HttpStatus.BAD_REQUEST)) {
                        return Mono.just(ResponseEntity.badRequest().build());
                    } else if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
                    } else {
                        return Mono.error(new RuntimeException("Unexpected status code: " + clientResponse.statusCode()));
                    }
                });
    }


    private void saveToDatabase(String currency, String name, BigDecimal currencyValue) {
        History history = new History();
        history.setCurrency(currency);
        history.setName(name);
        history.setRequest_send_date(new Date());
        history.setCurrency_value(currencyValue);
        historyRepository.save(history);
    }

    public Page<HistoryDto> getHistoryOfRequest(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return historyRepository.findAll(pageable)
                .map(historyAssembler::map);
    }
}



