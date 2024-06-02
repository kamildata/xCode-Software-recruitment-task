package xcode.com.recruicment.modules.currencies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xcode.com.recruicment.modules.currencies.model.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
