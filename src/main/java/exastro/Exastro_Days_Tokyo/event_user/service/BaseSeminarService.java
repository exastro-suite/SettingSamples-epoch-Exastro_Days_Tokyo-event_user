/*   Copyright 2021 NEC Corporation
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package exastro.Exastro_Days_Tokyo.event_user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import exastro.Exastro_Days_Tokyo.event_user.repository.SeminarRepository;
import exastro.Exastro_Days_Tokyo.event_user.service.dto.SeminarDto;

public abstract class BaseSeminarService {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected SeminarRepository repository;
	
	public BaseSeminarService() {
		
	}

	public List<SeminarDto> getSeminar(int eventId) {

		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		List<SeminarDto> seminarList = null;
		
		try {
			seminarList = repository.getSeminar(eventId)
					.stream()
					.map(s -> new SeminarDto(s.getSeminarId(), s.getSeminarName(), s.getBlockId(),
							s.getBlockName(), s.getStartDatetime(), s.getSpeakerId()))
					.collect(Collectors.toList());
		}
		catch(Exception e) {
			throw e;
		}
		
		return seminarList;
	}

}
