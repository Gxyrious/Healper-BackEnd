package cn.edu.tongji.healper.service;

import cn.edu.tongji.healper.entity.PsychologyScaleEntity;
import cn.edu.tongji.healper.entity.ScaleRecordEntity;
import cn.edu.tongji.healper.outdto.ScaleInfo;
import cn.edu.tongji.healper.outdto.ScaleRecordInfo;

import java.util.List;
import java.util.Map;

public interface ScaleService {
    List<ScaleRecordInfo> findScaleRecordInfoByClientId(Integer clientId, Integer page, Integer size);
    Integer countScaleRecordEntitiesByClientId(Integer clientId);

    void deleteScaleRecord(Integer id);

    ScaleRecordEntity updateScaleRecord(ScaleRecordEntity scaleRecordEntity);

    List<ScaleInfo> findBasicScales(Integer page, Integer size);

    PsychologyScaleEntity findSingleScale(Integer scaleId);

    ScaleRecordInfo findScaleRecordInfoById(Integer recordId);

    String getJsonScaleByClientId(Integer clientId);

    Map<String, Integer> findLabelsWithClient(Integer clientId);
}
