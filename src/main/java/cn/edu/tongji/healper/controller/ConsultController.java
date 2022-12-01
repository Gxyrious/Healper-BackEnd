package cn.edu.tongji.healper.controller;

import cn.edu.tongji.healper.indto.ConsultTimeInDto;
import cn.edu.tongji.healper.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/consult")
public class ConsultController {

    @Autowired
    private ConsultService consultService;

    @PutMapping(value = "start")
    public ResponseEntity startConsultation(@RequestBody ConsultTimeInDto inDto) {
        Integer startTime = inDto.getTime();
        if (startTime >= System.currentTimeMillis()) {
            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body("Time error!");
        } else if (consultService.startConsultation(inDto.getOrderId(), startTime)) {
            return ResponseEntity.ok("Consultation started!");
        } else {
            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body("Failed to start!");
        }
    }

    @PutMapping(value = "end")
    public ResponseEntity endConsultation(@RequestBody ConsultTimeInDto inDto) {
        Integer endTime = inDto.getTime();
        if (endTime >= System.currentTimeMillis()) {
            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body("Time error!");
        } else if (consultService.endConsultation(inDto.getOrderId(), endTime)) {
            return ResponseEntity.ok("Consultation ended!");
        } else {
            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body("Failed to end!");
        }
    }
}
