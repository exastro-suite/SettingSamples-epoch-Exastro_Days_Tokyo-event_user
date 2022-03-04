package exastro.Exastro_Days_Tokyo.event_user.service.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeminarDto {

//	セミナーID	
	private int seminarId;

//	セミナー名
	private String seminarName;
	
//	ブロックID
	private int blockId;

//	ブロック名	
	private String blockName;

//	開催日時(開始)
	private Date startDatetime;
	
//	登壇者ID
	private Integer speakerId;
}
