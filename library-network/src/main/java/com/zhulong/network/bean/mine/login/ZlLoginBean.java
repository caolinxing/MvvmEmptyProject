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
