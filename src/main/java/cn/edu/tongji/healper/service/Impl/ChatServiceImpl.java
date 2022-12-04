package cn.edu.tongji.healper.service.Impl;

import cn.edu.tongji.healper.entity.ChatMessageEntity;
import cn.edu.tongji.healper.repository.ChatMessageRepository;
import cn.edu.tongji.healper.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        chatMessage.setContent(content);
        try {
            return chatMessageRepository.save(chatMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ChatMessageEntity> findChatMessageEntitiesByClientIdAndConsultantId(
            Integer clientId, Integer consultantId, Integer page, Integer size
    ) {
        Pageable pageRequest = PageRequest.of(page - 1, size);
        List<ChatMessageEntity> messages =  chatMessageRepository
                .findChatMessageEntitiesByClientIdAndConsultantId(clientId, consultantId, pageRequest);
        return messages;
    }
}
