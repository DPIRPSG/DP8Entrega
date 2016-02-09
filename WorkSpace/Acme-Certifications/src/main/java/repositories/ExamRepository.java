/* ExamRepository.java
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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {

	@Query("select e from Exam e where e.certification.id = ?1")
	Collection<Exam> findByCertificationId(int certificationId);

}
