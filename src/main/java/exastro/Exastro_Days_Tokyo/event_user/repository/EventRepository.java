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

package exastro.Exastro_Days_Tokyo.event_user.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import exastro.Exastro_Days_Tokyo.event_user.repository.config.ConnectionConfig;
import exastro.Exastro_Days_Tokyo.event_user.repository.vo.EventDetailVO;
import exastro.Exastro_Days_Tokyo.event_user.repository.vo.EventVO;

@Repository
public class EventRepository extends BaseRepository {
	
	@Autowired
	public EventRepository(@Qualifier("configEvent") ConnectionConfig connectionConfig,
			RestTemplate restTemplate) {
		this.connectionConfig = connectionConfig;
		this.restTemplate = restTemplate;
	}
	
	public List<EventVO> getEvent() {
		
		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		String apiPath = "/api/v1/event";
		String apiUrl = connectionConfig.buildBaseUri() + apiPath;
		
		EventVO[] resBody = null;
		try {
			
			logger.debug("restTemplate.getForEntity [apiUrl: " + apiUrl + "]");
			resBody = restTemplate.getForObject(apiUrl, EventVO[].class);
			
			return Arrays.asList(resBody);
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public EventDetailVO getEventDetail(int eventId) {
		
		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		String apiPath = "/api/v1/event/{eventId}";
		String apiUrl = connectionConfig.buildBaseUri() + apiPath;
		
		EventDetailVO resBody = null;
		try {
			
			logger.debug("restTemplate.getForEntity [apiUrl: " + apiUrl + "], [eventId: " + eventId + "]");
			resBody = restTemplate.getForObject(apiUrl, EventDetailVO.class, eventId);
			
			return resBody;
		}
		catch(HttpClientErrorException e) {
			if(e.getStatusCode() == HttpStatus.NOT_FOUND) {
				return null;
			}
			throw e;
		}
		catch(Exception e) {
			throw e;
		}
	}

}
