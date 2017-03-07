/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Res;

import java.net.URL;

/**
 *
 * @author maksdave@gmail.com
 */
    public class Variables {
    private String[] workDuties = {"Helpdesk","Education","Paperwork","Lunch","Various"}; //Задаем параметры для списка.       
    private String date;
    public void setDate(String date)
    {
        this.date = date;
    }
    public  String getDate() //Возвращаем дату
    {
        return date;
    }
    private String outValue;
    public String getOutValue()
    {
        return outValue; 
    }
    public void setOutValue(String outValue)
    {
        this.outValue = outValue;
    }
    public String getFullInfo()//получаем значение для записи
    {
        return (getDate() + " " + getOutValue());  //заменить массив единичным выбранным значением из списка.
    }
    public String[] getWorkDuties() //получаем значение 
    {
        return workDuties;
    }
    private URL path = Variables.class.getResource("Logo.png"); //личная гениальная идея вызова лого =) по-другому не заводилось.
    public URL getPath()
    {
        return path;
    }
        }   

