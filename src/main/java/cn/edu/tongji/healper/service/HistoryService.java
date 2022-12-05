package cn.edu.tongji.healper.service;

import cn.edu.tongji.healper.entity.ConsultHistoryEntity;
import cn.edu.tongji.healper.outdto.Archive;
import cn.edu.tongji.healper.po.ConsultOrder;

import java.util.List;

public interface HistoryService {

    Integer addConsultHistory(Integer clientId, Integer consultantId, Integer expense);

    String findQrCodeByHistoryId(Integer historyId);

    Boolean updateHistoryStatusById(Integer historyId, String status);

    Integer findArchiveNumByClientId(Integer clientId);

    List<ConsultOrder> findConsultOrdersByClientId(Integer clientId, Integer page, Integer size);

    List<ConsultOrder> findConsultOrdersByConsultantId(Integer constantId, Integer page, Integer size);

    List<Archive> findArchiveByClientId(Integer clientId, Integer page, Integer size);

    void deleteOldWaitingOrdersByIds(List<Integer> ids);

    List<ConsultHistoryEntity> findRecordsByClientId(Integer clientId, Integer page, Integer size);

    Boolean endConsultation(Integer orderId, Integer endTime);

    Integer getOrderNumByClientId(Integer clientId);

    Integer getOrderNumByConsultantId(Integer consultantId);

    Integer getRecordNumByClientId(Integer clientId);

    List<ConsultOrder> findWaitingOrdersByClientId(Integer clientId);

}
