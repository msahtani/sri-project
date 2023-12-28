package ma.ensa.sriapi.indexation;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class IndexationProcess {

    public static List<String> processText(String text) throws IOException {

        List<String> tokens = tokenizeText(text);

        List<String> filteredTokens = removeStopwords(tokens);


        return tokens;
    }

    private static List<String> tokenizeText(String text) throws IOException {

        CharArraySet stopwords = FrenchAnalyzer.getDefaultStopSet();

        Analyzer analyzer = new Analyzer() {
            @Override
            protected TokenStreamComponents createComponents(String fieldName) {
                StandardTokenizer tokenizer = new StandardTokenizer();
                TokenStream tokenStream = new LowerCaseFilter(tokenizer);
                tokenStream = new StopFilter(tokenStream, stopwords);
                return new TokenStreamComponents(tokenizer, tokenStream);
            }
        };

        List<String> tokens = new ArrayList<>();

        try (TokenStream tokenStream = analyzer.tokenStream(null, new StringReader(text))) {
            tokenStream.reset();

            while (tokenStream.incrementToken()) {
                CharTermAttribute termAttribute = tokenStream.getAttribute(CharTermAttribute.class);
                tokens.add(termAttribute.toString());
            }

            tokenStream.end();
        }

        analyzer.close();

        return tokens;
    }

    private static List<String> removeStopwords(List<String> tokens) throws IOException {
        // Suppression des mots vides
        CharArraySet frenchStopWords = FrenchAnalyzer.getDefaultStopSet();
        List<String> filteredTokens = new ArrayList<>();

        for (String token : tokens) {
            if (!frenchStopWords.contains(token)) {
                filteredTokens.add(token);
            }
        }

        return filteredTokens;
    }
}
