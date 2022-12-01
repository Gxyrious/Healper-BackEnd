package cn.edu.tongji.healper.controller;

import cn.edu.tongji.healper.outdto.Archive;
import cn.edu.tongji.healper.po.ConsultOrder;
import cn.edu.tongji.healper.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping(value = "/archive/sum")
    public ResponseEntity getAllArchiveNumByClientId(@RequestParam Integer clientId) {
        Integer archivesNum = historyService.getAllArchive(clientId).size();
        return ResponseEntity.ok(archivesNum);
    }

    @GetMapping(value = "/archive/getSome")
    public ResponseEntity getSomeArchiveByClientId(@RequestParam Integer clientId, @RequestParam Integer page, @RequestParam Integer size) {
        List<Archive> archives = historyService.getSomeArchive(clientId, page - 1, size);
        if (archives != null && archives.size() > 0)
            return ResponseEntity.ok(archives);
        return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body("No Archives found!");
    }

}
