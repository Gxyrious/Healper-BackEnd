package cn.edu.tongji.healper.service;

import cn.edu.tongji.healper.entity.PsychologyScaleEntity;
import cn.edu.tongji.healper.entity.ScaleRecordEntity;

import java.util.List;

public interface ScaleService {
    List<ScaleRecordEntity> findScaleRecordEntitiesByClientId(Integer clientId, Integer page, Integer size);

    void deleteScaleRecord(Integer id);

    List<PsychologyScaleEntity> findScaleNames(Integer page, Integer size);

    PsychologyScaleEntity findSingleScale(Integer scaleId);

}
