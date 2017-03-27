package com.whieenz.storagemanage.base;

/**
 * Created by heziwen on 2017/3/23.
 */

public class CkInfoVO {
    private String ckmc;
    private String size;
    private String ckbm;
    private String cgy;
    private String address;
    private String tag;

    public CkInfoVO(){

    }
    public CkInfoVO(String ckbm,String ckmc, String size, String cgy, String address) {
        this.ckmc = ckmc;
        this.size = size;
        this.ckbm = ckbm;
        this.cgy = cgy;
        this.address = address;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCkmc() {
        return ckmc;
    }

    public void setCkmc(String ckmc) {
        this.ckmc = ckmc;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCkbm() {
        return ckbm;
    }

    public void setCkbm(String ckbm) {
        this.ckbm = ckbm;
    }

    public String getCgy() {
        return cgy;
    }

    public void setCgy(String cgy) {
        this.cgy = cgy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
