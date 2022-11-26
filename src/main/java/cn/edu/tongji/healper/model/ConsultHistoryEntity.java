package cn.edu.tongji.healper.model;

import javax.persistence.*;

@Entity
@Table(name = "consult_history", schema = "healper", catalog = "")
public class ConsultHistoryEntity {
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
    @Column(name = "archive_id", nullable = true)
    private Integer archiveId;
    @Basic
    @Column(name = "status", nullable = false, length = 1)
    private String status;
    @Basic
    @Column(name = "start_time", nullable = true)
    private Integer startTime;
    @Basic
    @Column(name = "end_time", nullable = true)
    private Integer endTime;
    @Basic
    @Column(name = "expense", nullable = false)
    private int expense;

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

    public Integer getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(Integer archiveId) {
        this.archiveId = archiveId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsultHistoryEntity history = (ConsultHistoryEntity) o;

        if (id != history.id) return false;
        if (clientId != history.clientId) return false;
        if (consultantId != history.consultantId) return false;
        if (expense != history.expense) return false;
        if (archiveId != null ? !archiveId.equals(history.archiveId) : history.archiveId != null) return false;
        if (status != null ? !status.equals(history.status) : history.status != null) return false;
        if (startTime != null ? !startTime.equals(history.startTime) : history.startTime != null) return false;
        if (endTime != null ? !endTime.equals(history.endTime) : history.endTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + clientId;
        result = 31 * result + consultantId;
        result = 31 * result + (archiveId != null ? archiveId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + expense;
        return result;
    }
}
