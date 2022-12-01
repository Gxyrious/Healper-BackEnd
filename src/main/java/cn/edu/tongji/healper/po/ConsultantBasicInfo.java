package cn.edu.tongji.healper.po;

import lombok.Data;

@Data
public class ConsultantBasicInfo {

    Integer id;

    String qrCodeLink;

    String realname;

    String sex;

    Integer age;

    Short expense;

    String label;

    String profile;

    public ConsultantBasicInfo(
            Integer id, String qrCodeLink,
            String realname, String sex,
            Integer age, Short expense,
            String label, String profile) {
        this.id = id;
        this.qrCodeLink = qrCodeLink;
        this.realname = realname;
        this.sex = sex;
        this.age = age;
        this.expense = expense;
        this.label = label;
        this.profile = profile;
    }
}
