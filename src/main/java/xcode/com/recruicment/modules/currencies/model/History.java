package xcode.com.recruicment.modules.currencies.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="history")
@Data
public class History {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String currency;
    private String name;
    private Date request_send_date;
    private BigDecimal currency_value;
}
