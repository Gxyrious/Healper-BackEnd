package cn.edu.tongji.healper.controller;

import cn.edu.tongji.healper.entity.ConsultHistoryEntity;
import cn.edu.tongji.healper.indto.ConsultRecordInDto;
import cn.edu.tongji.healper.indto.HistoryStatusInDto;
import cn.edu.tongji.healper.outdto.Archive;
import cn.edu.tongji.healper.po.ConsultOrder;
import cn.edu.tongji.healper.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(value = "api/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    // 示例3，添加一条咨询记录，包含clientId, consultantId和花费
    @PostMapping(value = "add")
    public ResponseEntity addHistory(@RequestBody ConsultRecordInDto inDto) {
        try {
            List<ConsultOrder> waitingOrders = historyService.findWaitingOrdersByClientId(inDto.getClientId());
            if (waitingOrders.isEmpty()) {
                Integer historyId = historyService.addConsultHistory(
                        inDto.getClientId(),
                        inDto.getConsultantId(),
                        inDto.getExpense()
                );
                return ResponseEntity.ok(historyId);
            } else {
                return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(waitingOrders);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @GetMapping(value = "archives")
    public ResponseEntity getArchivesByClientId(
            @RequestParam Integer clientId,
            @RequestParam Integer page,
            @RequestParam Integer size) {
        try {
            List<Archive> archives = historyService.findArchiveByClientId(clientId, page, size);
            return ResponseEntity.ok(archives);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(e);
        }
    }

    @GetMapping(value = "records")
    public ResponseEntity getConsultRecordsByClientId(
            @RequestParam Integer clientId,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        try {
            List<ConsultHistoryEntity> records = historyService.findRecordsByClientId(clientId, page, size);
            return ResponseEntity.ok(records);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(e);
        }
    }

    @GetMapping(value = "orders")
    public ResponseEntity getConsultOrdersByClientId(
            @RequestParam Integer clientId,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        try {
            List<ConsultOrder> orders = historyService.findConsultOrdersByClientId(clientId, page, size);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(e);
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
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body("Data 'status' isn't one of ('w', 'p', 'f', 'c')");
        }
    }

    @GetMapping(value = "archive/sum")
    public ResponseEntity getArchiveNumByClientId(@RequestParam Integer clientId) {
        try {
            Integer num = historyService.findArchiveNumByClientId(clientId);
            return ResponseEntity.ok(num);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(e);
        }
    }

    @GetMapping(value = "order/sum")
    public ResponseEntity getOrderNumByClientId(@RequestParam Integer clientId) {
        try {
            Integer num = historyService.getOrderNumByClientId(clientId);
            return ResponseEntity.ok(num);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(e);
        }
    }

    @GetMapping(value = "record/sum")
    public ResponseEntity getRecordNumByClientId(@RequestParam Integer clientId) {
        try {
            Integer num = historyService.getRecordNumByClientId(clientId);
            return ResponseEntity.ok(num);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(e);
        }
    }

    @GetMapping(value = "order/waiting")
    public ResponseEntity getWaitingConsultOrder(@RequestParam Integer clientId) {
        try {
            List<ConsultOrder> waitingOrders = historyService.findWaitingOrdersByClientId(clientId);
            if (waitingOrders.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                if (waitingOrders.size() > 1) {
                    // delete the other waiting orders
                    List<Integer> ids = new ArrayList<>();
                    for (int i = 1; i < waitingOrders.size(); i++) {
                        ids.add(waitingOrders.get(i).getId());
                    }
                    historyService.deleteOldWaitingOrdersByIds(ids);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
                } else {
                    return ResponseEntity.ok(waitingOrders.get(0));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

}
