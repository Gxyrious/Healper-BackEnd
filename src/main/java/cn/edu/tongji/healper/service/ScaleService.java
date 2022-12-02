package cn.edu.tongji.healper.service;

import cn.edu.tongji.healper.entity.PsychologyScaleEntity;
import cn.edu.tongji.healper.entity.ScaleRecordEntity;
import cn.edu.tongji.healper.outdto.ScaleRecordDto;

import java.util.List;

public interface ScaleService {
    List<ScaleRecordDto> findScaleRecordDtoByClientId(Integer clientId, Integer page, Integer size);
    Integer countScaleRecordEntitiesByClientId(Integer clientId);

    void deleteScaleRecord(Integer id);

    List<PsychologyScaleEntity> findScaleNames(Integer page, Integer size);

    PsychologyScaleEntity findSingleScale(Integer scaleId);

}
