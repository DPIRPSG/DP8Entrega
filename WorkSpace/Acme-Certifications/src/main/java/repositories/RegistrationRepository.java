/* RegistrationRepository.java
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

import domain.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

	@Query("select r from Registration r where r.announcement.id = ?1")
	Collection<Registration> findByAnnouncementId(int announcementId);

	@Query("select r from Registration r where r.owner.id = ?1 and r.announcement.id = ?2")
	Registration findByCustomerIdAndAnnouncementId(int customerId, int announcementId);
	
}
