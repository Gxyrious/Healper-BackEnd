package cn.edu.tongji.healper.service;

import cn.edu.tongji.healper.entity.ChatMessageEntity;

public interface ChatService {

    ChatMessageEntity addChatMessage(Integer clientId, Integer consultantId, String content);
}
