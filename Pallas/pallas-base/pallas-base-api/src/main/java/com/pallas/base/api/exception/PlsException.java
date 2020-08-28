package com.pallas.base.api.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: jax
 * @time: 2020/8/14 10:34
 * @desc: 通用异常类
 */
@Data
@AllArgsConstructor
public class PlsException extends RuntimeException {

  private static final int DEFAULT_CODE = 101;
  private int code;

  public PlsException(String msg) {
    super(msg);
    this.code = DEFAULT_CODE;
  }

  public PlsException(Throwable cause) {
    super(cause);
    this.code = DEFAULT_CODE;
  }

  public PlsException(String msg, Throwable cause) {
    super(msg, cause);
    this.code = DEFAULT_CODE;
  }

  @Override
  public String toString() {
    return new StringBuilder("{'code':").append(code)
        .append(",'message':'").append(this.getLocalizedMessage())
        .append("'}").toString();
  }
}