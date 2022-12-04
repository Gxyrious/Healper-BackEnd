package cn.edu.tongji.healper.entity;

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
    @Column(name = "end_time", nullable = false)
    private long endTime;
    @Basic
    @Column(name = "is_hidden", nullable = false)
    private byte isHidden;
    @Basic
    @Column(name = "scale_id", nullable = false)
    private int scaleId;
    @Basic
    @Column(name = "record", nullable = false, length = -1)
    private String record;

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

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public byte getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(byte isHidden) {
        this.isHidden = isHidden;
    }

    public int getScaleId() {
        return scaleId;
    }

    public void setScaleId(int scaleId) {
        this.scaleId = scaleId;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScaleRecordEntity that = (ScaleRecordEntity) o;

        if (id != that.id) return false;
        if (clientId != that.clientId) return false;
        if (endTime != that.endTime) return false;
        if (isHidden != that.isHidden) return false;
        if (scaleId != that.scaleId) return false;
        if (record != null ? !record.equals(that.record) : that.record != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + clientId;
        result = 31 * result + (int) (endTime ^ (endTime >>> 32));
        result = 31 * result + (int) isHidden;
        result = 31 * result + scaleId;
        result = 31 * result + (record != null ? record.hashCode() : 0);
        return result;
    }
}
