package cn.edu.tongji.healper.indto;

import cn.edu.tongji.healper.outdto.UserType;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class UserInDto {
    int id;
    UserType userType;
}
