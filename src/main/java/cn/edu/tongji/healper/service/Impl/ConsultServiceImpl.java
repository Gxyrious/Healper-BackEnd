package cn.edu.tongji.healper.service.Impl;

import cn.edu.tongji.healper.entity.ConsultHistoryEntity;
import cn.edu.tongji.healper.repository.ConsultHistoryRepository;
import cn.edu.tongji.healper.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultServiceImpl implements ConsultService {

    @Autowired
    private ConsultHistoryRepository historyRepository;

    @Override
    public Boolean startConsultation(Integer orderId, Integer startTime) {
        ConsultHistoryEntity order = historyRepository.getConsultHistoryEntityById(orderId);
        order.setStartTime(startTime);
        try {
            historyRepository.save(order);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean endConsultation(Integer orderId, Integer endTime) {
        ConsultHistoryEntity order = historyRepository.getConsultHistoryEntityById(orderId);
        order.setEndTime(endTime);
        try {
            historyRepository.save(order);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
