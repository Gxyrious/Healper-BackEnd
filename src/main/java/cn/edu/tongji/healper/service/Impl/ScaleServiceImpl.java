package cn.edu.tongji.healper.service.Impl;

import cn.edu.tongji.healper.entity.PsychologyScaleEntity;
import cn.edu.tongji.healper.entity.ScaleRecordEntity;
import cn.edu.tongji.healper.outdto.BasicScale;
import cn.edu.tongji.healper.po.ScaleRecordInfo;
import cn.edu.tongji.healper.repository.PsychologyScaleRepository;
import cn.edu.tongji.healper.repository.ScaleRecordRepository;
import cn.edu.tongji.healper.service.ScaleService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
    public List<BasicScale> findBasicScales(Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page - 1, size, Sort.Direction.ASC, "id");
        return psychologyScaleRepository.findScales(pageRequest);
    }

    @Override
    public PsychologyScaleEntity findSingleScale(Integer scaleId) {
        return psychologyScaleRepository.findPsychologyScaleEntityById(scaleId);
    }

    @Override
    public ScaleRecordInfo findScaleRecordInfoById(Integer recordId) {
        return scaleRecordRepository.findScaleRecordInfoById(recordId);
    }

    @Override
    public String getJsonScaleByClientId(Integer clientId) {
        Pageable pageable = PageRequest.of(0, 10);
        JSONArray jsonResult = new JSONArray();
        List<ScaleRecordInfo> records = scaleRecordRepository.findScaleRecordInfoByClientId(clientId, pageable);
        if (records.isEmpty()) {
            throw new RuntimeException("No scale record!");
        } else {
            String json = records.get(0).getRecord();
            JSONArray factors = JSONObject.parseArray(json);
            for (Object obj : factors) {
                String factor = ((JSONObject) obj).get("factor").toString();
                JSONObject factorRecords = new JSONObject();
                factorRecords.put("factor", factor);
                factorRecords.put("detail", new JSONArray());
                jsonResult.add(factorRecords);
            }
            for (Integer i = 0; i < records.size(); i++) {
                ScaleRecordInfo record = records.get(i);
                Long endTime = record.getEndTime();
                factors = JSONObject.parseArray(record.getRecord());
                System.out.println(factors.size());
                System.out.println(factors.toJSONString());
                for (Integer j = 0; j < factors.size(); j++) {
                    Object obj = factors.get(j);
                    String value = ((JSONObject) obj).get("value").toString();
                    JSONArray detail = (JSONArray) ((JSONObject) jsonResult.get(j)).get("detail");
                    JSONObject info = new JSONObject();
                    info.put("time", endTime);
                    info.put("value", value);
                    detail.add(info);
                }
            }
            return jsonResult.toJSONString();
        }
    }
}
