package com.judian.goule.store.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/11/9.
 */
 @Entity
public class RoomBean {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "imgUrl")
    private String image;
    @Property(nameInDb = "content")
    private String content;
    @Generated(hash = 1482393)
    public RoomBean(Long id, String image, String content) {
        this.id = id;
        this.image = image;
        this.content = content;
    }
    @Generated(hash = 2135387174)
    public RoomBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RoomBean{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
