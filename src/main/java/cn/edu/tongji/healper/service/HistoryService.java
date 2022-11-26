package cn.edu.tongji.healper.service;

public interface HistoryService {

    boolean addConsultHistory(int clientId, int consultantId, int expense);
}
