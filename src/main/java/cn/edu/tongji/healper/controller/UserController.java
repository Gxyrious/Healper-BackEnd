package cn.edu.tongji.healper.controller;

import cn.edu.tongji.healper.entity.User;
import cn.edu.tongji.healper.indto.LoginInfoInDto;
import cn.edu.tongji.healper.indto.RegisterInfoInDto;
import cn.edu.tongji.healper.entity.ClientEntity;
import cn.edu.tongji.healper.entity.ConsultantEntity;

import cn.edu.tongji.healper.indto.UpdatePasswdInDto;
import cn.edu.tongji.healper.indto.UploadImageInDto;
import cn.edu.tongji.healper.outdto.ConsultantInfoWithClient;
import cn.edu.tongji.healper.outdto.LoginInfoOutDto;
import cn.edu.tongji.healper.outdto.UserType;

import cn.edu.tongji.healper.po.ClientInfo;
import cn.edu.tongji.healper.po.ConsultantInfo;
import cn.edu.tongji.healper.service.UserService;
import cn.edu.tongji.healper.util.OSSUtils;
import cn.edu.tongji.healper.util.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static cn.edu.tongji.healper.util.MD5Utils.stringToMD5;

@RestController
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SMSUtils smsService;

    // 登录
    @PostMapping(value = "login")
    public ResponseEntity login(@RequestBody LoginInfoInDto loginInfoInDto) {
        User user = userService.findUserByPhone(loginInfoInDto.getUserPhone());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User doesn't exist!");
        } else {
            if (user.getPassword().equals(stringToMD5(loginInfoInDto.getUserPassword()))) {
                LoginInfoOutDto outDto = new LoginInfoOutDto();
                outDto.setUser(user);
                if (user.getClass() == ClientEntity.class) {
                    outDto.setUserType(UserType.client);
                } else if (user.getClass() == ConsultantEntity.class) {
                    outDto.setUserType(UserType.consultant);
                }
                return ResponseEntity.ok(outDto);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password error!");
            }
        }
    }

    @GetMapping(value = "info")
    public ResponseEntity getInfoByUserId(@RequestParam Integer id, @RequestParam UserType userType) {
        if (userType == UserType.client) {
            ClientInfo clientInfo = userService.findClientInfoById(id);
            if (clientInfo != null) {
                return ResponseEntity.ok(clientInfo);
            }
        } else if (userType == UserType.consultant) {
            ConsultantInfo consultantInfo = userService.findConsultantInfoById(id);
            if (consultantInfo != null) {
                return ResponseEntity.ok(consultantInfo);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Person does not exist");
    }


    //注册功能，判断手机号是否重复，并将密码md5加密后存储用户信息
    @PostMapping(value = "register")
    public ResponseEntity register(@RequestBody RegisterInfoInDto registerInfoInDto) {
        try {
            String userphone = registerInfoInDto.getUserPhone();
            User user = userService.findUserByPhone(userphone);
            if (user != null) {
                throw new RuntimeException("Phone already exists!");
            } else {
                if (smsService.checkCaptcha(userphone, registerInfoInDto.getCode())) {
                    ClientEntity newClient = userService.addClientInfo(
                            registerInfoInDto.getNickname(),
                            registerInfoInDto.getPassword(),
                            registerInfoInDto.getUserPhone(),
                            registerInfoInDto.getSex()
                    );
                    if (newClient != null) {
                        return ResponseEntity.ok(newClient.getId());
                    } else {
                        throw new RuntimeException("Failed to register!");
                    }
                } else {
                    throw new RuntimeException("Captcha incorrect!");
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(e);
        }
    }

    //修改用户信息
    @PutMapping(value = "info")
    public ResponseEntity updateClientBasicInfo(@RequestBody ClientInfo client) {
        try {
            userService.updateClientInfo(client);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("e");
        }
    }

    @PutMapping(value = "passwd")
    public ResponseEntity updateClientPasswd(@RequestBody UpdatePasswdInDto inDto) {
        try {
            if (userService.checkPasswdWithId(inDto.getId(), inDto.getUserType(), inDto.getOldPasswd())) {
                userService.updateUserPasswd(inDto.getId(), inDto.getUserType(), inDto.getNewPasswd());
                return ResponseEntity.ok("Password updated!");
            } else {
                return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body("Old password wrong!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    // 发送手机短信验证码
    @PostMapping("sendMsg")
    public ResponseEntity sendMsg(@RequestBody LoginInfoInDto loginInfoInDto) {
        //获取手机号
        try {
            String userphone = loginInfoInDto.getUserPhone();
            if (userService.findUserByPhone(userphone) != null) {
                throw new RuntimeException("Phone already exists!");
            } else {
                String code = smsService.sendMessage(userphone);
                return ResponseEntity.ok(code);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(e);
        }
    }

    @PostMapping("uploadProfile")
    public ResponseEntity uploadImage(@RequestBody UploadImageInDto inDto) {
        try {
            String imageBase64 = inDto.getBase64();

            // get image stream
            InputStream inputStream = OSSUtils.base64ToInputStream(imageBase64);

            // get image name
            String imageName = inDto.getUserType().toString()
                    + "-" + inDto.getId()
                    + "." + OSSUtils.getImageTypeFromBase64(imageBase64);

            // upload with stream and name
            String url = OSSUtils.uploadStream(inputStream, imageName);

            // update to database
            if (inDto.getUserType() == UserType.client) {
                ClientInfo clientInfo = userService.findClientInfoById(inDto.getId());
                clientInfo.setProfile(url);
                userService.updateClientInfo(clientInfo);
            } else if (inDto.getUserType() == UserType.consultant) {
                ConsultantInfo consultantInfo = userService.findConsultantInfoById(inDto.getId());
                consultantInfo.setProfile(url);
                userService.updateConsultantInfo(consultantInfo);
            } else {
                throw new RuntimeException("UserType error!");
            }

            return ResponseEntity.ok(url);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(e);
        }
    }

    @PostMapping(value = "uploadQrCode")
    public ResponseEntity uploadConsultantQrCode(@RequestBody UploadImageInDto inDto) {
        try {
            String imageBase64 = inDto.getBase64();

            InputStream inputStream = OSSUtils.base64ToInputStream(imageBase64);

            String imageName = "QrCode-" + inDto.getId() + "." + OSSUtils.getImageTypeFromBase64(imageBase64);

            String url = OSSUtils.uploadStream(inputStream, imageName);

            userService.updateConsultantQrCode(inDto.getId(), url);

            return ResponseEntity.ok(url);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(e);
        }
    }

    @GetMapping("consultants/label")
    public ResponseEntity findConsultantsWithLabel(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String label
    ) {
        try {
            List<ConsultantInfo> consultants = userService.findConsultantsByLabel(label, page, size);
            return ResponseEntity.ok(consultants);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No %s consultants found!".formatted(label));
        }
    }

    @GetMapping("consultants/client")
    public ResponseEntity findConsultantsWithClient(
            @RequestParam Integer clientId,
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String label
    ) {
        try {
//            String label = map.get("label");
            List<ConsultantInfoWithClient> consultants = userService.findConsultantsWithClient(clientId, label, page, size);
            return ResponseEntity.ok(consultants);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);

        }
    }

    @PutMapping("consultant/info")
    public ResponseEntity updateConsultantLabel(@RequestBody ConsultantInfo consultant) {
        try {
            // 需要修改一下来访者改信息的api
            userService.updateConsultantInfo(consultant);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PutMapping("consultant/label")
    public ResponseEntity updateConsultantLabel(@RequestBody String label) {
        // 怎么修改咨询师标签？
        try {

            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

}

