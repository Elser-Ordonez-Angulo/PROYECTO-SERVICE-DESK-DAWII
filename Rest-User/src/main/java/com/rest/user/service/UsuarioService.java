package com.rest.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.user.entity.Reclamo;
import com.rest.user.feingclients.ServiceFeingUser;
import com.rest.user.model.Usuario;
import com.rest.user.repository.IUsuarioRepository;

import jakarta.transaction.Transactional;


@Service
public class UsuarioService {
	@Autowired
	IUsuarioRepository usuarios;
	
	@Autowired
	ServiceFeingUser serviceFeingUser;
	
	
	public List<Usuario> listarUsuario(){
		return usuarios.findAll();
	}
	//Metodo que busca a un usuario por su DNI
	public Usuario buscarUsuarioPorDni(int dniUsuario) {
	    return usuarios.findByDniUsuario(dniUsuario).orElse(null);
	}
	//Metodo para buscar un usuario por ID
		public Usuario buscarUsuario(Integer id) {
			return usuarios.findById(id).orElse(null);
		}
		
	//Metodo para crear un usuario
	public Usuario crearUsuario(Usuario usuario) {
		return usuarios.save(usuario);
	}
	
	public Reclamo crearReclamo (Reclamo reclamo) {
		return serviceFeingUser.crearReclamo(reclamo);
	}
	
	//Metodo para Actualizar USUARIO por DNI
	public Usuario actualizarUsuarioPorDni(int dniUsuario, Usuario usuarioActualizado) {
	    Usuario usuarioExistente = usuarios.findByDniUsuario(dniUsuario).orElse(null);
	    if (usuarioExistente != null) {
	        usuarioExistente.setNombreUsuario(usuarioActualizado.getNombreUsuario());
	        usuarioExistente.setApellidoUsuario(usuarioActualizado.getApellidoUsuario());
	        usuarioExistente.setEmailUsuario(usuarioActualizado.getEmailUsuario());
	        usuarioExistente.setDireccionUsuario(usuarioActualizado.getDireccionUsuario());
	        usuarioExistente.setClave(usuarioActualizado.getClave());
	        
	        
	        return usuarios.save(usuarioExistente);
	    }
	    return null; 
	}
	 @Transactional
	    public void borrarUsuarioPorDni(int dniUsuario) {
	        // Elimina el usuario de la base de datos
	        usuarios.deleteByDniUsuario(dniUsuario);
	    }
	 
	
}
