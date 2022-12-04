package cn.edu.tongji.healper.po;

import lombok.Data;

@Data
public class ScaleRecordInfo {
    Integer scaleRecordId;
    Long endTime;
    Byte isHidden;
    Integer scaleId;
    String record;
    String scaleName;

    public ScaleRecordInfo(Integer scaleRecordId, Long endTime, Byte isHidden, Integer scaleId, String record, String scaleName) {
        this.scaleRecordId = scaleRecordId;
        this.endTime = endTime;
        this.isHidden = isHidden;
        this.scaleId = scaleId;
        this.record = record;
        this.scaleName = scaleName;
    }
}
