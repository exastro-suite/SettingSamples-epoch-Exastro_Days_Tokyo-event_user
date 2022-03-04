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

import org.springframework.stereotype.Service;

import exastro.Exastro_Days_Tokyo.event_user.repository.vo.EventDetailVO;
import exastro.Exastro_Days_Tokyo.event_user.service.dto.EventDetailDto;

@Service
public class EventUserService extends BaseEventService implements EventService {
	
//	@Autowired
//	private ParticipantService participantService;
	
	public EventUserService() {
		
	}

	public EventDetailDto getEventDetail(int eventId) {

		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		EventDetailDto eventDetail = null;
		
		try {
			EventDetailVO edvo = repository.getEventDetail(eventId);
			if(edvo == null) {
				return null;
			}
			eventDetail = new EventDetailDto(edvo.getEventId(), edvo.getEventName(), edvo.getEventOverview(),
					edvo.getEventDate(), edvo.getEventVenue(), edvo.getSpeakerIDs());
		}
		catch(Exception e) {
			throw e;
		}
		
		return eventDetail;
	}

}
