package cn.edu.tongji.healper.po;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class ConsultOrder implements Serializable {

    private int id;

    private Long startTime;

    private Long endTime;

    private int consultantId;

    private String realname;

    private int expense;

    private String status;

    public ConsultOrder(int id,
                        Long startTime, Long endTime,
                        int consultantId, String realname,
                        int expense, String status) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.consultantId = consultantId;
        this.realname = realname;
        this.expense = expense;
        this.status = status;
    }
}
