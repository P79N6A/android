package com.judian.goule.store.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/9/11.
 */
    @Entity
    public class NewsBean {

       @Id(autoincrement = true)
        private Long id;//@Id必须为Long


        private long identity;//身份唯一

        @Property(nameInDb = "info")
        private String info;//改变列名

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Property(nameInDb = "user_id")
        private String user_id;//改变列名

     @Property(nameInDb = "title")
        private String title;//改变列名

         @Property(nameInDb = "type")
        private String type;//改变列名   \

    public String getLook() {
        return look;
    }

    public void setLook(String look) {
        this.look = look;
    }

    @Property(nameInDb = "look")
        private String look;//改变列名   \


    @Override
    public String toString() {
        return "look="+look+"，title="+title+"，context="+context+"，id="+id+"，info="+info;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Property(nameInDb = "CONTEXT")
        private String context;//改变列名

    //  @Property(nameInDb = "NAME")
        private String name;//改变列名


        @NotNull
        private int age;//不能为空


        private String hobby;//不存储到数据库

        @Generated(hash = 790108337)
        public NewsBean(Long id, long identity, String info, String user_id, String title,
                String type, String look, String context, String name, int age, String hobby) {
            this.id = id;
            this.identity = identity;
            this.info = info;
            this.user_id = user_id;
            this.title = title;
            this.type = type;
            this.look = look;
            this.context = context;
            this.name = name;
            this.age = age;
            this.hobby = hobby;
        }

        @Generated(hash = 1662878226)
        public NewsBean() {
        }

        public Long getId() {
            return this.id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public long getIdentity() {
            return this.identity;
        }

        public void setIdentity(long identity) {
            this.identity = identity;
        }

        public String getInfo() {
            return this.info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return this.age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getHobby() {
            return this.hobby;
        }

        public void setHobby(String hobby) {
            this.hobby = hobby;
        }
    }


