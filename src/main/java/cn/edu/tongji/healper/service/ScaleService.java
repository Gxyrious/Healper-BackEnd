package cn.edu.tongji.healper.service;

import cn.edu.tongji.healper.model.ClientEntity;
import cn.edu.tongji.healper.model.PsychologyScaleEntity;
import cn.edu.tongji.healper.model.ScaleRecordEntity;

import java.util.List;

public interface ScaleService {
    List<ScaleRecordEntity> findScaleRecordEntitiesByClientId(Integer client_id);

    void deleteScaleRecord(Integer id);

    List<PsychologyScaleEntity> findPsychologyScaleName(Integer page, Integer size);

}
