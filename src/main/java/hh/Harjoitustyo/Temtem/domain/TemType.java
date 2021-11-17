package hh.Harjoitustyo.Temtem.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class TemType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	
	private Long typeId;
	
	
	private String name;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "temType")
	@JsonIgnoreProperties("temType")
	private List<Temtem> temtems;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "temType")
	@JsonIgnoreProperties("temType")
	private List<Move> moves;

	
	
	public TemType() {
		super();
	}

	public TemType(String name) {
		super();
		this.name = name;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Temtem> getTemtems() {
		return temtems;
	}

	public void setTemtems(List<Temtem> temtems) {
		this.temtems = temtems;
	}

	public List<Move> getMoves() {
		return moves;
	}

	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}

	@Override
	public String toString() {
		return "TemType [typeId=" + typeId + ", name=" + name + "]";
	}
	
	
	
	
}
