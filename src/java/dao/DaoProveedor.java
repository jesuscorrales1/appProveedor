/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Proveedor;
import java.util.List;

/**
 *
 * @author jesus
 */
public interface DaoProveedor {
    
    List<Proveedor> obtenerProveedores();
    Proveedor obtenerunProveedor(String ruc);
    boolean insertarProveedor(Proveedor proveedor);
    boolean actualizarProveedor(Proveedor proveedor);
    boolean habilitarProveedorString(String ruc, String estado);
}
