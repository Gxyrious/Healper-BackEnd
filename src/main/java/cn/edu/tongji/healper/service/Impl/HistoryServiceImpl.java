package cn.edu.tongji.healper.service.Impl;

import cn.edu.tongji.healper.entity.ConsultHistoryEntity;
import cn.edu.tongji.healper.outdto.Archive;
import cn.edu.tongji.healper.po.ConsultOrder;
import cn.edu.tongji.healper.repository.ConsultHistoryRepository;
import cn.edu.tongji.healper.service.HistoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        history.setStatus("w"); // 状态：等待中
        historyRepository.save(history);
        return true;
    }

    @Override
    public List<ConsultOrder> findConsultOrdersByClientId(Integer clientId, Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size);
        return historyRepository.findConsultOrder(clientId, pageRequest);
    }

    @Override
    public String findQrCodeByHistoryId(Integer historyId) {
        return historyRepository.findQrCodeByHistoryId(historyId);
    }

    @Override
    public Boolean updateHistoryStatusById(Integer historyId, String status) {
        Integer updatedNumber = historyRepository.updateHistoryStatusById(historyId, String.valueOf(status));
        return updatedNumber.equals(1);
    }

    @Override
    public List<Archive> getAllArchive(Integer clientId) {
        return historyRepository.getAllArchive(clientId);
    }

    @Override
    public List<Archive> getSomeArchive(Integer clientId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return historyRepository.getSomeArchive(clientId, pageable);
    }

    @Override
    public Boolean endConsultation(Integer orderId, Integer endTime) {
        return null;
    }
}
