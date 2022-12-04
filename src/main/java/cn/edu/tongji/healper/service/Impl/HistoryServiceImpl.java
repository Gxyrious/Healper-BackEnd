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
    public Boolean addConsultHistory(Integer clientId, Integer consultantId, Integer expense) {
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
        Pageable pageRequest = PageRequest.of(page - 1, size);
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
    public Integer getArchiveNumByClientId(Integer clientId) {
        return historyRepository.getArchiveNumByClientId(clientId);
    }

    @Override
    public List<Archive> findArchiveByClientId(Integer clientId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return historyRepository.getArchivesByClientId(clientId, pageable);
    }

    @Override
    public Boolean endConsultation(Integer orderId, Integer endTime) {
        return null;
    }

    @Override
    public Integer getOrderNumByClientId(Integer clientId) {
        return historyRepository.findOrderNumByClientId(clientId);
    }

    @Override
    public Integer getRecordNumByClientId(Integer clientId) {
        return historyRepository.findConsultRecordNumByClientId(clientId);
    }

    @Override
    public List<ConsultHistoryEntity> findRecordsByClientId(Integer clientId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return historyRepository.findConsultHistoryEntitiesByClientId(clientId, pageable);
    }
}
