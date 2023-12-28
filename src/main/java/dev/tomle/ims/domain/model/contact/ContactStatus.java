package dev.tomle.ims.domain.model.contact;

import dev.tomle.ims.domain.shared.BaseEntity;
import jakarta.persistence.Entity;

@Entity
public class ContactStatus extends BaseEntity {

	private String status;

	public enum StatusEnum {
		ACTIVE(1),
		INACTIVE(2);

		public final int id;
		private StatusEnum(int id) {
			this.id = id;
		}
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
