package cn.edu.tongji.healper.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "consultant", schema = "healper", catalog = "")
public class ConsultantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "qr_code_link")
    private String qrCodeLink;
    @Basic
    @Column(name = "realname")
    private String realname;
    @Basic
    @Column(name = "sex")
    private String sex;
    @Basic
    @Column(name = "userphone")
    private String userphone;
    @Basic
    @Column(name = "age")
    private Integer age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQrCodeLink() {
        return qrCodeLink;
    }

    public void setQrCodeLink(String qrCodeLink) {
        this.qrCodeLink = qrCodeLink;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultantEntity that = (ConsultantEntity) o;
        return id == that.id && Objects.equals(password, that.password) && Objects.equals(qrCodeLink, that.qrCodeLink) && Objects.equals(realname, that.realname) && Objects.equals(sex, that.sex) && Objects.equals(userphone, that.userphone) && Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, qrCodeLink, realname, sex, userphone, age);
    }
}
