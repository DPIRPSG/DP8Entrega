package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.CustomizationInfo;

import repositories.CustomizationInfoRepository;

@Service
@Transactional
public class CustomizationInfoService {
	
	// Managed repository -----------------------------------------------------

		@Autowired
		private CustomizationInfoRepository customizationInfoRepository;

		
		public CustomizationInfo findOne(int customizationInfoId) {
			Assert.notNull(customizationInfoId);
			
			CustomizationInfo result;
			
			result = customizationInfoRepository.findOne(customizationInfoId);
			
			return result;
		}
		
		public Collection<CustomizationInfo> findAll(){
			Collection<CustomizationInfo> result;
			
			result = customizationInfoRepository.findAll();
			
			return result;		
		}
}
