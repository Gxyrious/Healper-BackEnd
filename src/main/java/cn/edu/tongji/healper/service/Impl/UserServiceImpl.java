package cn.edu.tongji.healper.service.Impl;

import cn.edu.tongji.healper.entity.ClientEntity;
import cn.edu.tongji.healper.entity.ConsultantEntity;
import cn.edu.tongji.healper.entity.User;
import cn.edu.tongji.healper.repository.ClientRepository;
import cn.edu.tongji.healper.repository.ConsultantRepository;
import cn.edu.tongji.healper.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static cn.edu.tongji.healper.util.MD5Utils.stringToMD5;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private ClientRepository clientRepository;

    @Resource
    private ConsultantRepository consultantRepository;

    @Override
    public ClientEntity findClientEntityByUserPhone(String userPhone) {
        return clientRepository.findClientEntityByUserphone(userPhone);
    }

    @Override
    public ConsultantEntity findConsultantEntityByUserPhone(String userPhone) {
        return consultantRepository.findConsultantEntityByUserphone(userPhone);
    }

    @Override
    public User findUserByPhone(String phone) {
        // 先找Client
        ClientEntity client = findClientEntityByUserPhone(phone);
        if (client == null) {
            // 再找Consultant
            return findConsultantEntityByUserPhone(phone);
        } else {
            return client;
        }
    }


    @Override
    public ClientEntity addClientInfo(String nickname, String password, String userPhone, String sex) {
        ClientEntity newClient = new ClientEntity();
        newClient.setNickname(nickname);
        newClient.setPassword(stringToMD5(password));//密码进行md5加密
        newClient.setSex(sex);
        newClient.setUserphone(userPhone);
        return clientRepository.saveAndFlush(newClient);
    }

    @Override
    public ClientEntity updateClientInfo(ClientEntity client) {
        return clientRepository.save(client);
    }
}
