package dev.tomle.ims.model.dto;

import java.io.Serial;
import java.io.Serializable;

public class FileUploadUrlDTO extends BaseEntityDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    
    public String url;
    
    public FileUploadUrlDTO(String url) {
    	this.url = url;
    }
}
