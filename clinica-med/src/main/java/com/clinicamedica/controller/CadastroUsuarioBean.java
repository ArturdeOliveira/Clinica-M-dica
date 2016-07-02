package com.clinicamedica.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import com.clinicamedica.dao.GrupoDAO;
import com.clinicamedica.modelo.Grupo;
import com.clinicamedica.modelo.Usuario;
import com.clinicamedica.service.CadastroUsuarioService;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoDAO grupos;

	private Usuario usuario;
	private List<Grupo> gruposTela;
	private Grupo grupoAdicionado;
	private UploadedFile fotoUsuario;

	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	public void inicializar() {
		this.gruposTela = this.grupos.todos();
	}

	public CadastroUsuarioBean() {
		limpar();

	}

	public void salvar() {
		try {
			
			this.usuario = this.cadastroUsuarioService.salvar(this.usuario);
			FacesUtil.addSuccessMessage("Usuário salvo com sucesso!");
			this.limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void excluirGrupo() {
		this.usuario.getGrupos().remove(this.grupoAdicionado);
	}

	private void limpar() {
		this.usuario = new Usuario();
	}

	public void adicionarGrupo() {
		List<Grupo> gruposDoUsuario = this.usuario.getGrupos();

		if (gruposDoUsuario != null && this.grupoAdicionado != null && this.grupoAdicionado.getId() != null) {
			for (int i = 0; i < gruposDoUsuario.size(); i++) {
				if (gruposDoUsuario.get(i).getNome().equals(this.grupoAdicionado.getNome())) {
					throw new NegocioException("Grupo já está adicionado");
				}
			}
		}

		if (this.grupoAdicionado != null && this.grupoAdicionado.getId() != null) {
			this.usuario.getGrupos().add(this.grupoAdicionado);
		}

		this.grupoAdicionado = null;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public List<Grupo> getGruposTela() {
		return this.gruposTela;
	}

	public Grupo getGrupoAdicionado() {
		return this.grupoAdicionado;
	}

	public void setGrupoAdicionado(Grupo grupoAdicionado) {
		this.grupoAdicionado = grupoAdicionado;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UploadedFile getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(UploadedFile fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

	public boolean isEditando() {
		return this.usuario.getId() != null;
	}

}
