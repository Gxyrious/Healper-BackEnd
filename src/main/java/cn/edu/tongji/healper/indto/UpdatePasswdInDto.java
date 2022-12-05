package cn.edu.tongji.healper.indto;

import cn.edu.tongji.healper.outdto.UserType;
import lombok.Data;

@Data
public class UpdatePasswdInDto {

    Integer id;

    String oldPasswd;

    String newPasswd;

    UserType userType;
}
