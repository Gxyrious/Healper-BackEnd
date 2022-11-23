package cn.edu.tongji.healper.service.Impl;

import cn.edu.tongji.healper.model.ClientEntity;
import cn.edu.tongji.healper.repository.ClientRepository;
import cn.edu.tongji.healper.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private ClientRepository clientRepository;

    @Override
    public ClientEntity findUserEntityByUserPhone(String userPhone) {
        return clientRepository.findClientEntityByUserphone(userPhone);
    }
}
