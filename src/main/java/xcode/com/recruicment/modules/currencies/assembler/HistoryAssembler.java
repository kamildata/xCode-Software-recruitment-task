package xcode.com.recruicment.modules.currencies.assembler;

import org.springframework.stereotype.Component;
import xcode.com.recruicment.modules.currencies.dto.HistoryDto;
import xcode.com.recruicment.modules.currencies.model.History;

@Component
public class HistoryAssembler {

    public HistoryDto map(History history){
        HistoryDto historyDto = new HistoryDto();
        historyDto.setCurrency(history.getCurrency());
        historyDto.setDate(history.getRequest_send_date());
        historyDto.setValue(history.getCurrency_value());
        historyDto.setName(history.getName());
        return historyDto;
    }
}
