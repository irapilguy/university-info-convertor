package com.springproject.universityinfoconvertor;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Transliterator {

    public String getTranslatedString(String wordToConvert) {
        String result = "";
        char prevChar = 'a';
        for (char symbol : wordToConvert.toCharArray()) {
            String convertedSymbol = "";
            boolean isUpperCase = Character.isUpperCase(symbol);
            symbol = Character.toLowerCase(symbol);

            switch(symbol){
                case 'а': convertedSymbol = "a"; break;
                case 'б': convertedSymbol = "b"; break;
                case 'в': convertedSymbol = "v"; break;
                case 'г': convertedSymbol = "h"; break;
                case 'ґ': convertedSymbol = "g"; break;
                case 'д': convertedSymbol = "d"; break;
                case 'е': convertedSymbol = "e"; break;
                case 'ж': convertedSymbol = "zh"; break;
                case 'з': convertedSymbol = "z"; break;
                case 'и': convertedSymbol = "y"; break;
                case 'і': convertedSymbol = "i"; break;
                case 'к': convertedSymbol = "k"; break;
                case 'л': convertedSymbol = "l"; break;
                case 'м': convertedSymbol = "m"; break;
                case 'н': convertedSymbol = "n"; break;
                case 'о': convertedSymbol = "o"; break;
                case 'п': convertedSymbol = "p"; break;
                case 'р': convertedSymbol = "r"; break;
                case 'с': convertedSymbol = "s"; break;
                case 'т': convertedSymbol = "t"; break;
                case 'у': convertedSymbol = "u"; break;
                case 'ф': convertedSymbol = "f"; break;
                case 'х': convertedSymbol = "kh"; break;
                case 'ц': convertedSymbol = "ts"; break;
                case 'ч': convertedSymbol = "ch"; break;
                case 'ш': convertedSymbol = "sh"; break;
                case 'щ': convertedSymbol = "shch"; break;
                case 'є': convertedSymbol = isUpperCase ? "ye" : "ie"; break;
                case 'ї': convertedSymbol = isUpperCase ? "yi" : "i"; break;
                case 'й': convertedSymbol = isUpperCase ? "y" : "i"; break;
                case 'ю': convertedSymbol = isUpperCase ? "yu" : "iu"; break;
                case 'я': convertedSymbol = isUpperCase ? "ya" : "ia"; break;
                case 'ь': break;
                case '’': break;
                default: convertedSymbol = Character.toString(symbol);
            }

            if(symbol == 'г' && Character.toLowerCase(prevChar) == 'з'){
                convertedSymbol = "gh";
            }
            prevChar = symbol;

            if(isUpperCase) {
                convertedSymbol = Character.toUpperCase(convertedSymbol.charAt(0)) +convertedSymbol.substring(1);
            }

            result += convertedSymbol;
        }

        return result;
    }
}
