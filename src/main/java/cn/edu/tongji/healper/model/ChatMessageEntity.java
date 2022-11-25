package cn.edu.tongji.healper.model;

import javax.persistence.*;

@Entity
@Table(name = "chat_message", schema = "healper", catalog = "")
public class ChatMessageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "client_id", nullable = false)
    private int clientId;
    @Basic
    @Column(name = "consultant_id", nullable = false)
    private int consultantId;
    @Basic
    @Column(name = "create_time", nullable = false)
    private int createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(int consultantId) {
        this.consultantId = consultantId;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatMessageEntity that = (ChatMessageEntity) o;

        if (id != that.id) return false;
        if (clientId != that.clientId) return false;
        if (consultantId != that.consultantId) return false;
        if (createTime != that.createTime) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + clientId;
        result = 31 * result + consultantId;
        result = 31 * result + createTime;
        return result;
    }
}
