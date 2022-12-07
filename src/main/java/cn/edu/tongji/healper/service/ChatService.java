package cn.edu.tongji.healper.service;

import cn.edu.tongji.healper.entity.ChatMessageEntity;
import cn.edu.tongji.healper.outdto.UserType;

import java.util.List;

public interface ChatService {

    ChatMessageEntity addChatMessage(Integer clientId, Integer consultantId, String content, UserType sender);

    List<ChatMessageEntity> findChatMessageEntitiesByClientIdAndConsultantId(
            Integer clientId, Integer consultantId, Integer page, Integer size
    );

    ChatMessageEntity findMessageById(Integer msgId);
}
