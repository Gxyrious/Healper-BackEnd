package cn.edu.tongji.healper.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "chat_message", schema = "healper", catalog = "")
public class ChatMessageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "client_id")
    private int clientId;
    @Basic
    @Column(name = "consultant_id")
    private int consultantId;
    @Basic
    @Column(name = "create_time")
    private int createTime;
    @Basic
    @Column(name = "content")
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessageEntity that = (ChatMessageEntity) o;
        return id == that.id && clientId == that.clientId && consultantId == that.consultantId && createTime == that.createTime && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, consultantId, createTime, content);
    }
}
