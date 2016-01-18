package com.bju.cps450;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;



/*
    String file = parser.getRemainingArgs()[0];
    PushbackReader reader = new PushbackReader(new InputStreamReader(new FileInputStream(file)));


    OodleLexer lexer = new OodleLexer(reader, file, options.getDs());

    Token token = lexer.next();

 */

/**
 * Created by daniel on 1/16/16.
 */
public class SuperFile {

    private PushbackReader baseReader;


    public ArrayList<SubFile> SubFiles;

    public SuperFile(String[] fileNames) throws IOException{
        Integer start;
        Integer current = 1;
        SubFiles = new ArrayList<>();
        BufferedWriter out;
        FileWriter fstream = new FileWriter("combined.txt", false);
        out = new BufferedWriter(fstream);
        try
        {

            for (Integer i = 0; i < fileNames.length; ++i) {

                FileReader rstream = new FileReader(fileNames[i]);
                BufferedReader br = new BufferedReader(rstream);
                String line = br.readLine();
                start = current;
                while (line != null) {
                    out.write(line + "\n");
                    current++;
                    line = br.readLine();
                }
                br.close();
                SubFiles.add(new SubFile(fileNames[i], start, current-1));
            }
            // last file does not need to be decremented by 1
            SubFiles.get(SubFiles.size()-1).EndLine++;
        }
        catch (IOException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        finally
        {
            if(out != null) {
                out.close();
            }
        }

    }

    public PushbackReader getReader(){
        try {
            this.baseReader = new PushbackReader(new FileReader("combined.txt"));
            return baseReader;
        }catch (FileNotFoundException e){
            return null;
        }
    }

    public SubFile getFileByLine(Integer line){
        Integer s = this.SubFiles.size();
        for (Integer i = 0; i < s; ++i){
            if (this.SubFiles.get(i).InRange(line)){
                return this.SubFiles.get(i);
            }
        }
        return null;
    }

}
