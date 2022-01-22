/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.gm.servicio;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.com.gm.dao.UsuarioDao;
import pe.com.gm.domain.Rol;
import pe.com.gm.domain.Usuario;

@Service("userdetalis")
@Slf4j
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioDao.findByUsername(username);
        
        if(usuario == null){
            
            throw new UsernameNotFoundException(username);
        }
        
        var roles = new ArrayList<GrantedAuthority>();
        
        for (Rol rol: usuario.getRoles()){
            
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
              
        
    }

}
