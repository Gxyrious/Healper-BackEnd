package cn.edu.tongji.healper.outdto;

import cn.edu.tongji.healper.entity.ScaleRecordEntity;
import lombok.Data;

import java.util.List;

@Data
public class ScaleRecordDto {
    List<ScaleRecordEntity> scaleRecordEntities;
}
