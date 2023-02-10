/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class Encryptor {
    public static String encryptString(String pass) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(pass.getBytes());
        BigInteger big = new BigInteger(1,messageDigest);
        return big.toString(16);
    }
    public static void main(String[] args) throws NoSuchAlgorithmException {
        ArrayList<String> a = new ArrayList<>();
        a.add("GV001"); 
         a.add("GV002");   
          a.add("GV003");   
           a.add("GV004"); 
            
           a.add("GV005"); 
           
        for(int i =0;i<a.size();i++){
                 System.out.println(a.get(i)+ " password la: " +encryptString(a.get(i)));
        }



       
    }
}
