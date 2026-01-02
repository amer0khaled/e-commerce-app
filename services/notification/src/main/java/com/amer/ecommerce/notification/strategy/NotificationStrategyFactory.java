package com.amer.ecommerce.notification.strategy;

import com.amer.ecommerce.notification.domain.NotificationType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class NotificationStrategyFactory {

    private final List<NotificationStrategy> strategies;
    private final Map<NotificationType, NotificationStrategy> strategyMap =
            new EnumMap<>(NotificationType.class);

    @PostConstruct
    void init() {
        for (NotificationStrategy strategy : strategies) {
            for (NotificationType type : NotificationType.values()) {
                if (strategy.supports(type)) {
                    strategyMap.put(type, strategy);
                }
            }
        }
    }

    public NotificationStrategy getStrategy(NotificationType type) {
        NotificationStrategy strategy = strategyMap.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException(
                    "No NotificationStrategy found for type: " + type
            );
        }
        return strategy;
    }

}
