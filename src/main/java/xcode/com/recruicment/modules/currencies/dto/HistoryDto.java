package xcode.com.recruicment.modules.currencies.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class HistoryDto {
    private String currency;
    private String name;
    private Date date;
    private BigDecimal value;
}
