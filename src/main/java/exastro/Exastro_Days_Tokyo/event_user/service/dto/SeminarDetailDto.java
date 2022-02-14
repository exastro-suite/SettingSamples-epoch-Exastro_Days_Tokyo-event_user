package exastro.Exastro_Days_Tokyo.event_user.service.dto;

import java.util.Date;

import lombok.Data;

@Data
public class SeminarDetailDto {
	
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
	
//	セミナー概要
	private String seminarOverview;
	
//	定員
	private Integer capacity;
	
	public SeminarDetailDto(int seminarId, String seminarName, int blockId, String blockName, Date startDatetime ,
			Integer speakerId, String seminarOverview, Integer capacity){
		this.seminarId = seminarId;
		this.seminarName = seminarName;
		this.blockId = blockId;
		this.blockName = blockName;
		this.startDatetime = startDatetime;
		this.speakerId = speakerId;
		this.seminarOverview = seminarOverview;
		this.capacity = capacity;
	}
	
}
