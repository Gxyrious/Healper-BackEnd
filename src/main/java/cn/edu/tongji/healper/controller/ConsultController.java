package cn.edu.tongji.healper.controller;

import cn.edu.tongji.healper.entity.ChatMessageEntity;
import cn.edu.tongji.healper.indto.ConsultTimeInDto;
import cn.edu.tongji.healper.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/consult")
public class ConsultController {

    @Autowired
    private ConsultService consultService;

    @PutMapping(value = "start")
    public ResponseEntity startConsultation(@RequestBody ConsultTimeInDto inDto) {
        Long startTime = inDto.getTime();
        if (consultService.startConsultation(inDto.getOrderId(), startTime)) {
            return ResponseEntity.ok("Consultation started!");
        } else {
            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body("Failed to start!");
        }
    }

    @PutMapping(value = "end")
    public ResponseEntity endConsultation(@RequestBody ConsultTimeInDto inDto) {
        Long endTime = inDto.getTime();
        if (consultService.endConsultation(inDto.getOrderId(), endTime)) {
            return ResponseEntity.ok("Consultation ended!");
        } else {
            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body("Failed to end!");
        }
    }

    @GetMapping(value = "appointed")
    public ResponseEntity getAppointedConsultation(@RequestParam Integer clientId) {
        try {
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(e);
        }
    }

    @GetMapping(value = "records")
    public ResponseEntity getChatRecords(
            @RequestParam Integer clientId, @RequestParam Integer consultantId,
            @RequestParam Integer page, @RequestParam Integer size
    ) {
        try {
            List<ChatMessageEntity> messages = consultService.
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
            ChatMessageEntity messages = consultService.findMessageById(msgId);
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(e);
        }
    }
}
