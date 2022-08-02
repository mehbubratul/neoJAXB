package com.mehbub.neoJAXB.response;

import com.mehbub.neoJAXB.dto.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {
    private String message;
    private int responseStatus;
    private List<User> data;
}
