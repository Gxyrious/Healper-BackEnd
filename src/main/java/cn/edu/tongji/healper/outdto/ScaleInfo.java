package cn.edu.tongji.healper.outdto;

import lombok.Data;

@Data
public class ScaleInfo {

    Integer id;

    Integer quesNum;

    String name;

    String image;

    String summary;

    public ScaleInfo(Integer id, Integer quesNum,
                     String name, String image, String summary) {
        this.id = id;
        this.quesNum = quesNum;
        this.name = name;
        this.image = image;
        this.summary = summary;
    }
}
