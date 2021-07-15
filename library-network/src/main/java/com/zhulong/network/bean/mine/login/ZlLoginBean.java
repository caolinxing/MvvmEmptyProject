package com.zhulong.network.bean.mine.login;

import java.io.Serializable;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @since: clx
 * @date: 2021/7/12
 */
public class ZlLoginBean implements Serializable {

    /**
     * errNo : 0
     * result : {"uid":"12319288","username":"李杜2018","is_corp":"0","zlid":"3c118PE2TCXtEIqyeW52bcC6xNwykGpTExoHFpXpzS9WvNq-pqVfnkBhY37I80Pa","pcid":"12319288","returnpra":""}
     * exeTime : 0
     */

    private int errNo;
    private String msg;
    private ResultBean result;
    private int exeTime;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getErrNo() {
        return errNo;
    }

    public void setErrNo(int errNo) {
        this.errNo = errNo;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getExeTime() {
        return exeTime;
    }

    public void setExeTime(int exeTime) {
        this.exeTime = exeTime;
    }

    public static class ResultBean implements Serializable{
        /**
         * uid : 12319288
         * username : 李杜2018
         * is_corp : 0
         * zlid : 3c118PE2TCXtEIqyeW52bcC6xNwykGpTExoHFpXpzS9WvNq-pqVfnkBhY37I80Pa
         * pcid : 12319288
         * returnpra :
         */

        private String uid;
        private String username;
        private String is_corp;
        private String zlid;
        private String pcid;
        private String returnpra;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getIs_corp() {
            return is_corp;
        }

        public void setIs_corp(String is_corp) {
            this.is_corp = is_corp;
        }

        public String getZlid() {
            return zlid;
        }

        public void setZlid(String zlid) {
            this.zlid = zlid;
        }

        public String getPcid() {
            return pcid;
        }

        public void setPcid(String pcid) {
            this.pcid = pcid;
        }

        public String getReturnpra() {
            return returnpra;
        }

        public void setReturnpra(String returnpra) {
            this.returnpra = returnpra;
        }
    }
}
