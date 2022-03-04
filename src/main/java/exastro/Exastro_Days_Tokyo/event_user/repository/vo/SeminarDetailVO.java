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

package exastro.Exastro_Days_Tokyo.event_user.repository.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SeminarDetailVO {
	
//	セミナーID	
	private int seminarId;

//	セミナー名
	private String seminarName;
	
//	ブロックID
	private int blockId;

//	ブロック名	
	private String blockName;

//	開催日時(開始)
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date startDatetime;
	
//	登壇者ID
	private Integer speakerId;
	
//	セミナー概要
	private String seminarOverview;

//	定員
	private Integer capacity;
	
}
