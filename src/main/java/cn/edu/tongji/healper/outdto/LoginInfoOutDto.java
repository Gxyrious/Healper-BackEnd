package cn.edu.tongji.healper.outdto;

import cn.edu.tongji.healper.indto.LoginInfoInDto;
import cn.edu.tongji.healper.model.ClientEntity;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;

@Data
public class LoginInfoOutDto {
    Object user;

    UserType userType;
}
