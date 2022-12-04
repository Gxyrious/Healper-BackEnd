package cn.edu.tongji.healper.service;


public interface ConsultService {
    Boolean startConsultation(Integer orderId, Long startTime);

    Boolean endConsultation(Integer orderId, Long endTime);
}
