package xcode.com.recruicment.modules.currencies.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Rate {
    private String no;
    private String effectiveDate;
    private BigDecimal mid;
}
