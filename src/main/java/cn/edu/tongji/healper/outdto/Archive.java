package cn.edu.tongji.healper.outdto;

import lombok.Data;

@Data
public class Archive {
    Integer id;
    Integer consultantId;
    Long endTime;
    Integer expense;
    Long startTime;
    String advice;
    String summary;
    String consultantRealName;

    public Archive(Integer id, Integer consultantId, Long endTime, Integer expense, Long startTime, String advice, String summary, String consultantRealName) {
        this.id = id;
        this.consultantId = consultantId;
        this.endTime = endTime;
        this.expense = expense;
        this.startTime = startTime;
        this.advice = advice;
        this.summary = summary;
        this.consultantRealName = consultantRealName;
    }
}
