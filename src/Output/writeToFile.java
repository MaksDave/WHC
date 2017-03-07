/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * maksdave@gmail.com
 */
public class writeToFile { //переписать файлрайтер,если нужно.
    File sfile = null;
    //Если требуемого файла не существует.
    public void setSfile(File sfile)
    {
        this.sfile = sfile;
    }
    public File getSfile()
    {
        return sfile;
    }
    /*
    private String fileName;
    public String getFileName()
    {
        return fileName;
    }
    public void setFileName(String fileName)
    {
        this.fileName=fileName;
    }
*/    
   private String text;
  public String getText()
    {
        return text;
    }
   public void setText(String text)
    {
        this.text=text+"\n";
    }
     public void writeToFile(File sfile, String text) throws FileNotFoundException, IOException {
    //Определяем файл
    
    
    FileWriter fw = new FileWriter(/*getFileName()*/getSfile(), true);
    BufferedWriter bufferWriter = new BufferedWriter(fw);
    PrintWriter out = new PrintWriter(bufferWriter);    
try {
        //Записываем текст у файл
        out.println(text);
        //out.append(text);
    } finally {
        //После чего мы должны закрыть файл
        //Иначе файл не запишется
        //out.close();
        
        out.close();
        bufferWriter.close();
        fw.close();
        
        
    }
}
    
}
