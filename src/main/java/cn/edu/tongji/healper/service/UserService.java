package cn.edu.tongji.healper.service;

import cn.edu.tongji.healper.model.ClientEntity;

public interface UserService {

    ClientEntity findUserEntityByUserPhone(String userNameOrPhone);
}
