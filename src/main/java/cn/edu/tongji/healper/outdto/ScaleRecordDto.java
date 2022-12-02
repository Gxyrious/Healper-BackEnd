package cn.edu.tongji.healper.outdto;

import cn.edu.tongji.healper.entity.ScaleRecordEntity;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.util.List;

@Data
public class ScaleRecordDto {
    Integer endTime;
    Byte isHidden;
    Integer scaleId;
    Double resultSomatization;
    Double resultForce;
    Double resultSensitive;
    Double resultDepress;
    Double resultAnxious;
    Double resultHostile;
    Double resultTerror;
    Double resultParanoia;
    Double resultPsychopathic;
    String scaleName;

    public ScaleRecordDto(Integer endTime,
                          Byte isHidden, Integer scaleId, Double resultSomatization,
                          Double resultForce, Double resultSensitive, Double resultDepress,
                          Double resultAnxious, Double resultHostile, Double resultTerror,
                          Double resultParanoia, Double resultPsychopathic, String scaleName){
        this.endTime = endTime;
        this.isHidden = isHidden;
        this.scaleId = scaleId;
        this.resultSomatization = resultSomatization;
        this.resultForce = resultForce;
        this.resultSensitive = resultSensitive;
        this.resultDepress = resultDepress;
        this.resultAnxious = resultAnxious;
        this.resultHostile = resultHostile;
        this.resultTerror = resultTerror;
        this.resultParanoia = resultParanoia;
        this.resultPsychopathic = resultPsychopathic;
        this.scaleName = scaleName;
    }
}
