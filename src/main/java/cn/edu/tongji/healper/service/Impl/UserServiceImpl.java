package cn.edu.tongji.healper.service.Impl;

import cn.edu.tongji.healper.entity.ClientEntity;
import cn.edu.tongji.healper.entity.ConsultantEntity;
import cn.edu.tongji.healper.entity.User;
import cn.edu.tongji.healper.po.ClientInfo;
import cn.edu.tongji.healper.po.ConsultantInfo;
import cn.edu.tongji.healper.repository.ClientRepository;
import cn.edu.tongji.healper.repository.ConsultantRepository;
import cn.edu.tongji.healper.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

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
    public List<ConsultantInfo> findConsultantsByLabel(String label, Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        return consultantRepository.findConsultantEntitiesByLabel(label, pageRequest);
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
    public Boolean updateClientInfo(ClientInfo client) {
        try {
            ClientEntity updatedClient = clientRepository.findById(client.getId()).get();
            updatedClient.setBasicInfo(client.getNickname(), client.getSex(), client.getAge());
            clientRepository.save(updatedClient);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean checkPasswdWithId(Integer id, String password) {
        String realPassword = clientRepository.findPasswordById(id);
        return realPassword != null && realPassword.equals(stringToMD5(password));
    }

    @Override
    public Boolean updateClientPasswd(Integer id, String password) {
        try {
            ClientEntity updatedClient = clientRepository.findById(id).get();
            updatedClient.setPassword(stringToMD5(password));
            clientRepository.save(updatedClient);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ClientInfo findClientInfoById(Integer id) {
        return clientRepository.findClientInfoById(id);
    }

    @Override
    public ConsultantInfo findConsultantInfoById(Integer id) {
        return consultantRepository.findConsultantInfoById(id);
    }

}
