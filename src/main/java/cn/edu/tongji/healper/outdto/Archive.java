package cn.edu.tongji.healper.outdto;

import lombok.Data;

@Data
public class Archive {
    Integer consultantId;
    Integer endTime;
    Integer expense;
    Integer startTime;
    String advice;
    String summary;
    String consultantRealName;

    public Archive(Integer consultantId, Integer endTime, Integer expense,
                   Integer startTime, String advice,
                   String summary, String consultantRealName) {
        this.consultantId=consultantId;
        this.endTime=endTime;
        this.expense=expense;
        this.startTime=startTime;
        this.advice=advice;
        this.summary=summary;
        this.consultantRealName=consultantRealName;
    }
}
