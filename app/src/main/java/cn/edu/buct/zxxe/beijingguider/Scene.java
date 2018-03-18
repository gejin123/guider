package cn.edu.buct.zxxe.beijingguider;

import org.litepal.crud.DataSupport;

/**
 * Created by 84978 on 2017/12/31.
 */

public class Scene extends DataSupport {
    private int id;
    private String name;
    private String englishName;
    private String describeComplete;
    private String describeSimple;
    private String geography;
    private int imageId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getDescribeSimple() {
        return describeSimple;
    }

    public void setDescribeSimple(String describeSimple) {
        this.describeSimple = describeSimple;
    }

    public String getDescribeComplete() {
        return describeComplete;
    }

    public void setDescribeComplete(String describeComplete) {
        this.describeComplete = describeComplete;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getGeography() {
        return geography;
    }

    public void setGeography(String geography) {
        this.geography = geography;
    }
}
