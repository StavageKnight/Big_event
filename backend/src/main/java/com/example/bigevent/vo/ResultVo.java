package com.example.bigevent.vo;

import com.example.bigevent.exception.Code;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo {
    private int code;
    private String message;
    private Object data;

    private static final ResultVo EMPTY = ResultVo.builder().code(200).build();

    public static ResultVo ok(int i, String s) {
        return ResultVo.builder()
                .code(i)
                .message(s)
                .build();
    }


    public static ResultVo success(Object data, String message) {
        return ResultVo.builder()
                .code(200)
                .message(message)
                .data(data)
                .build();
    }

    public static ResultVo error(Code code) {
        return ResultVo.builder()
                .code(code.getCode())
                .message(code.getMessage())
                .build();
    }

    public static ResultVo error(int code, String message) {
        return ResultVo.builder()
                .code(code)
                .message(message)
                .build();
    }

}