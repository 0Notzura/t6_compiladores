package br.ufscar.dc.compiladores.analisador.sintatico;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

public class Principal {
    public static void main(String args[]) {
        try (PrintWriter pw = new PrintWriter(new File(args[1]))) {
            // inicia o fluxo de caracteres do arquivo e inicia o analisador lexico com ele
            CharStream cs = CharStreams.fromFileName(args[0]);
            AnalisadorLexer lexer = new AnalisadorLexer(cs);

            // a stream de tokens, o analisador parser e um objeto para tratar o erro são iniciados
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            AnalisadorParser parser = new AnalisadorParser(tokens);
            TratamentoDeErro mcel = new TratamentoDeErro(pw);

            // o Error Listener default do antlr é removido e adicionamos o nosso que
            // estamos criando
            parser.removeErrorListeners();
            parser.addErrorListener(mcel);

            // o programa do parser é executado
            parser.programa();
            pw.println("Compilacao finalizada");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
