package hh.Harjoitustyo.Temtem.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TemTypeRepository extends CrudRepository<TemType, Long> {

	//List<TemType> findByTemTypeIn(Long temType);
	
}
