package cn.edu.buct.zxxe.beijingguider;

import org.litepal.crud.DataSupport;

import java.lang.reflect.Field;

/**
 * Created by 84978 on 2018/1/4.
 */

public class Route extends DataSupport {
    private int id;
    private String name;
    private int time;
    private String position;
    private int imageId;
    private String[] lines;
    private String line0;
    private String line1;
    private String line2;
    private String line3;
    private String line4;
    private int lineLength;
    private String describe;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getLine0() {
        return line0;
    }

    public void setLine0(String line0) {
        this.line0 = line0;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public String getLine4() {
        return line4;
    }

    public void setLine4(String line4) {
        this.line4 = line4;
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public void setLines(String[] lines) {
        setLineLength(lines.length);
        Class c = Route.class;
        Field[] fields = new Field[lineLength];
        try {
            for (int i = 0; i < lineLength; i++) {
                fields[i] = c.getDeclaredField("line" + i);
                fields[i].setAccessible(true);
                fields[i].set(this, lines[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getLines() {
        if (lines == null) {
            lines = new String[lineLength];
            Class c = Route.class;
            Field[] fields = new Field[lineLength];
            try {
                for (int i = 0; i < lineLength; i++) {
                    fields[i] = c.getDeclaredField("line" + i);
                    fields[i].setAccessible(true);
                    lines[i] = (String) fields[i].get(this);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lines;
    }
}
