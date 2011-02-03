/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mcdev.util;

 import java.io.*;
 import java.util.concurrent.*;
 import java.util.logging.Level;
 import java.util.logging.Logger;

 /**
  *
  * @author janisk
  */
 public class ReadCommand implements Runnable {

   private DataInputStream in = null;
   LinkedBlockingQueue queue = null;

   /**
    * Creates a new instance of CommandRead
    * @param in - Data input stream of socket
    * @param queue - data output inteface
    */
   public ReadCommand(DataInputStream in, LinkedBlockingQueue queue) {
       this.in = in;
       this.queue = queue;
   }


   @Override
   public void run() {
       byte b = 0;
       String s = "";
       char ch;
       int a = 0;
       while (true) {
           int sk = 0;
           try {
               a = in.read();
           } catch (IOException ex) {
               return;
           }
           if (a != 0 && a > 0) {
               if (a < 0x80) {
                   sk = a;
               } else {
                   if (a < 0xC0) {
                       a = a << 8;
                       try {
                           a += in.read();
                       } catch (IOException ex) {
                           return;
                       }
                       sk = a ^ 0x8000;
                   } else {
                       if (a < 0xE0) {
                           try {
                               for (int i = 0; i < 2; i++) {
                                   a = a << 8;
                                   a += in.read();
                               }
                           } catch (IOException ex) {
                               Logger.getLogger(ReadCommand.class.getName()).log(Level.SEVERE, null, ex);
                               return;
                           }
                           sk = a ^ 0xC00000;
                       } else {
                           if (a < 0xF0) {
                               try {
                                   for (int i = 0; i < 3; i++) {
                                       a = a << 8;
                                       a += in.read();
                                   }
                               } catch (IOException ex) {
                                   Logger.getLogger(ReadCommand.class.getName()).log(Level.SEVERE, null, ex);
                                   return;
                               }
                               sk = a ^ 0xE0000000;
                           } else {
                               if (a < 0xF8) {
                                   try {
                                       a = 0;
                                       for (int i = 0; i < 5; i++) {
                                           a = a << 8;
                                           a += in.read();
                                       }
                                   } catch (IOException ex) {
                                       Logger.getLogger(ReadCommand.class.getName()).log(Level.SEVERE, null, ex);
                                       return;
                                   }
                               } else {
                               }
                           }
                       }
                   }
               }
               s += "\n";
               byte[] bb = new byte[sk];
               try {
                   a = in.read(bb, 0, sk);
               } catch (IOException ex) {
                   a = 0;
                   ex.printStackTrace();
                   return;
               }
               if (a > 0) {
                   s += new String(bb);
               }
           } else if (b == -1) {
               System.out.println("Error, it should not happen ever, or connected to wrong port");
           } else {
               try {
                   queue.put(s);
               } catch (InterruptedException ex) {
                   ex.printStackTrace();
                   System.out.println("exiting reader");
                   return;
               }
               s = "";
           }
       }
   }
 }