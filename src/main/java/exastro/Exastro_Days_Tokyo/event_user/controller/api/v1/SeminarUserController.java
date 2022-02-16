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

package exastro.Exastro_Days_Tokyo.event_user.controller.api.v1;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exastro.Exastro_Days_Tokyo.event_user.controller.api.v1.form.SeminarDetailForm;
import exastro.Exastro_Days_Tokyo.event_user.service.dto.ParticipantDto;
import exastro.Exastro_Days_Tokyo.event_user.service.dto.SeminarDetailDto;

@RestController
@RequestMapping("/api/v1/seminar")
public class SeminarUserController extends BaseSeminarController {
	
	public SeminarUserController() {
	}
	
	@GetMapping("/{seminarId}")
	public SeminarDetailForm seminarDetail(@PathVariable(value = "seminarId") @Validated int seminarId,
			@RequestParam (name = "user_id", defaultValue = "") String userId, @RequestParam (name = "kind_of_sso", defaultValue = "") String kindOfSso) {
		
		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		SeminarDetailForm seminarDetail = null;
		
		try {
			SeminarDetailDto e = service.getSeminarDetail(seminarId);
			seminarDetail = new SeminarDetailForm(e.getSeminarId(), e.getSeminarName(), e.getBlockId(), e.getBlockName(),
					 e.getStartDatetime(), e.getSpeakerId(), e.getSeminarOverview(), e.getCapacity());
			
			// 申込済みのセミナーかどうか判定
			List<ParticipantDto> participantList = participantService.getParticipant(userId, kindOfSso);
			
			for (ParticipantDto participant : participantList) {
				if (String.valueOf(seminarDetail.getSeminarId()).contains(String.valueOf(participant.getSeminarId()))){
					seminarDetail.setParticipated(true);
				}
			}
			
			// セミナーが定員上限に達しているか判定
			Integer capacity = e.getCapacity();
			Integer countParticipant = participantService.countParticipant(seminarId);
			
			if (capacity == null) {
				seminarDetail.setCapacityOver(false);
			}else if (countParticipant >= capacity) {
				seminarDetail.setCapacityOver(true);
			}
		}
		catch(Exception e) {
			logger.debug(e.getMessage(), e);
			throw e;
		}
		
		return seminarDetail;
	}
}
