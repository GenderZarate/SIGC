package Services;

import Beans.CarreraEspecialidadBE;
import DAO.CarreraEspecialidadDA;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CESDEDOCLAB04
 */
public class CarreraEspecialidadBL {

    public List<CarreraEspecialidadBE> listarCarrera() {

        //esta creando una instacia oCarreraEspecialidadDA
        CarreraEspecialidadDA oCarreraEspecialidadDA = new CarreraEspecialidadDA();
        
        //se guarda la lista en oListaCarreraEspecialidadBE
        List<CarreraEspecialidadBE> oListaCarreraEspecialidadBE = oCarreraEspecialidadDA.listarCarreras();

        //retornando la lista CarreraEspecialidadBE
        return oListaCarreraEspecialidadBE;

    }

    public CarreraEspecialidadBE findcarrerasById(int id) {

         //esta creando una instacia oCarreraEspecialidadDA
        CarreraEspecialidadDA oCarreraEspecialidadDA = new CarreraEspecialidadDA();
        
         //esta creando una instacia oCarreraEspecialidadBE
        CarreraEspecialidadBE oCarreraEspecialidadBE = new CarreraEspecialidadBE();

        
        //la instancia oCarreraEspecialidadDA esta extraendo el Id mediante el metodo findCarreraById
        oCarreraEspecialidadBE = oCarreraEspecialidadDA.findCarreraById(id);

        //retornando la lista CarreraEspecialidadBE
        return oCarreraEspecialidadBE;
    }

    public CarreraEspecialidadBE updateCarrera(CarreraEspecialidadBE oCarreraEspecialidadBE) {

         //esta creando una instacia oCarreraEspecialidadDA
        CarreraEspecialidadDA oCarreraEspecialidadDA = new CarreraEspecialidadDA();
        
        //extraer los compos para realizar las modificaciones 
        String carrera, descripcion, estado;
        carrera = (oCarreraEspecialidadBE.getCarrera());
        descripcion = (oCarreraEspecialidadBE.getDescripcion());
        estado = (oCarreraEspecialidadBE.getEstado() + "");

        
        // realiza la compracion para poder hacer la actualizacion
        if (carrera.equals("") || descripcion.equals("")
                || estado.equals("")) {
            
            //aqui es donde indica el id 3 cuando se realizo la actualizacion
            oCarreraEspecialidadBE.setIndOpSp(3);
            
           //retornando la lista CarreraEspecialidadBE
            return oCarreraEspecialidadBE;
        
            //
        } else {
            CarreraEspecialidadBE resultados = oCarreraEspecialidadDA.updatecarrera(oCarreraEspecialidadBE);
            
            
            return resultados;
        }
    }

    public CarreraEspecialidadBE addCarrera(CarreraEspecialidadBE oCarreraEspecialidadBE) {

         //esta creando una instacia oCarreraEspecialidadDA
        CarreraEspecialidadDA oCarreraEspecialidadDA = new CarreraEspecialidadDA();
        
        //extraer los compos para realizar las modificaciones 
        String carrera, descripcion;
        boolean estado;

        //extraer los compos para realizar las modificaciones 
        carrera = (oCarreraEspecialidadBE.getCarrera());
        descripcion = (oCarreraEspecialidadBE.getDescripcion());
        estado = (oCarreraEspecialidadBE.getEstado());
        
        // realiza la compracion para poder hacer la actualizacion
        if (carrera.equals("") || descripcion.equals("")) {

          //aqui es donde indica el id 2 cuando se realizo la actualizacion
            oCarreraEspecialidadBE.setIndOpSp(2);
            
            //retornando la lista CarreraEspecialidadBE
            return oCarreraEspecialidadBE;
        } else {
            
            return oCarreraEspecialidadDA.addCarrera(oCarreraEspecialidadBE);
        }
    }

}
