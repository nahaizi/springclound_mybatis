package com.lili.es.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BaseLogObj implements Serializable {

        @JsonProperty("type")
        private String type;
        @JsonProperty("line_id")
        private String lineId;
        @JsonProperty("play_name")
        private String playName;
        @JsonProperty("speech_number")
        private String speechNumber;
        @JsonProperty("line_number")
        private String lineNumber;
        @JsonProperty("speaker")
        private String speaker;
        @JsonProperty("text_entry")
        private String textEntry;
}
