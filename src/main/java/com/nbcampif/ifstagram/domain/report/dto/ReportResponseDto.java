package com.nbcampif.ifstagram.domain.report.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class ReportResponseDto {
    private String result;

    public ReportResponseDto(String result){
        this.result = result;
    }
}
