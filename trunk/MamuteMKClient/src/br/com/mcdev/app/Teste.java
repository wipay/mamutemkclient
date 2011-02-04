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
public class Teste {
    public static void main(String[] args) {
       ApiConn ret = new ApiConn("192.168.88.1", 8728);
       if (!ret. isConnected()) {
           ret.start();
           try {
               ret.join();
               if (ret.isConnected()) {
                   ret.login("172.16.23.1", 8728);
               }
           } catch (InterruptedException ex) {

               return null;
           }
       }
       aConn.sendCommand("/ip/address/print");
       DataReceiver dataRec = new DataReceiver(aConn, this);
       dataRec.start();
    }
    
}

