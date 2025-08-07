package io.github.thejeremias.salario.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import io.github.thejeremias.salario.dao.UsuarioDao;
import io.github.thejeremias.salario.domain.Usuario;
import io.github.thejeremias.salario.dto.FiltroUsuarioDto;
import io.github.thejeremias.salario.dto.LoginDto;
import io.github.thejeremias.salario.exception.DaoException;
import io.github.thejeremias.salario.exception.NegocioException;
import io.github.thejeremias.salario.util.PasswordUtil;

public class UsuarioService {
	
	private UsuarioDao usuarioDao;
	
	public UsuarioService() {
		this.usuarioDao = new UsuarioDao();
	}
	
	public void autenticar(LoginDto loginDto, HttpSession session) throws NegocioException {
		try {
			Usuario usuarioBd = usuarioDao.findByNome(loginDto.getUsuario());
			if (usuarioBd == null || !PasswordUtil.checkPassword(loginDto.getSenha(), usuarioBd.getSenha())) {
				throw new NegocioException("Credencial inválida.");
			}
			session.setAttribute("usuario", usuarioBd);
		} catch(DaoException e) {
			if (e.isRegistroNaoEncontrado()) {
				throw new NegocioException("Usuário não encontrado.", e);
			}
			throw new NegocioException("Erro ao buscar usuário.", e);
		}
	}

	public void salvar(Usuario usuario) throws NegocioException {
		try {
			usuario.validar();
			usuario.setSenha(PasswordUtil.hashPassword(usuario.getSenha()));
			usuarioDao.save(usuario);
		} catch(DaoException e) {
			e.printStackTrace();
			throw new NegocioException("Erro ao tentar persistir registro", e);
		}
	}

	public List<Usuario> filterPaginadoProjetado(FiltroUsuarioDto filtroUsuarioDto) {
		return usuarioDao.filterPaginadoProjetado(filtroUsuarioDto);
	}

	public int countWithFilter(FiltroUsuarioDto filtroUsuarioDto) {
		return usuarioDao.countWithFilter(filtroUsuarioDto);
	}
	
	public void deleteById(Long id) throws NegocioException {
		try {
			usuarioDao.deleteById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new NegocioException("Erro ao tentar remover registro", e);
		}
	}
	
	public Usuario findById(Long id) {
		return usuarioDao.findById(id);
	}

}
