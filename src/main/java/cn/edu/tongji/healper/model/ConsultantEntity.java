package cn.edu.tongji.healper.model;

import javax.persistence.*;

@Entity
@Table(name = "consultant", schema = "healper", catalog = "")
public class ConsultantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "userphone", nullable = false, length = 11)
    private String userphone;
    @Basic
    @Column(name = "realname", nullable = false, length = 16)
    private String realname;
    @Basic
    @Column(name = "password", nullable = false, length = 32)
    private String password;
    @Basic
    @Column(name = "qr_code_link", nullable = true, length = 128)
    private String qrCodeLink;
    @Basic
    @Column(name = "sex", nullable = true, length = 1)
    private String sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsultantEntity that = (ConsultantEntity) o;

        if (id != that.id) return false;
        if (userphone != null ? !userphone.equals(that.userphone) : that.userphone != null) return false;
        if (realname != null ? !realname.equals(that.realname) : that.realname != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (qrCodeLink != null ? !qrCodeLink.equals(that.qrCodeLink) : that.qrCodeLink != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userphone != null ? userphone.hashCode() : 0);
        result = 31 * result + (realname != null ? realname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (qrCodeLink != null ? qrCodeLink.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        return result;
    }
}
