package cn.edu.tongji.healper.controller;

import cn.edu.tongji.healper.indto.HistoryStatusInDto;
import cn.edu.tongji.healper.outdto.Archive;
import cn.edu.tongji.healper.po.ConsultOrder;
import cn.edu.tongji.healper.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    // 示例3，添加一条咨询记录，包含clientId, consultantId和花费
    @PostMapping(value = "add")
    public ResponseEntity<String> addHistory(@RequestBody Map<String, Object> map) {
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
    public ResponseEntity<String> getQrCodeByHistoryId(@RequestParam Integer historyId) {
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
        HashSet<Character> statusSet = new HashSet<>();
        statusSet.add('w'); // 等候中
        statusSet.add('p'); // 待付款
        statusSet.add('f'); // 已完成
        statusSet.add('c'); // 已取消
        if (status.length() == 1 && statusSet.contains(status.charAt(0))) {
            // 数据无误
            Boolean result = historyService.updateHistoryStatusById(inDto.getHistoryId(), status);
            if (result) {
                return ResponseEntity.ok(status);
            } else {
                return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body("HistoryId not found!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body("Data 'status' isn't one of ('w', 'p', 'f', 'c)");
        }
    }

    @GetMapping(value = "/archive/sum")
    public ResponseEntity<Integer> getAllArchiveNumByClientId(@RequestParam Integer clientId) {
        Integer archivesNum = historyService.getAllArchive(clientId);
        return ResponseEntity.ok(archivesNum);
    }

    @GetMapping(value = "/archive/getSome")
    public ResponseEntity getSomeArchiveByClientId(
            @RequestParam Integer clientId,
            @RequestParam Integer page,
            @RequestParam Integer size) {
        List<Archive> archives = historyService.getSomeArchive(clientId, page - 1, size);
        if (archives != null && archives.size() > 0) {
            return ResponseEntity.ok(archives);
        }
        return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body("No Archives found!");
    }

}
