package cn.edu.tongji.healper.indto;

import cn.edu.tongji.healper.outdto.UserType;
import lombok.Data;

@Data
public class UploadImageInDto {

    Integer id;

    String base64;

    UserType userType;
}
