package MichaelOdio;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.IOException;

public class ParseName {

    private Tokenizer tokenizer = null;
    private NameFinderME nameFinder = null;
    private double highestNameProb = 0.0;
    private String name = null;

    public ParseName(){
        try {
            tokenizer = new TokenizerME(new TokenizerModel(ClassLoader.getSystemResourceAsStream("en-token.bin")));
            nameFinder = new NameFinderME(new TokenNameFinderModel(ClassLoader.getSystemResourceAsStream("en-ner-person.bin")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String validateName(String line) {

        String tokens[] = tokenizer.tokenize(line);
        Span nameSpans[] = nameFinder.find(tokens);
        if (nameSpans.length > 0) {
            double nameProb = nameFinder.probs(nameSpans)[0];
            if (nameProb > highestNameProb) {
                highestNameProb = nameProb;
                name = Span.spansToStrings(nameSpans, tokens)[0];
            }
        }
        return name;
    }


}
