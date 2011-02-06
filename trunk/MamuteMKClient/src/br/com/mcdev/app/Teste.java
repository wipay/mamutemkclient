/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mcdev.app;

import br.com.mcdev.util.ApiConn;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tigun
 */
public class Teste {

    public static void main(String[] args) {
        try {
            ApiConn aConn = new ApiConn("10.2.1.53", 8728);
            aConn.start();
            try {
                aConn.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(MCMKLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (aConn.isConnected()) {
                String confirm = aConn.login("admin", "tigrao123456".toCharArray());
                if (confirm.equals("successful")) {
                    System.out.println("Conectou Perfeitamente");
                } else {
                    System.out.println("A senha falhou");
                }
            } else {
                System.out.println("Falha na conexão");
            }
            aConn.sendCommand("/system/identity/getall");
            Pattern fName = Pattern.compile("(?<==name=)\\S+");
            Matcher m = fName.matcher(aConn.getData());
            while (m.find()) {
                System.out.println(m.group());
            }

           

        } catch (InterruptedException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
