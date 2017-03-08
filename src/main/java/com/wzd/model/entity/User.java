package com.wzd.model.entity;

import java.util.Date;
import javax.persistence.*;

public class User {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * ����
     */
    private String name;

    /**
     * �Ա�
     */
    private Boolean sex;

    /**
     * ����
     */
    private Date birthday;

    /**
     * ��ȡID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * ����ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * ��ȡ����
     *
     * @return name - ����
     */
    public String getName() {
        return name;
    }

    /**
     * ��������
     *
     * @param name ����
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ��ȡ�Ա�
     *
     * @return sex - �Ա�
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * �����Ա�
     *
     * @param sex �Ա�
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /**
     * ��ȡ����
     *
     * @return birthday - ����
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * ��������
     *
     * @param birthday ����
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}