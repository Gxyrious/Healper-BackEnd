package cn.edu.tongji.healper.indto;

import lombok.Data;

@Data
public class ConsultRecordInDto {

    String clientId;  //数据库中为Integer

    String consultantId;  //数据库中为Integer

    String expense;  //数据库中为Integer

    String status;
}
