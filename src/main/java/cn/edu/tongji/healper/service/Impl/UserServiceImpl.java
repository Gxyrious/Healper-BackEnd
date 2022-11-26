package cn.edu.tongji.healper.service.Impl;

import cn.edu.tongji.healper.model.ClientEntity;
import cn.edu.tongji.healper.model.ConsultantEntity;
import cn.edu.tongji.healper.repository.ClientRepository;
import cn.edu.tongji.healper.repository.ConsultantRepository;
import cn.edu.tongji.healper.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
