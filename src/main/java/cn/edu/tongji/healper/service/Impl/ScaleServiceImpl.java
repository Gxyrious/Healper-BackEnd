package cn.edu.tongji.healper.service.impl;

import cn.edu.tongji.healper.entity.PsychologyScaleEntity;
import cn.edu.tongji.healper.entity.ScaleRecordEntity;
import cn.edu.tongji.healper.outdto.ScaleInfo;
import cn.edu.tongji.healper.outdto.ScaleRecordInfo;
import cn.edu.tongji.healper.repository.PsyScaleRepository;
import cn.edu.tongji.healper.repository.ScaleRecordRepository;
import cn.edu.tongji.healper.service.ScaleService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScaleServiceImpl implements ScaleService {
    @Resource
    private ScaleRecordRepository scaleRecordRepository;

    @Resource
    private PsyScaleRepository psychologyScaleRepository;

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
    public List<ScaleInfo> findBasicScales(Integer page, Integer size) {
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
            for (ScaleRecordInfo record : records) {
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

    @Override
    public Map findLabelsWithClient(Integer clientId) {
        List<String> records = scaleRecordRepository.findClientLabelsPriority(clientId);
        HashMap<String, Integer> factorValues = new HashMap<>();
        for (Object obj: JSONObject.parseArray(records.get(0))) {
            // obj: {"factor": "躯体化", "value": 3}
            JSONObject map = (JSONObject) obj;
            String factor = map.get("factor").toString();
            factorValues.put(factor, 0);
        }
        for (String json: records) {
            for (Object obj: JSONObject.parseArray(json)) {
                // obj: {"factor": "躯体化", "value": 3}
                JSONObject map = (JSONObject) obj;
                String factor = map.get("factor").toString();
                int value = Integer.parseInt(map.get("value").toString());
                factorValues.put(factor, Integer.parseInt(factorValues.get(factor).toString()) + value);
            }
        }

        return factorValues;
    }
}
