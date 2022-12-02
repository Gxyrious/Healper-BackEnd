package cn.edu.tongji.healper.indto;

import lombok.Data;

@Data
public class UpdatePasswdInDto {

    Integer id;

    String oldPasswd;

    String newPasswd;
}
