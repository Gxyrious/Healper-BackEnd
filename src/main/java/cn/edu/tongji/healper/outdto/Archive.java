package cn.edu.tongji.healper.outdto;

import lombok.Data;

@Data
public class Archive {
    Integer consultantId;
    Long endTime;
    Integer expense;
    Long startTime;
    String advice;
    String summary;
    String consultantRealName;

    public Archive(Integer consultantId, Long endTime, Integer expense,
                   Long startTime, String advice,
                   String summary, String consultantRealName) {
        this.consultantId = consultantId;
        this.endTime = endTime;
        this.expense = expense;
        this.startTime = startTime;
        this.advice = advice;
        this.summary = summary;
        this.consultantRealName = consultantRealName;
    }
}
