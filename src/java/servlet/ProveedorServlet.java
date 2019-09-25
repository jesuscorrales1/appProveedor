/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import dao.DaoProveedor;
import dao.impl.DaoProveedorImpl;
import dto.Proveedor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jesus
 */
@WebServlet(name = "ProveedorServlet", urlPatterns = {"/proveedor"})
public class ProveedorServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Gson gson = new Gson();
        DaoProveedor daoproveedor = new DaoProveedorImpl();
        
        String ruc = request.getParameter("oruc");
        String r;
        if (ruc != null) {
            r = gson.toJson(daoproveedor.obtenerunProveedor(ruc));
            
        }else {
        r = gson.toJson(daoproveedor.obtenerProveedores());
        }
         
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(r);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         Gson myGson = new Gson ();
         
         /*
         	"ruc": "20109333159",
	"rsocial": "Pepito2";
	"rlegal": "Lima";
	"direccion": "Lima";
         */
         
         Proveedor myproveedor = myGson.fromJson(request.getReader(), Proveedor.class);
         
         DaoProveedor myDaoProveedor = new DaoProveedorImpl();
         
         boolean resultado =  myDaoProveedor.insertarProveedor(myproveedor); 
         
         String resultadoString;
         
         if (resultado) {
            resultadoString = "{\"msg\":\"Se registro correctamente\"}";
        }else{
             resultadoString = "{\"msg\":\"Error\"}";
        }
         
         
         
         
         response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(resultadoString);
        }
        
        
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Gson myGson = new Gson ();
        
        /*
        {
	"ruc": "20109333159",
	"rsocial": "Pepito3",
	"rlegal": "Arequipa",
	"direccion": "Paucarpata"
}
        */
               
        
        Proveedor myproveedor = myGson.fromJson(request.getReader(), Proveedor.class);
         
         DaoProveedor myDaoProveedor = new DaoProveedorImpl();
         
         boolean resultado =  myDaoProveedor.actualizarProveedor(myproveedor); 
         
         String resultadoString;
         
         if (resultado) {
            resultadoString = "{\"msg\":\"Se actualizo correctamente\"}";
        }else{
             resultadoString = "{\"msg\":\"Error\"}";
        }
         
         
         
         
         response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(resultadoString);
        }
        
        
        
        
        
        
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        
        Gson myGson = new Gson();
        DaoProveedor daoProveedor = new DaoProveedorImpl();
        String ruc = req.getParameter("ruc");
        String estado = req.getParameter("estado");
        
       boolean resultado = daoProveedor.habilitarProveedorString(ruc, estado); 
       
       String resultadoString;
       
        if (resultado) {
            resultadoString = "{\"msg\":\"Se cambio correctamente\"}";
        }else{
            resultadoString = "{\"msg\": \"Error\"}";
        }
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(resultadoString);
        }
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
