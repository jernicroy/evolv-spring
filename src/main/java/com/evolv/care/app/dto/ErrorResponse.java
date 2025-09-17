package com.evolv.care.app.dto;

import lombok.Builder;

@Builder
public record ErrorResponse (int status, String errorCode, String message){

}