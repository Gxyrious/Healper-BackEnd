package cn.edu.tongji.healper.controller;

import cn.edu.tongji.healper.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "api/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    // 示例3，添加一条咨询记录，包含clientId, consultantId和花费
    @PostMapping(value = "add")
    public ResponseEntity addHistory(@RequestBody Map<String, Object> map) {
        Integer clientId = Integer.parseInt(map.get("clientId").toString());
        Integer consultantId = Integer.parseInt(map.get("consultantId").toString());
        Integer expense = Integer.parseInt(map.get("expense").toString());
        if(historyService.addConsultHistory(clientId, consultantId, expense)) {
            return ResponseEntity.ok("");
        } else {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout!");
        }
    }
}
