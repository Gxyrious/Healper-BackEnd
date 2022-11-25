package cn.edu.tongji.healper.model;

import javax.persistence.*;

@Entity
@Table(name = "scale_record", schema = "healper", catalog = "")
public class ScaleRecordEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "client_id", nullable = false)
    private int clientId;
    @Basic
    @Column(name = "scale_id", nullable = false)
    private int scaleId;
    @Basic
    @Column(name = "end_time", nullable = false)
    private int endTime;
    @Basic
    @Column(name = "is_hidden", nullable = false)
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

        if (id != that.id) return false;
        if (clientId != that.clientId) return false;
        if (scaleId != that.scaleId) return false;
        if (endTime != that.endTime) return false;
        if (isHidden != that.isHidden) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + clientId;
        result = 31 * result + scaleId;
        result = 31 * result + endTime;
        result = 31 * result + (int) isHidden;
        return result;
    }
}
