/* CertificationRepository.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Certification;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Integer> {
	
	@Query("select c from Certification c where c.extinctionDate > ?1")
	Collection<Certification> findAllActive(Date currentMoment);
	
}
