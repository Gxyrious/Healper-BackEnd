package cn.edu.tongji.healper.service;

import cn.edu.tongji.healper.po.ConsultOrder;

import java.util.List;

public interface HistoryService {

    boolean addConsultHistory(int clientId, int consultantId, int expense);

    List<ConsultOrder> findConsultOrdersByClientId(Integer clientId, Integer page, Integer size);

    String findQrCodeByHistoryId(Integer historyId);

    Boolean updateHistoryStatusById(Integer historyId, char status);
}
