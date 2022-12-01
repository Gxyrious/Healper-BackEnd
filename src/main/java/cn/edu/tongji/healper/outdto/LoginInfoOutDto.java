package cn.edu.tongji.healper.outdto;

import cn.edu.tongji.healper.entity.User;
import lombok.Data;

@Data
public class LoginInfoOutDto {
    User user;

    UserType userType;
}
