package com.porotkin.string_analyzer.model;

public class WithStatementAnalyzer implements Analyzer {
    private enum EnumState {
        Start, Error, Final, // S1 = Start, S26 = Final
        S2,S3,S4,S5,S6,S7,S8,S9,S10,
        S11,S12,S13,S14,S15,S16,S17,S18,S19,S20,
        S21,S22,S23,S24,S25,S27,S28,S29,S30,
        S31,S32,S33,S34,S35,S36,S37,S38,S39,S40,
        S41,S42,S43,S44,S45,S46,S47,S48,S49, S50
    }
    private final AnalyzedError error; // the Error class
    private final String string;
    private EnumState state;

    public WithStatementAnalyzer(
            AnalyzedError error, // Dependency Injection for Error
            String string // Initial string to analyze
    ) {
        this.error = error;
        this.string = string.toUpperCase();
    }

    /**
     * Method that analyzes the given string
     * @return AnalyzedError instance or null if successful
     */
    @Override
    public AnalyzedError analyze() {
        int pos = 0; // current position
        state = EnumState.Start; // current state
        if (string.length() == 0) {
            error.setErrorPosition(pos);
            error.setErrorString("String cannot be empty!");
            state = EnumState.Error;
        }
        while ((state != EnumState.Error) && (state != EnumState.Final)) {
            if (pos >= string.length()) {
                state = EnumState.Error;
                error.setErrorPosition(pos);
                error.setErrorString("Index out of range");
            }
            else {
                char ch = string.charAt(pos);
                switch (state) {
                    // expecting letter "W"
                    case Start -> {
                        if (ch == 'W') {
                            state = EnumState.S2;
                        } else {
                            error.setErrorString("Letter 'W' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting letter "I"
                    case S2 -> {
                        if (ch == 'I') {
                            state = EnumState.S3;
                        } else {
                            error.setErrorString("Letter 'I' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting letter "T"
                    case S3 -> {
                        if (ch == 'T') {
                            state = EnumState.S4;
                        } else {
                            error.setErrorString("Letter 'T' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting letter "H"
                    case S4 -> {
                        if (ch == 'H') {
                            state = EnumState.S5;
                        } else {
                            error.setErrorString("Letter 'H' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting space
                    case S5 -> {
                        if (ch == ' ') {
                            state = EnumState.S6;
                        } else {
                            error.setErrorString("Space expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting space or _|A|..|Z|
                    case S6 -> {
                        if (ch == ' ') {
                            //state = EnumState.S6;
                        } else if (Character.isLetter(ch) || (ch == '_')) {
                            state = EnumState.S7;
                        } else {
                            error.setErrorString("Letter, underscore or space expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting letter or digit or , or . or space
                    case S7 -> {
                        if (ch == ',') {
                            state = EnumState.S6;
                        } else if (Character.isLetterOrDigit(ch) || (ch == '_')) {
                            //state = EnumState.S7;
                        } else if (ch == ' ') {
                            state = EnumState.S8;
                        } else if (ch == '.') {
                            state = EnumState.S9;
                        } else {
                            error.setErrorString("Letter or digit or ',' or '.' or space expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting ',' or space or 'D'
                    case S8 -> {
                        if (ch == ',') {
                            state = EnumState.S6;
                        } else if (ch == ' ') {
                            //state = EnumState.S8;
                        } else if (ch == 'D') {
                            state = EnumState.S10;
                        } else {
                            error.setErrorString("',' or space or 'D' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting underscore or letter
                    case S9 -> {
                        if ((Character.isLetter(ch)) || (ch == '_')) {
                            state = EnumState.S11;
                        } else {
                            error.setErrorString("Underscore or letter expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting 'O' character
                    case S10 -> {
                        if (ch == 'O') {
                            state = EnumState.S12;
                        } else {
                            error.setErrorString("Character 'O' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting ',' or digital or letter or underscore or space
                    case S11 -> {
                        if (ch == ',') {
                            state = EnumState.S6;
                        } else if ((Character.isLetterOrDigit(ch)) || (ch == '_')) {
                            //state = EnumState.S11;
                        } else if (ch == ' ') {
                            state = EnumState.S8;
                        } else {
                            error.setErrorString("Character 'O' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting space
                    case S12 -> {
                        if (ch == ' ') {
                            state = EnumState.S13;
                        } else {
                            error.setErrorString("Space expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting space, `'`, underscore or letter
                    case S13 -> {
                        if (ch == ' ') {
                            //state = EnumState.S13;
                        } else if (ch == '\'') {
                            state = EnumState.S14;
                        } else if ((Character.isLetter(ch)) || (ch == '_')) {
                            state = EnumState.S15;
                        } else {
                            error.setErrorString("Space, `'`, underscore or letter expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting underscore or letter
                    case S14 -> {
                        if ((Character.isLetter(ch)) || (ch == '_')) {
                            state = EnumState.S16;
                        } else {
                            error.setErrorString("Underscore or letter expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting underscore or letter or digit or space or '[' or ':'
                    case S15 -> {
                        if (ch == ' ') {
                            state = EnumState.S18;
                        } else if (ch == '[') {
                            state = EnumState.S17;
                        } else if (ch == ':') {
                            state = EnumState.S19;
                        } else if ((Character.isLetterOrDigit(ch)) || (ch == '_')) {
                            //state = EnumState.S15;
                        } else {
                            error.setErrorString("Underscore or letter or digit or space or '[' or ':' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting underscore or letter or '''
                    case S16 -> {
                        if (ch == '\'') {
                            state = EnumState.S20;
                        } else if ((Character.isLetter(ch)) || (ch == '_')) {
                            //state = EnumState.S16;
                        } else {
                            error.setErrorString("Underscore or letter or ''' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting space or digit or '-' or underscore or letter
                    case S17 -> {
                        if (ch == ' ') {
                            //state = EnumState.S17;
                        } else if ((Character.isLetter(ch)) || (ch == '_')) {
                            state = EnumState.S24;
                        } else if (ch == '-') {
                            state = EnumState.S23;
                        } else if (ch == '0') { // check for '0' before checking if digit
                            state = EnumState.S21;
                        } else if (Character.isDigit(ch)) {
                            state = EnumState.S22;
                        } else {
                            error.setErrorString("Space or digit or '-' or underscore or letter expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting space or '[' or ':'
                    case S18 -> {
                        if (ch == ' ') {
                            //state = EnumState.S18;
                        } else if (ch == '[') {
                            state = EnumState.S17;
                        } else if (ch == ':') {
                            state = EnumState.S19;
                        } else {
                            error.setErrorString("Space or '[' or ':' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting '='
                    case S19 -> {
                        if (ch == '=') {
                            state = EnumState.S25;
                        } else {
                            error.setErrorString("'=' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting space or ';'
                    case S20 -> {
                        if (ch == ' ') {
                            //state = EnumState.S20;
                        } else if (ch == ';') {
                            state = EnumState.Final;
                        } else {
                            error.setErrorString("Space or ';' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting space or ']'
                    case S21 -> {
                        if (ch == ' ') {
                            //state = EnumState.S21;
                        } else if (ch == ']') {
                            state = EnumState.S27;
                        } else {
                            error.setErrorString("Space or ']' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting digit or ']' or space
                    case S22 -> {
                        if (ch == ' ') {
                            state = EnumState.S21;
                        } else if (ch == ']') {
                            state = EnumState.S27;
                        } else if (Character.isDigit(ch)) {
                            //state = EnumState.S22;
                        } else {
                            error.setErrorString("Space or ']' or digit expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting '1'..'9'
                    case S23 -> {
                        if ((ch != '0') && (Character.isDigit(ch))) {
                            state = EnumState.S22;
                        } else {
                            error.setErrorString("'1'..'9' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting digit or letter or underscore or space or ']'
                    case S24 -> {
                        if ((ch == '_') || (Character.isLetterOrDigit(ch))) {
                            //state = EnumState.S24;
                        } else if (ch == ' ') {
                            state = EnumState.S21;
                        } else if (ch == ']') {
                            state = EnumState.S27;
                        } else {
                            error.setErrorString("Digit or letter or underscore or space or ']' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting space or underscore or letter or '-' or digit
                    case S25 -> {
                        if ((ch == '_') || (Character.isLetter(ch))) {
                            state = EnumState.S28;
                        } else if (ch == ' ') {
                            //state = EnumState.S25;
                        } else if (ch == '-') {
                            state = EnumState.S30;
                        } else if (ch == '0') { // check for '0' before checking if digit
                            state = EnumState.S29;
                        } else if (Character.isDigit(ch)) {
                            state = EnumState.S31;
                        } else {
                            error.setErrorString("Space or underscore or letter or '-' or digit expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting space or ':'
                    case S27 -> {
                        if (ch == ' ') {
                            //state = EnumState.S27;
                        } else if (ch == ':') {
                            state = EnumState.S19;
                        } else {
                            error.setErrorString("Space or ':' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting space or underscore or letter or digit or '[' or ';'
                    case S28 -> {
                        if (ch == ' ') {
                            state = EnumState.S33;
                        } else if (ch == '[') {
                            state = EnumState.S32;
                        } else if (ch == ';') {
                            state = EnumState.Final;
                        } else if ((ch == '_') || (Character.isLetterOrDigit(ch))) {
                            //state = EnumState.S28;
                        } else {
                            error.setErrorString("Space or underscore or letter or digit or '[' or ';' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting space or dot or ';'
                    case S29 -> {
                        if (ch == ' ') {
                            state = EnumState.S35;
                        } else if (ch == '.') {
                            state = EnumState.S34;
                        } else if (ch == ';') {
                            state = EnumState.Final;
                        } else {
                            error.setErrorString("Space or dot or ';' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting digit
                    case S30 -> {
                        if (ch == '0') { //check for '0' before checking if it's a digit
                            state = EnumState.S36;
                        } else if (Character.isDigit(ch)) {
                            state = EnumState.S31;
                        } else {
                            error.setErrorString("Digit expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting digit or dot or ';'
                    case S31 -> {
                        if (Character.isDigit(ch)) {
                            //state = EnumState.S31;
                        } else if (ch == '.') {
                            state = EnumState.S34;
                        } else if (ch == ';') {
                            state = EnumState.Final;
                        } else {
                            error.setErrorString("Digit or dot or ';' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting digit or space or '-' or underscore or letter
                    case S32 -> {
                        if (ch == '0') { // check for '0' before checking if it's a digit
                            state = EnumState.S40;
                        } else if (Character.isDigit(ch)) {
                            state = EnumState.S39;
                        } else if (ch == '-') {
                            state = EnumState.S37;
                        } else if (ch == ' ') {
                            //state = EnumState.S32;
                        } else if ((ch == '_') || (Character.isLetter(ch))) {
                            state = EnumState.S38;
                        } else {
                            error.setErrorString("Digit or space or '-' or underscore or letter expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting '[' or ';' or '/'|'*'|'+'|'-' or space or 'M' or 'D'
                    case S33 -> {
                        if (ch == '[') {
                            state = EnumState.S32;
                        } else if (ch == ';') {
                            state = EnumState.Final;
                        } else if (ch == ' ') {
                            //state = EnumState.S33;
                        } else if (ch == 'M') {
                            state = EnumState.S41;
                        } else if (ch == 'D') {
                            state = EnumState.S43;
                        } else if ((ch == '/') || (ch == '*') || (ch == '+') || (ch == '-')) {
                            state = EnumState.S42;
                        } else {
                            error.setErrorString("'[' or ';' or '/'|'*'|'+'|'-' or space or 'M' or 'D' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting digit
                    case S34 -> {
                        if (Character.isDigit(ch)) {
                            state = EnumState.S44;
                        } else {
                            error.setErrorString("Digit expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting ';' or '/'|'*'|'+'|'-' or space or 'M' or 'D'
                    case S35 -> {
                        if (ch == ';') {
                            state = EnumState.Final;
                        } else if (ch == 'M') {
                            state = EnumState.S41;
                        } else if (ch == 'D') {
                            state = EnumState.S43;
                        } else if (ch == ' ') {
                            //state = EnumState.S35;
                        } else if ((ch == '/') || (ch == '*') || (ch == '+') || (ch == '-')) {
                            state = EnumState.S42;
                        } else {
                            error.setErrorString("';' or '/'|'*'|'+'|'-' or space or 'M' or 'D' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting '.'
                    case S36 -> {
                        if (ch == '.') {
                            state = EnumState.S34;
                        } else {
                            error.setErrorString("'.' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting '1'..'9'
                    case S37 -> {
                        if ((ch != '0') && (Character.isDigit(ch))) {
                            state = EnumState.S39;
                        } else {
                            error.setErrorString("'1'..'9' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting Digit or letter or underscore or space or ']'
                    case S38 -> {
                        if ((ch == '_') || (Character.isLetterOrDigit(ch))) {
                            //state = EnumState.S38;
                        } else if (ch == ' ') {
                            state = EnumState.S40;
                        } else if (ch == ']') {
                            state = EnumState.S45;
                        } else {
                            error.setErrorString("Digit or letter or underscore or space or ']' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting Digit or space or ']'
                    case S39 -> {
                        if (Character.isDigit(ch)) {
                            //state = EnumState.S39;
                        } else if (ch == ' ') {
                            state = EnumState.S40;
                        } else if (ch == ']') {
                            state = EnumState.S45;
                        } else {
                            error.setErrorString("Digit or space or ']' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting Space or ']'
                    case S40 -> {
                        if (ch == ' ') {
                            //state = EnumState.S40;
                        } else if (ch == ']') {
                            state = EnumState.S45;
                        } else {
                            error.setErrorString("Space or ']' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting 'O'
                    case S41 -> {
                        if (ch == 'O') {
                            state = EnumState.S46;
                        } else {
                            error.setErrorString("'O' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting Space
                    case S42 -> {
                        if (ch == ' ') {
                            state = EnumState.S25;
                        } else {
                            error.setErrorString("Space expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting 'I'
                    case S43 -> {
                        if (ch == 'I') {
                            state = EnumState.S47;
                        } else {
                            error.setErrorString("'I' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting ';' or 'E' or space or digit
                    case S44 -> {
                        if (ch == ';') {
                            state = EnumState.Final;
                        } else if (ch == 'E') {
                            state = EnumState.S48;
                        } else if (ch == ' ') {
                            state = EnumState.S35;
                        } else if (Character.isDigit(ch)) {
                            //state = EnumState.S44;
                        } else {
                            error.setErrorString("';' or 'E' or space or digit expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting ';' or space
                    case S45 -> {
                        if (ch == ';') {
                            state = EnumState.Final;
                        } else if (ch == ' ') {
                            state = EnumState.S35;
                        } else {
                            error.setErrorString("';' or space expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting 'D'
                    case S46 -> {
                        if (ch == 'D') {
                            state = EnumState.S42;
                        } else {
                            error.setErrorString("'D' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting 'V'
                    case S47 -> {
                        if (ch == 'V') {
                            state = EnumState.S42;
                        } else {
                            error.setErrorString("'V' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting '-' or digit
                    case S48 -> {
                        if (ch == '0') {
                            state = EnumState.S45;
                        } else if (Character.isDigit(ch)) {
                            state = EnumState.S50;
                        } else if (ch == '-') {
                            state = EnumState.S49;
                        } else {
                            error.setErrorString("'-' or digit expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting '1'..'9'
                    case S49 -> {
                        if ((ch != '0') && (Character.isDigit(ch))) {
                            state = EnumState.S50;
                        } else {
                            error.setErrorString("'1'..'9' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }
                    // expecting Space or digit or ';'
                    case S50 -> {
                        if (Character.isDigit(ch)) {
                            //state = EnumState.S50;
                        } else if (ch == ' ') {
                            state = EnumState.S35;
                        } else if (ch == ';') {
                            state = EnumState.Final;
                        } else {
                            error.setErrorString("Space or digit or ';' expected");
                            error.setErrorPosition(pos);
                            state = EnumState.Error;
                        }
                    }

                    default -> {
                        state = EnumState.Error;
                        error.setErrorPosition(pos);
                        error.setErrorString("Unexpected error");
                    }
                }
                pos++;
            }
        }
        return (state == EnumState.Final) ? null : error;
    }
}
