/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DaoProveedor;
import db.ConnectDB;
import dto.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/*,*
 *
 * @author jesus
 */
public class DaoProveedorImpl implements DaoProveedor{
    private final ConnectDB db;
    private final StringBuilder sql;

    public DaoProveedorImpl() {
        db = new ConnectDB();
        sql = new StringBuilder();
    }

    @Override
    public List<Proveedor> obtenerProveedores() {
        sql.delete(0, sql.length());
        sql.append("SELECT vc_ruc, vc_rsocial, vc_rlegal, vc_direccion, ch_habilitar FROM t_proveedor");
        
        try (
                //Almacenar conexion en el objeto "cn"
                Connection cn = db.getConnection();
                //Preparar el script a ejecutar "ps"
                PreparedStatement ps = cn.prepareCall(sql.toString());
                //ejacutar script y almacenar resultado en "rs"
                ResultSet rs = ps.executeQuery();
                ){
            
            List<Proveedor> listarProveedor = new LinkedList<>();
            
            while (rs.next()) {       
                
                //Instancear la clase proveedor al parametro proveedor
                Proveedor proveedor = new Proveedor();
                proveedor.setRuc(rs.getString("vc_ruc"));
                proveedor.setRsocial(rs.getString("vc_rsocial"));
                proveedor.setRlegal(rs.getString("vc_rlegal"));
                proveedor.setDireccion(rs.getString("vc_direccion"));
                proveedor.setHabilitar(rs.getString("ch_habilitar"));
                
                listarProveedor.add(proveedor);
             }
            
            return listarProveedor;
            
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Proveedor obtenerunProveedor(String ruc) {
        sql.delete(0, sql.length());
        sql.append("SELECT vc_ruc, vc_rsocial, vc_rlegal, vc_direccion, ch_habilitar "
                + "FROM t_proveedor "
                + "WHERE vc_ruc = ?");
        
        try (
                Connection cn = db.getConnection();
              PreparedStatement ps = cn.prepareCall(sql.toString());
              ){
            
            ps.setString(1, ruc);
            
            try (ResultSet rs = ps.executeQuery()){
                
                if (rs.next()){
                    Proveedor proveedor = new Proveedor();
                    proveedor.setRuc(rs.getString("vc_ruc"));
                    proveedor.setRsocial(rs.getString("vc_rsocial"));
                    proveedor.setRlegal(rs.getString("vc_rlegal"));
                    proveedor.setDireccion(rs.getString("vc_direccion"));
                    proveedor.setHabilitar(rs.getString("ch_habilitar"));
                    
                    return proveedor;
                }else{
                    return null;
                }
                
                
            } catch (Exception e) {
                return null;
            }
              
                      
        } catch (Exception e) {
            return null;
        }
        
     }

    @Override
    public boolean insertarProveedor(Proveedor proveedor) {
        
        sql.delete(0, sql.length());
        sql.append("INSERT INTO t_proveedor (vc_ruc, vc_rsocial, vc_rlegal, vc_direccion, ch_habilitar)"
        + "VALUES (?, ?, ?, ?, 'Y')");
        
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareCall(sql.toString());
                ){
            ps.setString(1, proveedor.getRuc());
            ps.setString(2, proveedor.getRsocial());
            ps.setString(3, proveedor.getRlegal());
            ps.setString(4, proveedor.getDireccion());
            
           int i = ps.executeUpdate();
           
            if (i==0) {
                return false;
            }else{
                return true;
            }
        } catch (Exception e) {
            return false;
        }
       }

    @Override
    public boolean actualizarProveedor(Proveedor proveedor) {
        sql.delete(0, sql.length());
        sql.append("UPDATE t_proveedor SET vc_rsocial = ?, vc_rlegal = ?, vc_direccion = ? WHERE vc_ruc = ?");
        
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareCall(sql.toString());
                ){
            ps.setString(1, proveedor.getRsocial());
            ps.setString(2, proveedor.getRlegal());
            ps.setString(3, proveedor.getDireccion());
            ps.setString(4, proveedor.getRuc());
            
            int i = ps.executeUpdate();
           
            if (i==0) {
                return false;
            }else{
                return true;
            }
            
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean habilitarProveedorString(String ruc, String estado) {
        sql.delete(0, sql.length());
        sql.append("UPDATE t_proveedor SET ch_habilitar = ? WHERE vc_ruc = ?");
        
        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareCall(sql.toString());
                ){
            ps.setString(1, estado);
            ps.setString(2, ruc);
                        
            int i = ps.executeUpdate();
           
            if (i==0) {
                return false;
            }else{
                return true;
            }
        } catch (Exception e) {
            return false;
          }
    }

}
