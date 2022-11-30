package cn.edu.tongji.healper.service;

import cn.edu.tongji.healper.entity.ClientEntity;
import cn.edu.tongji.healper.entity.ConsultantEntity;

public interface UserService {

    //client相关接口
    ClientEntity findClientEntityByUserPhone(String userPhone);

    boolean addClientInfo(String nickname, String password, String userPhone, String sex);

    ClientEntity updateClientInfo(ClientEntity client);

    //consultant相关接口
    ConsultantEntity findConsultantEntityByUserPhone(String userPhone);
}
