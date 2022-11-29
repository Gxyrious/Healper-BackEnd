package cn.edu.tongji.healper.controller;

import cn.edu.tongji.healper.indto.UserInDto;
import cn.edu.tongji.healper.model.ScaleRecordEntity;
import cn.edu.tongji.healper.outdto.ScaleRecordDto;
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
    public ResponseEntity getScaleRecord(@RequestParam int client_id) {
        List<ScaleRecordEntity> scaleRecordEntities = scaleService.findScaleRecordEntitiesByClientId(client_id);
        ScaleRecordDto scaleRecordDto = new ScaleRecordDto();
        scaleRecordDto.setScaleRecordEntities(scaleRecordEntities);
        return ResponseEntity.ok(scaleRecordDto);
    }

    //增加/修改测评记录
    @PostMapping(value = "/update")
    public ResponseEntity updateScaleRecord(@RequestBody ScaleRecordEntity scaleRecordEntity) {
        return ResponseEntity.ok("");
    }

    //删除测评记录
    @PostMapping (value = "/delete")
    public ResponseEntity deleteScaleRecord(@RequestParam Integer id){
        try {
            scaleService.deleteScaleRecord(id);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
