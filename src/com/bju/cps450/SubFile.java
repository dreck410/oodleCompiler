package com.bju.cps450;

/**
 * Created by daniel on 1/16/16.
 * Holds the start and end line for a file
 */
public class SubFile {
    public Integer StartLine;
    public Integer EndLine;
    public String Name;

    public SubFile(String Name, Integer Start, Integer End){
        this.StartLine = Start;
        this.EndLine = End;
        this.Name = Name;
    }

    /**
     * Checks to see if a line number is in the range
     * @param line
     * @return
     */
    public Boolean InRange(Integer line){
        return (this.StartLine <= line && line <= this.EndLine);
    }

    /**
     * does the math of getting an offset
     * @param line
     * @return
     */
    public Integer getOffset(Integer line){
        return (line - this.StartLine + 1);
    }
}
