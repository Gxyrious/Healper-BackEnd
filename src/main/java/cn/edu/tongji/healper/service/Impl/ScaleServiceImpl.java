package cn.edu.tongji.healper.service.Impl;

import cn.edu.tongji.healper.model.PsychologyScaleEntity;
import cn.edu.tongji.healper.model.ScaleRecordEntity;
import cn.edu.tongji.healper.repository.ScaleRecordRepository;
import cn.edu.tongji.healper.service.ScaleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScaleServiceImpl implements ScaleService {
    @Resource
    private ScaleRecordRepository scaleRecordRepository;

    @Override
    public List<ScaleRecordEntity> findScaleRecordEntitiesByClientId(Integer client_id) {
        return scaleRecordRepository.findScaleRecordEntitiesByClientId(client_id);
    }

    @Override
    public void deleteScaleRecord(Integer id) {
        scaleRecordRepository.deleteById(id);
    }

}
