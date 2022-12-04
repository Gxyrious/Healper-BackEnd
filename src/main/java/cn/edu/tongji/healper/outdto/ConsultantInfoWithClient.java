package cn.edu.tongji.healper.outdto;

import cn.edu.tongji.healper.po.ConsultantInfo;
import lombok.Data;

@Data
public class ConsultantInfoWithClient {

    ConsultantInfo info;

    Boolean isAppointed;

}
