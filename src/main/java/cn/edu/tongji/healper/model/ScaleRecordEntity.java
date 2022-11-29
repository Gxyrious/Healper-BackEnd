package cn.edu.tongji.healper.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "scale_record", schema = "healper", catalog = "")
public class ScaleRecordEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "client_id")
    private int clientId;
    @Basic
    @Column(name = "scale_id")
    private int scaleId;
    @Basic
    @Column(name = "end_time")
    private int endTime;
    @Basic
    @Column(name = "is_hidden")
    private byte isHidden;

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

    public int getScaleId() {
        return scaleId;
    }

    public void setScaleId(int scaleId) {
        this.scaleId = scaleId;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public byte getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(byte isHidden) {
        this.isHidden = isHidden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScaleRecordEntity that = (ScaleRecordEntity) o;
        return id == that.id && clientId == that.clientId && scaleId == that.scaleId && endTime == that.endTime && isHidden == that.isHidden;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, scaleId, endTime, isHidden);
    }
}
