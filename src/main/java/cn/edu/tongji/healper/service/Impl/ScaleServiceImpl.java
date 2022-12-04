package cn.edu.tongji.healper.service.Impl;

import cn.edu.tongji.healper.entity.PsychologyScaleEntity;
import cn.edu.tongji.healper.entity.ScaleRecordEntity;
import cn.edu.tongji.healper.po.ScaleRecordInfo;
import cn.edu.tongji.healper.repository.PsychologyScaleRepository;
import cn.edu.tongji.healper.repository.ScaleRecordRepository;
import cn.edu.tongji.healper.service.ScaleService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScaleServiceImpl implements ScaleService {
    @Resource
    private ScaleRecordRepository scaleRecordRepository;

    @Resource
    private PsychologyScaleRepository psychologyScaleRepository;

    @Override
    public Integer countScaleRecordEntitiesByClientId(Integer clientId){
        return scaleRecordRepository.countScaleRecordEntitiesByClientId(clientId);
    }
    @Override
    public List<ScaleRecordInfo> findScaleRecordInfoByClientId(Integer clientId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return scaleRecordRepository.findScaleRecordInfoByClientId(clientId, pageable);
    }

    @Override
    public ScaleRecordEntity updateScaleRecord(ScaleRecordEntity scaleRecordEntity){
        return scaleRecordRepository.save(scaleRecordEntity);
    }

    @Override
    public void deleteScaleRecord(Integer id) {
        scaleRecordRepository.deleteById(id);
    }

    @Override
    public List<PsychologyScaleEntity> findScaleNames(Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page - 1, size, Sort.Direction.ASC, "id");
        return psychologyScaleRepository.findScales(pageRequest);
    }

    @Override
    public PsychologyScaleEntity findSingleScale(Integer scaleId) {
        return psychologyScaleRepository.findPsychologyScaleEntityById(scaleId);
    }

}
