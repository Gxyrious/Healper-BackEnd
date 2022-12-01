package cn.edu.tongji.healper.service;


public interface ConsultService {
    Boolean startConsultation(Integer orderId, Integer startTime);

    Boolean endConsultation(Integer orderId, Integer endTime);
}
