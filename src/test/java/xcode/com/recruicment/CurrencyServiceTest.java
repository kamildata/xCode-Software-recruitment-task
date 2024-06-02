package xcode.com.recruicment;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import xcode.com.recruicment.modules.currencies.assembler.HistoryAssembler;
import xcode.com.recruicment.modules.currencies.dto.CurrencyDto;
import xcode.com.recruicment.modules.currencies.dto.CurrencyResponse;
import xcode.com.recruicment.modules.currencies.dto.HistoryDto;
import xcode.com.recruicment.modules.currencies.model.History;
import xcode.com.recruicment.modules.currencies.repository.HistoryRepository;
import xcode.com.recruicment.modules.currencies.service.CurrencyService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CurrencyServiceTest {
    @InjectMocks
    private CurrencyService currencyService;

    @Mock
    private HistoryRepository historyRepository;

    @Mock
    private HistoryAssembler historyAssembler;

    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testGetCurrencyValue_Success() {

    }

    @Test
    public void testGetHistoryOfRequest() {
        // Given
        int page = 0;
        int size = 10;

        List<History> historyList = new ArrayList<>();
        History history = new History();
        history.setCurrency("USD");
        history.setName("Test");
        history.setRequest_send_date(new Date());
        history.setCurrency_value(new BigDecimal("10.50"));
        historyList.add(history);

        Page<History> historyPage = new PageImpl<>(historyList);

        when(historyRepository.findAll(any(Pageable.class))).thenReturn(historyPage);

        HistoryDto historyDto = new HistoryDto();
        historyDto.setCurrency("USD");
        historyDto.setName("Test");
        historyDto.setDate(new Date());
        historyDto.setValue(new BigDecimal("10.50"));

        when(historyAssembler.map(history)).thenReturn(historyDto);

        // When
        Page<HistoryDto> result = currencyService.getHistoryOfRequest(page, size);

        // Then
        assertEquals(1, result.getContent().size());
        assertEquals("USD", result.getContent().get(0).getCurrency());
        assertEquals("Test", result.getContent().get(0).getName());

    }

}
