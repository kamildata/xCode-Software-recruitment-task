package xcode.com.recruicment.modules.currencies.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class CurrencyResponse {
    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;
}
