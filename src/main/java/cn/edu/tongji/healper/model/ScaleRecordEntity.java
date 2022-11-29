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
    @Column(name = "end_time")
    private int endTime;
    @Basic
    @Column(name = "is_hidden")
    private byte isHidden;
    @Basic
    @Column(name = "scale_id")
    private int scaleId;
    @Basic
    @Column(name = "result_somatization")
    private double resultSomatization;
    @Basic
    @Column(name = "result_force")
    private double resultForce;
    @Basic
    @Column(name = "result_sensitive")
    private double resultSensitive;
    @Basic
    @Column(name = "result_depress")
    private double resultDepress;
    @Basic
    @Column(name = "result_anxious")
    private double resultAnxious;
    @Basic
    @Column(name = "result_hostile")
    private double resultHostile;
    @Basic
    @Column(name = "result_terror")
    private double resultTerror;
    @Basic
    @Column(name = "result_paranoia")
    private double resultParanoia;
    @Basic
    @Column(name = "result_psychopathic")
    private double resultPsychopathic;

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

    public int getScaleId() {
        return scaleId;
    }

    public void setScaleId(int scaleId) {
        this.scaleId = scaleId;
    }

    public double getResultSomatization() {
        return resultSomatization;
    }

    public void setResultSomatization(double resultSomatization) {
        this.resultSomatization = resultSomatization;
    }

    public double getResultForce() {
        return resultForce;
    }

    public void setResultForce(double resultForce) {
        this.resultForce = resultForce;
    }

    public double getResultSensitive() {
        return resultSensitive;
    }

    public void setResultSensitive(double resultSensitive) {
        this.resultSensitive = resultSensitive;
    }

    public double getResultDepress() {
        return resultDepress;
    }

    public void setResultDepress(double resultDepress) {
        this.resultDepress = resultDepress;
    }

    public double getResultAnxious() {
        return resultAnxious;
    }

    public void setResultAnxious(double resultAnxious) {
        this.resultAnxious = resultAnxious;
    }

    public double getResultHostile() {
        return resultHostile;
    }

    public void setResultHostile(double resultHostile) {
        this.resultHostile = resultHostile;
    }

    public double getResultTerror() {
        return resultTerror;
    }

    public void setResultTerror(double resultTerror) {
        this.resultTerror = resultTerror;
    }

    public double getResultParanoia() {
        return resultParanoia;
    }

    public void setResultParanoia(double resultParanoia) {
        this.resultParanoia = resultParanoia;
    }

    public double getResultPsychopathic() {
        return resultPsychopathic;
    }

    public void setResultPsychopathic(double resultPsychopathic) {
        this.resultPsychopathic = resultPsychopathic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScaleRecordEntity that = (ScaleRecordEntity) o;
        return id == that.id && clientId == that.clientId && endTime == that.endTime && isHidden == that.isHidden && scaleId == that.scaleId && Double.compare(that.resultSomatization, resultSomatization) == 0 && Double.compare(that.resultForce, resultForce) == 0 && Double.compare(that.resultSensitive, resultSensitive) == 0 && Double.compare(that.resultDepress, resultDepress) == 0 && Double.compare(that.resultAnxious, resultAnxious) == 0 && Double.compare(that.resultHostile, resultHostile) == 0 && Double.compare(that.resultTerror, resultTerror) == 0 && Double.compare(that.resultParanoia, resultParanoia) == 0 && Double.compare(that.resultPsychopathic, resultPsychopathic) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, endTime, isHidden, scaleId, resultSomatization, resultForce, resultSensitive, resultDepress, resultAnxious, resultHostile, resultTerror, resultParanoia, resultPsychopathic);
    }
}
