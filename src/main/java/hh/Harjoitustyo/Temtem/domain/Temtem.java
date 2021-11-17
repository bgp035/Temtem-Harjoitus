package hh.Harjoitustyo.Temtem.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Temtem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long temId;
	

    @NotBlank(message = "Name is mandatory")
	private String name;
	
	
	@ManyToOne
	@JsonIgnoreProperties("temtems")
	@JoinColumn(name = "typeId")
	private TemType temType;
	
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
			name = "Temtem_Move",
			joinColumns = { @JoinColumn(name = "temId") },
			inverseJoinColumns = { @JoinColumn(name = "moveId") }
	)
	
	Set<Move> moves = new HashSet<>();
	
	
	public Temtem() {
		super();
	}
	
	public Temtem(String name, TemType temType) {
		super();
		this.name = name;
		this.temType = temType;
	}
	
	public Long getTemId() {
		return temId;
	}
	
	public void setTemId(Long temId) {
		this.temId = temId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public TemType getTemType() {
		return temType;
	}
	
	public void setTemType(TemType temType) {
		this.temType = temType;
	}

	
	@Override
	public String toString() {
		return "Temtem [temId=" + temId + ", name=" + name + ", temType=" + temType +"]";
	}
	
	
}
