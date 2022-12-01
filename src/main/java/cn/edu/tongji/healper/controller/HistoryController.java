package cn.edu.tongji.healper.controller;

import cn.edu.tongji.healper.outdto.HistoryStatusInDto;
import cn.edu.tongji.healper.po.ConsultOrder;
import cn.edu.tongji.healper.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        if (historyService.addConsultHistory(clientId, consultantId, expense)) {
            return ResponseEntity.ok("添加成功！");
        } else {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout!");
        }
    }

    @GetMapping(value = "orders")
    public ResponseEntity getConsultOrdersByClientId(
            @RequestParam Integer clientId,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        List<ConsultOrder> orders = historyService.findConsultOrdersByClientId(clientId, page, size);
        if (orders.size() != 0) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body("No Order found!");
        }
    }

    @GetMapping(value = "pay")
    public ResponseEntity getQrCodeByHistoryId(@RequestParam Integer historyId) {
        String qrCode = historyService.findQrCodeByHistoryId(historyId);
        if (qrCode != null) {
            return ResponseEntity.ok(qrCode);
        } else {
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body("History not found!");
        }
    }

    @PutMapping(value = "status")
    public ResponseEntity updateHistoryStatusById(@RequestBody HistoryStatusInDto inDto) {
        String status = inDto.getStatus();
        Set statusSet = new HashSet<Character>();
        statusSet.add('w'); // 等候中
        statusSet.add('p'); // 待付款
        statusSet.add('f'); // 已完成
        if (status.length() == 1 && statusSet.contains(status.charAt(0))) {
            // 数据无误
            Character c = status.charAt(0);
            Boolean result = historyService.updateHistoryStatusById(inDto.getHistoryId(), c);
            if (result) {
                return ResponseEntity.ok(c);
            } else {
                return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body("HistoryId not found!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body("Data 'status' isn't one of ('w', 'p', 'f')");
        }
    }
}
