package xcode.com.recruicment.modules.currencies.assembler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import xcode.com.recruicment.modules.currencies.dto.CurrencyResponse;
import xcode.com.recruicment.modules.currencies.dto.CurrencyValueDto;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CurrencyAssembler {

    public Mono<ResponseEntity<CurrencyValueDto>> map(Mono<ResponseEntity<CurrencyResponse>> currencyResponseMono) {
        return currencyResponseMono.map(currencyResponseEntity -> {
            CurrencyResponse currencyResponse = currencyResponseEntity.getBody();
            if (currencyResponse != null && !currencyResponse.getRates().isEmpty()) {
                BigDecimal mid = currencyResponse.getRates().get(0).getMid();
                CurrencyValueDto currencyValueDto = new CurrencyValueDto();
                currencyValueDto.setValue(mid);

                return ResponseEntity.ok(currencyValueDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        });
    }
}
