package br.ufscar.dc.compiladores.analisador.sintatico;

import java.io.PrintWriter;
import java.util.BitSet;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;


public class TratamentoDeErro implements ANTLRErrorListener {
    //Objeto do tipo Printwriter é criado
    PrintWriter pw;
    //flag para marcar se encontrou o erro ou não é criada e setada para não encontrou no começo
    static boolean encontrouErro = false;

    public TratamentoDeErro(PrintWriter pw) {
        this.pw = pw;
    }

    @Override
    public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact,
            BitSet ambigAlts, ATNConfigSet configs) {
        // Não será necessário para o T2, pode deixar vazio
    }

    @Override
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
            BitSet conflictingAlts, ATNConfigSet configs) {
        // Não será necessário para o T2, pode deixar vazio
    }

    @Override
    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction,
            ATNConfigSet configs) {
        // Não será necessário para o T2, pode deixar vazio
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
            String msg, RecognitionException e) {

        // Token único é usado
        Token t = (Token) offendingSymbol;
        String text = t.getText();

        // conversão do texto que vem do EOF para tirar <>
        if(text == "<EOF>")
            text = "EOF";
        String tipo = AnalisadorLexer.VOCABULARY.getDisplayName(t.getType()); // Token vira do tipo string
        if(!encontrouErro){
            // nos tratamentos de erro, caso um erro exisa o programa encerra(break)
            // trata de erros que nao sao nem de cadeia nao fechada nem de comentarios
            //nao fechados
            if(tipo == "ERRO"){
                pw.println("Linha " + t.getLine() + ": " + text + " - simbolo nao identificado");
                TratamentoDeErro.encontrouErro = true;
            }
            // trata de erros de cadeias nao fechadas. Aspas não fechadas    
            else if(tipo == "CADEIA_LITERAL_NAO_FECHADA"){
                pw.println("Linha " + t.getLine() + ": " + "cadeia literal nao fechada");
                TratamentoDeErro.encontrouErro = true;
            }
            // trata de erros de cadeias nao fechadas. Aspas não fechadas    
            else if(tipo == "COMENTARIO_NAO_FECHADO"){
                pw.println("Linha " + t.getLine() + ": " + "comentario nao fechado");
                TratamentoDeErro.encontrouErro = true;
            }
            // trata de erros de comentarios nao fechados
            //chegando aqui, quer dizer que houve um erro sintatico, não lexico como os anteriores
            else{                
                pw.println("linha " + line + ": erro sintatico proximo a " + text);
                TratamentoDeErro.encontrouErro = true;
            }
        }
    }
}
