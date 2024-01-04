package attendanceManagementSystem.ams.attendanceSheet;//package com.example.attendance.AttendanceSheet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


import java.io.IOException;
import java.util.HashMap;

@Converter
public class JsonNodeConverter implements AttributeConverter<JsonNode,String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(JsonNode jsonNode) {
        try {
            return objectMapper.writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            // Handle exception or log it
            throw new RuntimeException("Error converting JsonNode to String", e);
        }
    }

    @Override
    public JsonNode convertToEntityAttribute(String jsonString) {
        try {
            // Convert HashMap to JsonNode
            HashMap<String, Object> hashMap = objectMapper.readValue(jsonString, HashMap.class);
            return objectMapper.valueToTree(hashMap);
        } catch (IOException e) {
            // Handle exception or log it
            throw new RuntimeException("Error converting String to JsonNode", e);
        }
    }
}
