/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mcdev.control;

import br.com.mcdev.entitys.Address;
import br.com.mcdev.util.ApiConn;
import java.util.List;

/**
 *
 * @author tiago
 */
public class CtAddress {

    private ApiConn conn;
    private List<Address> lad;

    public List<Address> getList(){
        List<Address> listad=null;
        return listad;
    }

    public Address findById(Long id){
        Address adr=null;
        return adr;
    }


}
