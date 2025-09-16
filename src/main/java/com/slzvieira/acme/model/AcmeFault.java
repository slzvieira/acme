package com.slzvieira.acme.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(name = "AcmeFault")
@Data
@Builder
public class AcmeFault {

    private Integer code;
    private String type;
    private List<String> messages;

    public AcmeFault addMessage(String message) {
        if (Objects.isNull(messages)) {
            messages = new ArrayList<>();
        }
        messages.add(message);
        return this;
    }
}
