package com.bju.cps450;

/**
 * Created by daniel on 1/16/16.
 */

/**
 * Holds the current status of the command line options
 */
public class Options {
    private Boolean ds;
    private String[] files;

    public void setDs(Boolean in){
        this.ds = in;
    }

    public Integer getDs(){
        if (this.ds){
            return 2;
        }else{
            return 1;
        }
    }

    public void setFiles(String[] files) {
        this.files = files;
    }

    public String[] getFileNames(){
        return this.files;
    }
}
