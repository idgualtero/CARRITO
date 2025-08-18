package com.usabana.models.asynchronous;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.usabana.models.synchronous.AuthorizationRequest;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class CloudEvent {

    private String specversion;
    private String type;
    private String source;
    private String subject;
    private String id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "America/Bogota")
    private Date time;
    private String datacontenttype;
    private List<AuthorizationRequest> data;

}