package xcode.com.recruicment.modules.currencies.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Data
public class CurrencyValueDto {
        private BigDecimal value;
}
