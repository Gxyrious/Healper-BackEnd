package cn.edu.tongji.healper.service;

import cn.edu.tongji.healper.entity.ConsultHistoryEntity;
import cn.edu.tongji.healper.outdto.Archive;
import cn.edu.tongji.healper.po.ConsultOrder;

import java.util.List;

public interface HistoryService {

    Boolean addConsultHistory(Integer clientId, Integer consultantId, Integer expense);


    String findQrCodeByHistoryId(Integer historyId);

    Boolean updateHistoryStatusById(Integer historyId, String status);

    Integer getArchiveNumByClientId(Integer clientId);

    List<ConsultOrder> findConsultOrdersByClientId(Integer clientId, Integer page, Integer size);

    List<Archive> findArchiveByClientId(Integer clientId, Integer page, Integer size);

    List<ConsultHistoryEntity> findRecordsByClientId(Integer clientId, Integer page, Integer size);

    Boolean endConsultation(Integer orderId, Integer endTime);

    Integer getOrderNumByClientId(Integer clientId);

    Integer getRecordNumByClientId(Integer clientId);

}
