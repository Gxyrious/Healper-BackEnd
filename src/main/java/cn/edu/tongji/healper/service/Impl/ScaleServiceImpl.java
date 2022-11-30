package cn.edu.tongji.healper.service.Impl;

import cn.edu.tongji.healper.model.PsychologyScaleEntity;
import cn.edu.tongji.healper.model.ScaleRecordEntity;
import cn.edu.tongji.healper.repository.PsychologyScaleRepository;
import cn.edu.tongji.healper.repository.ScaleRecordRepository;
import cn.edu.tongji.healper.service.ScaleService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScaleServiceImpl implements ScaleService {
    @Resource
    private ScaleRecordRepository scaleRecordRepository;

    @Resource
    private PsychologyScaleRepository psychologyScaleRepository;

    @Override
    public List<ScaleRecordEntity> findScaleRecordEntitiesByClientId(Integer client_id) {
        return scaleRecordRepository.findScaleRecordEntitiesByClientId(client_id);
    }

    @Override
    public void deleteScaleRecord(Integer id) {
        scaleRecordRepository.deleteById(id);
    }

    @Override
    public List<PsychologyScaleEntity> findScaleNames(Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        return psychologyScaleRepository.findScales(pageRequest);
    }

    @Override
    public PsychologyScaleEntity findSingleScale(Integer scaleId) {
        return psychologyScaleRepository.findPsychologyScaleEntityById(scaleId);
    }

}
