/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Output.writeToFile;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Res.Variables;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * maksdave@gmail.com
 */
public class Gui extends JFrame {
   Variables forGui = new Variables();
   writeToFile WF = new writeToFile();
    private JButton Exit = new JButton("Exit"); //Объявляем кнопки
    private JButton Accept = new JButton("Where to save?");
    private JComboBox Box = new JComboBox(forGui.getWorkDuties()); //получаем
    private JButton SaveTo = new JButton ("Save to...");
    private JCheckBox onTop = new JCheckBox("on Top",false);
    public Gui() { //Задаем параметры конструктора Swing
        super("Work Hours Counter");
        //setUndecorated(true); //For minimizing in future...
        this.setBounds(200,100,480,75);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*this.setUndecorated(true);   
        this.getRootPane().setWindowDecorationStyle(JRootPane.WARNING_DIALOG); */ //minimized version of dialog
        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());
        container.add(Box);
        container.add(Accept);
        container.add(SaveTo);
        container.add(Exit);
        container.add(onTop);
        container.setBackground(Color.YELLOW);
        onTop.setSelected(false); //to action
        onTop.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent event) {
        JCheckBox onTop = (JCheckBox) event.getSource();
        if(onTop.isSelected()){setAlwaysOnTop(true);}else{setAlwaysOnTop(false);}}});
        Image icon = Toolkit.getDefaultToolkit().getImage(forGui.getPath()); 
        this.setIconImage(icon);
        Accept.setEnabled(false);
        Accept.addActionListener (new ActionListener () {
        public void actionPerformed(ActionEvent e) {
            if(WF.getSfile()==null)
            {
              Accept.setEnabled(false);
              Accept.setText("Where to save?");
            }
            else{
        String forDate = "yyyy/MM/dd HH:mm"; //Date template
        DateFormat dateFormat = new SimpleDateFormat(forDate);
        forGui.setDate(dateFormat.format(new Date()));
        if(!WF.getSfile().exists()) { 
            try {
                WF.getSfile().createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
}
        WF.setText(forGui.getFullInfo());
        forGui.setOutValue(Box.getSelectedItem().toString());
            try {
                WF.writeToFile(WF.getSfile()/*WF.getFileName()*/, forGui.getFullInfo());
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        }});
        SaveTo.addActionListener (new ActionListener () {
        public void actionPerformed(ActionEvent e) {
           
          //FileNameExtensionFilter sFilter = new FileNameExtensionFilter("*.TXT"); //Фильтр файлов
          final JFileChooser sFC = new JFileChooser();  
          //sFC.setFileFilter(sFilter);
          sFC.showSaveDialog(SaveTo);
          WF.setSfile(sFC.getSelectedFile());
          sFC.setDialogTitle("Specify a file to save...");   //Выбрать файл по умолчанию
           if(WF.getSfile()!=null)
           {
           Accept.setEnabled(true);
           Accept.setText("Accept");
           }
        }
});
        Exit.addActionListener (new ActionListener () {
        public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
});
    }
    public static void main(String[] args) {
        // TODO code application logic here
       Gui app = new Gui();
       app.setResizable(false);
       app.setVisible(true);
       
        
        
      
     //2016/11/16 12:08:43
        
    
     
         
        
     
        
    }
    
}
