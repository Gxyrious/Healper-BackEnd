package cn.edu.tongji.healper.service.Impl;

import cn.edu.tongji.healper.entity.ChatMessageEntity;
import cn.edu.tongji.healper.repository.ChatMessageRepository;
import cn.edu.tongji.healper.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatMessageRepository chatMessageRepository;


    @Override
    public ChatMessageEntity addChatMessage(Integer clientId, Integer consultantId, String content) {
        ChatMessageEntity chatMessage = new ChatMessageEntity();
        chatMessage.setClientId(clientId);
        chatMessage.setConsultantId(consultantId);
        chatMessage.setCreateTime(System.currentTimeMillis());
        try {
            return chatMessageRepository.save(chatMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
