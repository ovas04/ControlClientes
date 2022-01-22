package pe.com.gm.servicio;

import java.util.List;
import pe.com.gm.domain.Persona;

public interface PersonaService {

    public List<Persona> listarPersonas();
 
    public void guardar(Persona persona);
    
    public void eliminar(Persona persona);
    
    public Persona encontraPersona(Persona persona);
    
}
