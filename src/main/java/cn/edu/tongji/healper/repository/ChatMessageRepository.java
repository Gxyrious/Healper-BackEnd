package cn.edu.tongji.healper.repository;

import cn.edu.tongji.healper.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Integer> {
}
