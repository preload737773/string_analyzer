package com.porotkin.string_analyzer;

import com.porotkin.string_analyzer.model.AnalyzedError;
import com.porotkin.string_analyzer.model.Analyzer;
import com.porotkin.string_analyzer.model.AnalyzerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyRestController {
    @PostMapping(value="/analyze")
    public String analyze(@RequestBody String string) {
        Analyzer analyzer = AnalyzerFactory.createAnalyzer("with", "simpleError", string);
        AnalyzedError error = analyzer.analyze();
        return (error != null) ? error.toString() : "WITH statement was typed correctly.";
    }
}
