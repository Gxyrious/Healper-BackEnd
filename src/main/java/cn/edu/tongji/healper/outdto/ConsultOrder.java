package cn.edu.tongji.healper.outdto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ConsultOrder implements Serializable {

    private int id;

    private Long startTime;

    private Long endTime;

    private int consultantId;


    private int clientId;

    private String realname;

    private int expense;

    private String status;


    private String clientSex;


    private Integer clientAge;


    private String consultantLabel;


    private Integer consultantAge;


    private String consultantProfile;

    public ConsultOrder(int id, Long startTime, Long endTime,
                        int consultantId, int clientId, String realname,
                        int expense, String status, String clientSex, Integer clientAge) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.consultantId = consultantId;
        this.clientId = clientId;
        this.realname = realname;
        this.expense = expense;
        this.status = status;
        this.clientSex = clientSex;
        this.clientAge = clientAge;
    }
}
