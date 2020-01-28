
package ad_ex28_bdr3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AD_Ex28_bdr3 {
    /*
     desenvolve un proxecto java chamado bdor3 que  amose todos 
     os datos de todos
     os empregados da taboa empregados
     */
  public static Connection conexion = null;
   public static Connection getConexion() throws SQLException{
        String usuario = "hr";
        String password = "hr";
        String host = "localhost"; 
        String puerto = "1521";
        String sid = "orcl";
        String ulrjdbc = "jdbc:oracle:thin:" + usuario + "/" + password + "@" + host + ":" + puerto + ":" + sid;
        
         conexion = DriverManager.getConnection(ulrjdbc);
            return conexion;
        
    }
    public static void closeConexion() throws SQLException {
      conexion.close();
      }
    
    public void mostrarEmpregados() {
        try {
            PreparedStatement pstm = conexion.prepareStatement("select * from empregadosbdor");

            ResultSet rs = pstm.executeQuery();

            System.out.println("ID\tTIPO_EMP\tSALARIO\n");

            //RECORREMOS EL RS
            while (rs.next()) {
                
                System.out.print(rs.getInt(1) + "\t");

                //TIPO_EMP (objeto)
                java.sql.Struct objSQL = (java.sql.Struct) rs.getObject(2);
                
                Object[] campos = objSQL.getAttributes();
                
                String nome = (String) campos[0];
                
                java.math.BigDecimal edade = (java.math.BigDecimal) campos[1];

                System.out.print(nome + " " + edade + "\t");

               
                System.out.print(rs.getInt(3));

                System.out.println("\n");

            }

        } catch (SQLException ex) {
            Logger.getLogger(AD_Ex28_bdr3.class.getName()).log(Level.SEVERE, null, ex);
        }

}
    
    public static void main(String[] args) throws SQLException {
    AD_Ex28_bdr3 obj = new AD_Ex28_bdr3();

        obj.getConexion();

        obj.mostrarEmpregados();

       obj.closeConexion();
      
    }
    
}
