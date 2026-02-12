package dev.tomle.ims.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected long id;
	
	@CreatedDate
	@Column(name="createddate", columnDefinition = "timestamptz")
	private Instant createdDate;
	@CreatedBy
	@Column(name="createdby")
	private String createdBy;

	@LastModifiedDate
	@Column(name="lastmoddate", columnDefinition = "timestamptz")
	private Instant lastModDate;
	@LastModifiedBy
	@Column(name="lastmodby")
	private String lastModBy;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Instant getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Instant createDate) {
		this.createdDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Instant getLastModDate() {
		return lastModDate;
	}

	public void setLastModDate(Instant modifyDate) {
		this.lastModDate = modifyDate;
	}

	public String getLastModBy() {
		return lastModBy;
	}

	public void setLastModBy(String modifiedBy) {
		this.lastModBy = modifiedBy;
	}
}
