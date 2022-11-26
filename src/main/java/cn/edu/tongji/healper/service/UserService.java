package cn.edu.tongji.healper.service;

import cn.edu.tongji.healper.model.ClientEntity;
import cn.edu.tongji.healper.model.ConsultantEntity;

public interface UserService {

    ClientEntity findClientEntityByUserPhone(String userPhone);

    ConsultantEntity findConsultantEntityByUserPhone(String userPhone);
}
