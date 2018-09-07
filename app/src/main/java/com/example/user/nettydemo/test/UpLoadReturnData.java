package com.example.user.nettydemo.test;

public class UpLoadReturnData {


    /**
     * file : {"rid":"5b60044dd3ad8e791c7ce264"}
     */

    private FileBean file;

    public FileBean getFile() {
        return file;
    }

    public void setFile(FileBean file) {
        this.file = file;
    }

    public static class FileBean {
        /**
         * rid : 5b60044dd3ad8e791c7ce264
         */

        private String rid;

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }
    }
}
