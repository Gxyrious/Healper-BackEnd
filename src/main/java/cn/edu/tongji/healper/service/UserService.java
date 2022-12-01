package cn.edu.tongji.healper.service;

import cn.edu.tongji.healper.entity.ClientEntity;
import cn.edu.tongji.healper.entity.ConsultantEntity;
import cn.edu.tongji.healper.entity.User;

import java.util.List;

public interface UserService {

    //client相关接口
    ClientEntity findClientEntityByUserPhone(String userPhone);

    ClientEntity addClientInfo(String nickname, String password, String userPhone, String sex);

    ClientEntity updateClientInfo(ClientEntity client);

    //consultant相关接口
    ConsultantEntity findConsultantEntityByUserPhone(String userPhone);

    User findUserByPhone(String phone);

    List<ConsultantEntity> findConsultantsByLabel(String label, Integer page, Integer size);
}
