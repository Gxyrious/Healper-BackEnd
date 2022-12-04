package cn.edu.tongji.healper.controller;


import cn.edu.tongji.healper.entity.PsychologyScaleEntity;
import cn.edu.tongji.healper.entity.ScaleRecordEntity;
import cn.edu.tongji.healper.po.ScaleRecordInfo;
import cn.edu.tongji.healper.service.ScaleService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/scale")
public class ScaleController {
    @Autowired
    private ScaleService scaleService;

    //分页查询测评记录
    @GetMapping(value = "/getRecord")
    public ResponseEntity getScaleRecord(@RequestParam Integer clientId,
                                         @RequestParam Integer page,
                                         @RequestParam Integer size) {
        List<ScaleRecordInfo> scaleRecordInfos = scaleService.findScaleRecordInfoByClientId(clientId, page, size);
        return ResponseEntity.ok(scaleRecordInfos);
    }

    //查询测评记录总数
    @GetMapping(value = "/sum")
    public ResponseEntity getScaleRecordNum(@RequestParam Integer clientId) {
        Integer sum = scaleService.countScaleRecordEntitiesByClientId(clientId);
        return sum != null ? ResponseEntity.ok(sum) : ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body("Id doesn't exist!");
    }

    //增加/修改测评记录
    @PostMapping(value = "/update")
    public ResponseEntity updateScaleRecord(@RequestBody ScaleRecordEntity scaleRecordEntity) {
        return ResponseEntity.ok(scaleService.updateScaleRecord(scaleRecordEntity));
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
