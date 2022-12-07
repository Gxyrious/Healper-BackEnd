package cn.edu.tongji.healper.service;

import cn.edu.tongji.healper.entity.PsychologyScaleEntity;
import cn.edu.tongji.healper.entity.ScaleRecordEntity;
import cn.edu.tongji.healper.outdto.BasicScale;
import cn.edu.tongji.healper.po.ScaleRecordInfo;

import java.util.List;

public interface ScaleService {
    List<ScaleRecordInfo> findScaleRecordInfoByClientId(Integer clientId, Integer page, Integer size);
    Integer countScaleRecordEntitiesByClientId(Integer clientId);

    void deleteScaleRecord(Integer id);

    ScaleRecordEntity updateScaleRecord(ScaleRecordEntity scaleRecordEntity);

    List<BasicScale> findBasicScales(Integer page, Integer size);

    PsychologyScaleEntity findSingleScale(Integer scaleId);

    ScaleRecordInfo findScaleRecordInfoById(Integer recordId);

    String getJsonScaleByClientId(Integer clientId);
}
