package dev.tomle.ims.model.dto;

import java.time.Instant;

public abstract class BaseEntityDTO {
	
	public long id;
	public Instant createdDate;
	public String createdBy;
	public Instant lastModDate;
	public String lastModBy;
}
