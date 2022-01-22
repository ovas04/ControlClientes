
package pe.com.gm.web;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pe.com.gm.dao.iPersonaDao;
import pe.com.gm.domain.Persona;
import pe.com.gm.servicio.PersonaService;

@Controller
@Slf4j
public class ControladorIncio {
    
    @Autowired
    private PersonaService personaService;
    
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user ){
        
        var personas = personaService.listarPersonas();

        log.info("usuario que hizo login :" + user);
        
        log.info("Ejecuntado Controlador Spring MVC");
        
        model.addAttribute("personas",personas);
        
        var saldoTotal = 0D;
        
        for(var p: personas){
            
            saldoTotal += p.getSaldo();
            
        }
        
        model.addAttribute("saldoTotal",saldoTotal);
        model.addAttribute("totalClientes",personas.size());
        
        return "index";  // El archivo HTML debe llevar el mismo nombre
        
    }
 
    @GetMapping("/agregar")
    public String agregar(Persona persona){
        
        return "modificar";
        
    }
    
    
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        
        if(errores.hasErrors()){
            
            return "modificar";
            
        }
        
        personaService.guardar(persona);
        
        return "redirect:/";
       
    } 
    
    @GetMapping("/editar/{id_persona}")
    public String editar(Persona persona, Model model){
        
        persona = personaService.encontraPersona(persona);
        
        model.addAttribute("persona",persona);
        
        return "modificar";
       
    }
    
    /* Otra forma
     @GetMapping("/eliminar/{id_persona}")
    public String eliminar(Persona persona){
        
        personaService.eliminar(persona);        
        
        return "redirect:/";
       
    } */
     
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        
        personaService.eliminar(persona);        
        
        return "redirect:/";
       
    }
    
}