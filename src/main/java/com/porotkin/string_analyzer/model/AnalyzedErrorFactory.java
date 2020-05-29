package com.porotkin.string_analyzer.model;

public class AnalyzedErrorFactory {
    public static AnalyzedError createAnalyzedError(String errorType) {
        switch (errorType) {
            case "simpleError" -> {
                return new SimpleError();
            }
            default -> {
                return new SimpleError();
            }
        }
    }
}
