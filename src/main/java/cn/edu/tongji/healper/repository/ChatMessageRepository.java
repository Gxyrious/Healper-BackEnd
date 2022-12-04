package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.entity.ChatMessageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ChatMessageRepository extends
        JpaRepository<ChatMessageEntity, Integer>,
        JpaSpecificationExecutor<ChatMessageEntity> {
    List<ChatMessageEntity> findChatMessageEntitiesByClientIdAndConsultantId(
            Integer clientId, Integer consultantId, Pageable pageable
    );
}
