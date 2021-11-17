package hh.Harjoitustyo.Temtem.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Move {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long moveId;
	
	
	private String moveName;
	
	
	private int damage;
	
	
	private String description;
	
	
	@ManyToOne
	@JsonIgnoreProperties("moves")
	@JoinColumn(name = "TemTypeId")
	private TemType temType;
	
	@ManyToMany(mappedBy = "moves")
	private Set<Temtem> temtems = new HashSet<>();
	
	
	public Move() {
		super();
	}


	
	public Move(String moveName, int damage, String description, TemType temType) {
		super();
		this.moveName = moveName;
		this.damage = damage;
		this.description = description;
		this.temType = temType;
	}




	public Long getMoveId() {
		return moveId;
	}


	public void setMoveId(Long moveId) {
		this.moveId = moveId;
	}

	
	
	public String getMoveName() {
		return moveName;
	}



	public void setMoveName(String moveName) {
		this.moveName = moveName;
	}



	public int getDamage() {
		return damage;
	}


	public void setDamage(int damage) {
		this.damage = damage;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public TemType getTemType() {
		return temType;
	}


	public void setTemType(TemType temType) {
		this.temType = temType;
	}



	@Override
	public String toString() {
		return "Move [moveId=" + moveId + ", moveName=" + moveName + ", damage=" + damage + ", description="
				+ description + "]";
	}


	
	
	
	
}
