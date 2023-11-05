package com.projeto.ssxund3r.lojavirtual.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
public class Usuario implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	@Column(name = "id_usuario")
	private Long id;
	
	@Column(name="login_usu", nullable = false)
	private String login;
	
	@Column(name="senha_usu", nullable = false)
	private String senha;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_atual_senha_usu")
	private Date dataAtualSenha;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuarios_acesso", uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_acesso"} , 
	name = "unique_acesso_user"), 
	joinColumns = @JoinColumn(name ="id_usuario", referencedColumnName = "id_usuario", table = "usuario", 
	unique = false, foreignKey = @ForeignKey(name = "fk_usuario", value = ConstraintMode.CONSTRAINT)),
	inverseJoinColumns = @JoinColumn(name = "id_acesso", unique = false, referencedColumnName = "id_acesso", table = "acesso",
	foreignKey = @ForeignKey(name = "fk_acesso", value = ConstraintMode.CONSTRAINT)))
	private List<Acesso> acessos;
	
	@ManyToOne
	@JoinColumn(name="id_pessoa", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_pessoa"))
	private Pessoa pessoa;
	
	/*Autoridades = São os acessos, ou seja ROLE_ADMIN, ROLE_SECRETARIO, ROLE_FINANCEIRO*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.acessos;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}