package br.com.banco.models;
import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name = "conta")
public class Conta implements Serializable{
		private static final long serialVersionUID = 1L;
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_conta")
	    private Long id;

		@Column(name = "nome_responsavel", nullable = false)
	    private String nomeResponsavel;

		public Conta() {
			
		}
		public Conta(Long id, String nomeResponsavel) {
			super();
			this.id = id;
			this.nomeResponsavel = nomeResponsavel;
		}
		
	    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}
	

		
}
