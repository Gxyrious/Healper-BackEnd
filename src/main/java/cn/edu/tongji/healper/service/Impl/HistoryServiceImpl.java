package cn.edu.tongji.healper.service.impl;

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
    public Integer addConsultHistory(Integer clientId, Integer consultantId, Integer expense) {
        ConsultHistoryEntity history = new ConsultHistoryEntity();
        history.setClientId(clientId);
        history.setConsultantId(consultantId);
        history.setExpense(expense);
        history.setStatus("p"); // 状态：待付款
        ConsultHistoryEntity consultHistoryEntity = historyRepository.save(history);
        return consultHistoryEntity.getId();
    }

    @Override
    public List<ConsultOrder> findConsultOrdersByClientId(Integer clientId, Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page - 1, size);
        return historyRepository.findConsultOrderByClientId(clientId, pageRequest);
    }

    @Override
    public List<ConsultOrder> findConsultOrdersByConsultantId(Integer constantId, Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page - 1, size);
        return historyRepository.findConsultOrderByConsultantId(constantId, pageRequest);
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
    public Integer findArchiveNumByClientId(Integer clientId) {
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
    public Integer getOrderNumByConsultantId(Integer consultantId) {
        return historyRepository.findOrderNumByConsultantId(consultantId);
    }

    @Override
    public Integer getRecordNumByClientId(Integer clientId) {
        return historyRepository.findConsultRecordNumByClientId(clientId);
    }

    @Override
    public List<ConsultOrder> findWaitingOrdersByClientId(Integer clientId) {
        return historyRepository.findWaitingConsultOrders(clientId);
    }

    @Override
    public void writeClientArchive(Integer historyId, String adviceURL, String summaryURL) {
        ConsultHistoryEntity historyEntity = historyRepository.findById(historyId).get();
        historyEntity.setAdvice(adviceURL);
        historyEntity.setSummary(summaryURL);
        historyRepository.save(historyEntity);
    }

    @Override
    public void deleteOldWaitingOrdersByIds(List<Integer> ids) {
        historyRepository.deleteAllById(ids);
    }

    @Override
    public List<ConsultHistoryEntity> findRecordsByClientId(Integer clientId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return historyRepository.findConsultHistoryEntitiesByClientId(clientId, pageable);
    }
}
