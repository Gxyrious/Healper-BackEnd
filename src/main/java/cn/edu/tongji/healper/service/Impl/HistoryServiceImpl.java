package cn.edu.tongji.healper.service.Impl;

import cn.edu.tongji.healper.entity.ConsultHistoryEntity;
import cn.edu.tongji.healper.repository.ConsultHistoryRepository;
import cn.edu.tongji.healper.service.HistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Resource
    ConsultHistoryRepository historyRepository;
    @Override
    public boolean addConsultHistory(int clientId, int consultantId, int expense) {
        ConsultHistoryEntity history = new ConsultHistoryEntity();
        history.setClientId(clientId);
        history.setConsultantId(consultantId);
        history.setExpense(expense);
        history.setStatus("w");
        historyRepository.save(history);
        return true;
    }
}
