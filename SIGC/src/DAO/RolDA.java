package DAO;

import Beans.RolBE;
import Beans.UsuarioBE;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CESDE Modificado por Herverth
 */

// REALIZADO POR HUAYCHA GALINDO DIEGO KEVIN
public class RolDA extends BaseDA {

    String cadenaConexion;
    String DriverConnection;
    String user;
    String password;

    public RolDA() {
        cadenaConexion = super.getConnectionString();
        DriverConnection = super.getDriverConnection();
        user = super.getUser();
        password = super.getPassword();
    }

    public RolBE listarRolBE(RolBE oRolBE1) throws SQLException {
        RolBE oRolBE = null;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {
            oRolBE = new RolBE();
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);
            if (oRolBE1.getIndOpSp() == 1) {

                String sql = " SELECT idrol,nombrerol,estado FROM rol WHERE idrol=? and estado=true";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, oRolBE1.getIdrol());
                rs = pst.executeQuery();
                System.out.println("consulta :" + sql);

            }
            while (rs.next()) {
                oRolBE.setIdrol(rs.getInt("idrol"));
                oRolBE.setNombrerol(rs.getString("nombrerol"));
                oRolBE.setEstado(rs.getBoolean("estado"));
            }

            cn.commit();
            cn.setAutoCommit(true);
        } catch (Exception ex) {
            cn.rollback();
            ex.printStackTrace();
        } finally {

            rs.close();
            rs = null;
            cn.close();
            cn = null;

        }
        return oRolBE;
    }

    public ArrayList<RolBE> listarRegistroRolBE(RolBE oRolBE1) throws SQLException {
        ArrayList<RolBE> listaRolBE = null;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {
            listaRolBE = new ArrayList<RolBE>();
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);
            String sql = "";
            if (oRolBE1.getIndOpSp() == 1) {
                sql = " SELECT idrol,nombrerol,estado FROM rol WHERE estado=true";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
            }
            if (oRolBE1.getIndOpSp() == 2) {
                sql = " SELECT idrol,nombrerol,estado FROM rol WHERE idrol=? and estado=true";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, oRolBE1.getIdrol());
                rs = pst.executeQuery();
            }

            while (rs.next()) {
                RolBE oRolBE = new RolBE();
                oRolBE.setIdrol(rs.getInt("idrol"));
                oRolBE.setNombrerol(rs.getString("nombrerol"));
                oRolBE.setEstado(rs.getBoolean("estado"));
                listaRolBE.add(oRolBE);
            }

            cn.commit();
            cn.setAutoCommit(true);
        } catch (Exception ex) {
            cn.rollback();
            ex.printStackTrace();
        } finally {
            rs.close();
            rs = null;
            cn.close();
            cn = null;
            oRolBE1 = null;
        }
        return listaRolBE;
    }

    public int insertarRegistrosRolBE(ArrayList<RolBE> oListaRolBE) throws SQLException {
        int resultado = 0;
        Connection cn = null;
        CallableStatement cs = null;

        try {
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);

            cn.setAutoCommit(false);

            for (RolBE oRolBE : oListaRolBE) {
                cs = cn.prepareCall("{call uspInsertarRol(?,?,?)}");
                cs.setString(1, oRolBE.getNombrerol());
                cs.setBoolean(2, oRolBE.getEstado());
                cs.registerOutParameter(3, java.sql.Types.INTEGER);
                cs.execute();
                resultado = cs.getInt(3);
                cs.close();
                cs = null;
            }
            cn.commit();
            cn.setAutoCommit(true);
        } catch (Exception ex) {
            cn.rollback();
            ex.printStackTrace();
        } finally {
            cn.close();
            cn = null;

        }
        return resultado;
    }

    public int insertarRolBE(RolBE oRolBE) throws SQLException {
        int resultado = 0;
        Connection cn = null;
        CallableStatement cs = null;

        try {
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);

            cn.setAutoCommit(false);

            cs = cn.prepareCall("{call uspInsertarRol(?,?,?)}");
            cs.setString(1, oRolBE.getNombrerol());
            cs.setBoolean(2, oRolBE.getEstado());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.execute();
            resultado = cs.getInt(3);
            cs.close();
            cs = null;
            cn.commit();
            cn.setAutoCommit(true);
        } catch (Exception ex) {
            cn.rollback();
            ex.printStackTrace();
        } finally {
            cn.close();
            cn = null;

        }
        return resultado;
    }

    public int actualizarRolBE(RolBE oRolBE) throws SQLException {
        int resultado = 0;
        Connection cn = null;
        CallableStatement cs = null;

        try {
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);

            cn.setAutoCommit(false);

            cs = cn.prepareCall("{call uspActualizarRol(?,?,?)}");
            cs.setInt(1, oRolBE.getIdrol());
            cs.setString(2, oRolBE.getNombrerol());
            cs.setBoolean(3, oRolBE.getEstado());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            resultado = cs.getInt(3);
            cs.close();
            cs = null;
            cn.commit();
            cn.setAutoCommit(true);
        } catch (Exception ex) {
            cn.rollback();
            ex.printStackTrace();
        } finally {
            cn.close();
            cn = null;

        }
        return resultado;
    }

    public ResultSet listarRS(String query) throws SQLException {
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        try {
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);
            String sql = query;
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            cn.commit();
            cn.setAutoCommit(true);
        } catch (Exception ex) {
            cn.rollback();
            ex.getMessage();
        } finally {
            cn.close();
            cn = null;
        }
        return rs;
    }

    public List<RolBE> listarRoles() {
        ArrayList<RolBE> listarRol = new ArrayList<>();
        UtilDAO oUtilDAO = new UtilDAO();
            //se esta ejecutando el  Query para listar 
        oUtilDAO.ejecutarQuery("select * from rol  ");

        ResultSet resultados = oUtilDAO.ejecutarQuery("select * from rol  ");
               //se crea el try para ver las fallas
        try {
            while (resultados.next()) {
              //se esta realizando un bucle 
                RolBE oRolBE = new RolBE();

                int id = resultados.getInt("idrol");
                String nombrerol = resultados.getString("nombrerol");
                boolean estado = resultados.getBoolean("estado");
                String descripcion = resultados.getString("descripcion");

                oRolBE.setIdrol(id);
                oRolBE.setNombrerol(nombrerol);
                oRolBE.setEstado(estado);
                oRolBE.setDescripcion(descripcion);

                listarRol.add(oRolBE);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
                // retorna los datos del Listado de los Roles
        return listarRol;
    }
  // se esta creando un public para agregar los Roles
    public RolBE addRol(RolBE oRolBE) {

        UtilDAO oUtilDAO = new UtilDAO();

        String cadquery = ("insert into "
                + " rol(nombrerol,estado,descripcion) values ("
                + " '" + oRolBE.getNombrerol() + "'"
                + ", '" + oRolBE.getEstado() + "'"
                + ", '" + oRolBE.getDescripcion() + "');");

        int cad = oUtilDAO.ejecutarUpdate(cadquery);

        System.out.println("resultado" + cad);
        try {

            oRolBE.setIndOpSp(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            oRolBE.setIndOpSp(2);
        }

        return oRolBE;
    }

    public RolBE updateRol(RolBE oRolBE) {
     //Actualizando los Roles
        UtilDAO oUtilDAO = new UtilDAO();
        try {
            String cadquery = ("update rol "
                    + " set nombrerol = '" + oRolBE.getNombrerol() + "' "
                    + " ,estado = '" + oRolBE.getEstado() + "' "
                    + " ,descripcion = '" + oRolBE.getDescripcion() + "' "
                    + " where idrol= " + oRolBE.getIdrol() + ";");

            int cad = oUtilDAO.ejecutarUpdate(cadquery);

            System.out.println("resultado" + cad);

            oRolBE.setIndOpSp(1);

            //se realiza la comprobacion de la actualizacion.
            if (cad == 1) {
                oRolBE.setIndOpSp(1);
            } else {
                oRolBE.setIndOpSp(2);
            }

        } catch (Exception e) {
            oRolBE.setIndOpSp(2);
        }

        return oRolBE;
    }
}
