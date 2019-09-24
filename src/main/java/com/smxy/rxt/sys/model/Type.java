package com.smxy.rxt.sys.model;

import javax.persistence.*;

@Table(name = "typeall")
public class Type {
    @Id
    @Column(name = "typeId")
    private Integer typeid;

    @Column(name = "typeName")
    private String typename;

    /**
     * @return typeId
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * @param typeid
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * @return typeName
     */
    public String getTypename() {
        return typename;
    }

    /**
     * @param typename
     */
    public void setTypename(String typename) {
        this.typename = typename;
    }
}