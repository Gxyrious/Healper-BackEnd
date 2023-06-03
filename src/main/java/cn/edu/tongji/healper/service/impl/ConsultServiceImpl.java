package cn.edu.tongji.healper.service.impl;

import cn.edu.tongji.healper.entity.ChatMessageEntity;
import cn.edu.tongji.healper.entity.ConsultHistoryEntity;
import cn.edu.tongji.healper.outdto.UserType;
import cn.edu.tongji.healper.repository.ChatMsgRepository;
import cn.edu.tongji.healper.repository.ConsultHistoryRepository;
import cn.edu.tongji.healper.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultServiceImpl implements ConsultService {

    @Autowired
    private ConsultHistoryRepository historyRepository;

    @Autowired
    ChatMsgRepository chatMessageRepository;


    @Override
    public ChatMessageEntity addChatMessage(Integer clientId, Integer consultantId, String content, UserType sender) {
        ChatMessageEntity chatMessage = new ChatMessageEntity();
        chatMessage.setClientId(clientId);
        chatMessage.setConsultantId(consultantId);
        chatMessage.setCreateTime(System.currentTimeMillis() / 1000);
        chatMessage.setContent(content);
        chatMessage.setSender(sender == UserType.client ? "0" : "1");
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
        List<ChatMessageEntity> messages =  chatMessageRepository
                .findChatMessageEntitiesByClientIdAndConsultantId(clientId, consultantId);
        return messages;
    }

    @Override
    public ChatMessageEntity findMessageById(Integer msgId) {
        return chatMessageRepository.findById(msgId).get();
    }


    @Override
    public Boolean startConsultation(Integer orderId, Long startTime) {
        ConsultHistoryEntity order = historyRepository.getConsultHistoryEntityById(orderId);
        order.setStartTime(startTime);
        try {
            historyRepository.save(order);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean endConsultation(Integer orderId, Long endTime) {
        ConsultHistoryEntity order = historyRepository.getConsultHistoryEntityById(orderId);
        order.setEndTime(endTime);
        try {
            historyRepository.save(order);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
