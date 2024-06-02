package xcode.com.recruicment.modules.currencies.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import xcode.com.recruicment.modules.currencies.assembler.CurrencyAssembler;
import xcode.com.recruicment.modules.currencies.dto.CurrencyDto;
import xcode.com.recruicment.modules.currencies.dto.CurrencyValueDto;
import xcode.com.recruicment.modules.currencies.dto.HistoryDto;
import xcode.com.recruicment.modules.currencies.service.CurrencyService;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;
    private final CurrencyAssembler currencyAssembler;

    @PostMapping("/get-current-currency-value-command")
    public Mono<ResponseEntity<CurrencyValueDto>> getCurrencyValue(@RequestBody CurrencyDto currencyDto) {
        return currencyAssembler.map(currencyService.getCurrencyValue(currencyDto));
    }

    @GetMapping("/requests")
    public Page<HistoryDto> getHistoryOfRequest(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return currencyService.getHistoryOfRequest(page, size);
    }

}
