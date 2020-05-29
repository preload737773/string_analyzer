package com.porotkin.string_analyzer.model;

public interface AnalyzedError {
    String toString();
    void setErrorString(String errorString);
    void setErrorPosition(int errorPosition);
}
