package cn.edu.tongji.healper.controller;


import cn.edu.tongji.healper.entity.ChatMessageEntity;
import cn.edu.tongji.healper.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;


    @GetMapping(value = "records")
    public ResponseEntity getChatRecords(
            @RequestParam Integer clientId, @RequestParam Integer consultantId,
            @RequestParam Integer page, @RequestParam Integer size
            ) {
        try {
            List<ChatMessageEntity> messages = chatService.
                    findChatMessageEntitiesByClientIdAndConsultantId(clientId, consultantId, page, size);
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(e);
        }
    }

    @GetMapping(value = "record")
    public ResponseEntity getChatRecord(@RequestParam Integer msgId) {
        try {
            ChatMessageEntity messages = chatService.findMessageById(msgId);
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(e);
        }
    }
}
