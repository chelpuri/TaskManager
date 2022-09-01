package com.practice.new_project.common;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
@Getter
@Setter
public class ErrorResponseDTO {
    @NonNull
   private String message;
}
