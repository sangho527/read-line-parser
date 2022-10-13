package com.line.parser;

public interface Parser<T> { // <T> 제너릭을 사용해 다형성 적용해주기
    T parse(String str);
}
