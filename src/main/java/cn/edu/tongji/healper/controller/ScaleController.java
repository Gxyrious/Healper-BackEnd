package cn.edu.tongji.healper.controller;


import cn.edu.tongji.healper.entity.PsychologyScaleEntity;
import cn.edu.tongji.healper.entity.ScaleRecordEntity;
import cn.edu.tongji.healper.service.ScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/scale")
public class ScaleController {
    @Autowired
    private ScaleService scaleService;

    //查询测评记录
    @GetMapping(value = "/getRecord")
    public ResponseEntity getScaleRecord(@RequestParam int clientId) {
        List<ScaleRecordEntity> scaleRecordEntities = scaleService.findScaleRecordEntitiesByClientId(clientId);
        return ResponseEntity.ok(scaleRecordEntities);
    }

    //增加/修改测评记录
    @PostMapping(value = "/update")
    public ResponseEntity updateScaleRecord(@RequestBody ScaleRecordEntity scaleRecordEntity) {
        return ResponseEntity.ok("");
    }

    //删除测评记录
    @DeleteMapping(value = "/delete")
    public ResponseEntity deleteScaleRecord(@RequestParam Integer id) {
        try {
            scaleService.deleteScaleRecord(id);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/names")
    public ResponseEntity getPsychologyScales(@RequestParam Integer page, @RequestParam Integer size) {
        List<PsychologyScaleEntity> names = scaleService.findScaleNames(page, size);
        if (names != null) {
            return ResponseEntity.ok(names);
        } else {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout!");
        }
    }

    @GetMapping(value = "/single")
    public ResponseEntity getSingleScaleById(@RequestParam Integer scaleId) {
        PsychologyScaleEntity scale = scaleService.findSingleScale(scaleId);
        return scale != null ? ResponseEntity.ok(scale) : ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body("Index doesn't exist!");
    }

}
