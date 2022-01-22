/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.gm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.gm.domain.Usuario;

/**
 *
 * @author MSI
 */
public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    
    Usuario findByUsername(String username);
    
}
