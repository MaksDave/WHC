/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Output.WriteToFile;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Res.Variables;
import java.io.File;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * maksdave@gmail.com
 */
public class Gui extends JFrame {
    Variables dutyCheckBox = new Variables();
    WriteToFile fileWriterStream = new WriteToFile();
    private JButton Exit = new JButton("Exit"); //Объявляем кнопки
    private JButton Accept = new JButton("Where to save?");
    private JComboBox Box = new JComboBox(dutyCheckBox.getWorkDuties()); //получаем
    private JButton SaveTo = new JButton("Save to...");
    private JCheckBox onTop = new JCheckBox("on Top", false);
    //private JCheckBox SimpleMode = new JCheckBox("Simple mode", false);

    public Gui() { //Задаем параметры конструктора Swing
        super("Work Hours Counter");
        this.setBounds(200, 100, 480, 75);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());
        container.add(Box);
        container.add(Accept);
        container.add(SaveTo);
        container.add(Exit);
        container.add(onTop);
       
        container.setBackground(Color.YELLOW);
      
        onTop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JCheckBox onTop = (JCheckBox) event.getSource();
                if (onTop.isSelected()) {
                    setAlwaysOnTop(true);
                } else {
                    setAlwaysOnTop(false);
                }
            }
        });
        Image icon = Toolkit.getDefaultToolkit().getImage(dutyCheckBox.getPath());
        this.setIconImage(icon);
        Accept.setEnabled(false);
        Accept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fileWriterStream.getSfile() == null) {
                    Accept.setEnabled(false);
                    Accept.setText("Where to save?");
                } else {
                    String forDate = "yyyy/MM/dd HH:mm"; //Date template
                    DateFormat dateFormat = new SimpleDateFormat(forDate);
                    dutyCheckBox.setDate(dateFormat.format(new Date()));
                  
                    fileWriterStream.setText(dutyCheckBox.getFullInfo());
                    dutyCheckBox.setOutValue(Box.getSelectedItem().toString());
                    try {
                        fileWriterStream.writeToFile(fileWriterStream.getSfile(), dutyCheckBox.getFullInfo());
                    } catch (IOException ex) {
                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        SaveTo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

               //I want to implement filechooser with only .txt type.
                final JFileChooser sFC = new JFileChooser();
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Notepad .txt", "txt"); 
                sFC.setAcceptAllFileFilterUsed(false);// This one hide other types.
                        
                sFC.addChoosableFileFilter(restrict);
                //sFC.setFileFilter(sFilter);
                sFC.showSaveDialog(SaveTo);
                if (sFC.getSelectedFile().getAbsolutePath().endsWith(".txt")){
                fileWriterStream.setSfile(sFC.getSelectedFile());
                }
                
                else {
                fileWriterStream.setSfile( new File(sFC.getSelectedFile() + ".txt"));}
                
               
                sFC.setDialogTitle("Specify a file to save...");   //Выбрать файл по умолчанию
                
                if (fileWriterStream.getSfile() != null) {
                    Accept.setEnabled(true);
                    Accept.setText("Accept");}
                
                else {Accept.setEnabled(false);
                Accept.setText("Where to save?");
                }
                
            }
        });
        Exit.addActionListener(new ActionListener() {
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
