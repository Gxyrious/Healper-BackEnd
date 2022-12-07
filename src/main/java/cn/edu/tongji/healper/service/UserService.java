package cn.edu.tongji.healper.service;

import cn.edu.tongji.healper.entity.ClientEntity;
import cn.edu.tongji.healper.entity.ConsultantEntity;
import cn.edu.tongji.healper.entity.User;
import cn.edu.tongji.healper.outdto.ConsultantInfoWithClient;
import cn.edu.tongji.healper.outdto.UserType;
import cn.edu.tongji.healper.po.ClientInfo;
import cn.edu.tongji.healper.po.ConsultantInfo;

import java.util.List;

public interface UserService {

    //公共接口------------------------------
    User findUserByPhone(String phone);

    void updateUserPasswd(Integer id, UserType userType, String password);


    //client相关接口------------------------------
    ClientEntity findClientEntityByUserPhone(String userPhone);

    ClientInfo findClientInfoById(Integer id);

    ClientEntity addClientInfo(String nickname, String password, String userPhone, String sex, Integer age);

    void updateClientInfo(ClientInfo client);

    void updateConsultantInfo(ConsultantInfo consultant);

    void updateConsultantQrCode(Integer id, String url);

    Boolean checkPasswdWithId(Integer id, UserType userType, String password);


    //consultant相关接口-----------------------------
    ConsultantEntity findConsultantEntityByUserPhone(String userPhone);

    ConsultantInfo findConsultantInfoById(Integer id);

    List<ConsultantInfo> findConsultantsByLabel(String label, Integer page, Integer size);

    List<ConsultantInfoWithClient> findConsultantsWithClient(Integer clientId, String label, Integer page, Integer size);




    //其它类相关接口------------------------------

}
