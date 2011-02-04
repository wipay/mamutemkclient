/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mcdev.app;

import br.com.mcdev.util.ApiConn;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiago
 */
class DataReceiver extends Thread {

   private ApiConn aConn = null;


   public DataReceiver(ApiConn aConn) {
       this.aConn = aConn;

   }

   @Override
   public void run() {
       String s = "";
       while (true) {
           try {
               s = aConn.getData();
               if (s != null) {

                   if (s.contains("!done")) {
                   }
               }
           } catch (InterruptedException ex) {
               Logger.getLogger(DataReceiver.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
 }