package com.porotkin.string_analyzer.model;

public class SimpleError implements AnalyzedError {
    private int errorPosition = 0;
    private String errorString = "";

    public SimpleError() {}

    public String getErrorString() {
        return errorString;
    }
    public int getErrorPosition() {
        return errorPosition;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    public void setErrorPosition(int errorPosition) {
        this.errorPosition = errorPosition;
    }

    @Override
    public String toString() {
        return String.format("Error at position %d: %s", errorPosition, errorString);
    }
}
