package com.porotkin.string_analyzer.model;

public class AnalyzerFactory {
    public static Analyzer createAnalyzer(String name, String errorType, String initialString) {
        switch (name) {
            case "with" -> {
                return new WithStatementAnalyzer(AnalyzedErrorFactory.createAnalyzedError("simpleError"),
                        initialString);
            }
            default -> {
                return new WithStatementAnalyzer(AnalyzedErrorFactory.createAnalyzedError("simpleError"),
                        initialString);
            }
        }
    }
}
