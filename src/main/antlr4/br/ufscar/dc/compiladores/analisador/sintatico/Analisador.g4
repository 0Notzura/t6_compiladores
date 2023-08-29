grammar Analisador;


programa: comando+  ;

comando: comandoApagarA | comandoApagarP | comandoAtribuicao | comandoCriarA | comandoCriarP | comandoEntrada
        | comandoEscreverArq | comandoLer | comandoMostrar | comandoUnirA | comandoUnirA | comandoIncluirP;

comandoAtribuicao: 'ATRIBUIR' VAR '=' CADEIA;

comandoLer: LER_ARQUIVO APAR (CADEIA | VAR) VIR VAR FPAR;

comandoMostrar: MOSTRAR_ARQUIVO APAR (CADEIA | VAR) FPAR;

comandoEscreverArq: ESCREVER_ARQUIVO APAR (CADEIA | VAR) VIR (CADEIA | VAR) FPAR;

comandoEntrada: LER_ENTRADA APAR VAR FPAR;

comandoApagarA: APAGAR_ARQUIVO APAR (CADEIA | VAR) FPAR;

comandoCriarA: CRIAR_ARQUIVO APAR (CADEIA | VAR) VIR (CADEIA | VAR) FPAR;

comandoUnirA: UNIR_ARQUIVO APAR (CADEIA | VAR) VIR (CADEIA | VAR) FPAR;

comandoCriarP: CRIAR_PASTA APAR (CADEIA | VAR) FPAR;

comandoApagarP: APAGAR_PASTA APAR (CADEIA | VAR) FPAR;

comandoIncluirP: INCLUIR_PASTA APAR (CADEIA | VAR) VIR (CADEIA | VAR) FPAR;


CADEIA:	'\'' ( ESC_SEQ | ~('\''|'\\') )* '\'';


APAR:'(';
FPAR:')';

INCLUIR_PASTA: 'incluirPasta';

APAGAR_PASTA: 'apagarPasta';

CRIAR_PASTA: 'criarPasta';

UNIR_ARQUIVO: 'unirarquivo';

APAGAR_ARQUIVO: 'apagarArquivo';

CRIAR_ARQUIVO: 'criarArquivo';

MOSTRAR_ARQUIVO: 'mostrarArquivo';

ESCREVER_ARQUIVO: 'escreverNoArquivo';

LER_ENTRADA: 'lerEntrada';

LER_ARQUIVO: 'lerArquivo';

COMENTARIO: '{' ~('\n'|'\r'|'{'|'}')* '}' '\n'?'\r'? {skip();}; // Comentário


fragment
ESC_SEQ	:'\\\'';

WS: (' ' | '\n' | '\r' | '\t') {skip();}; // Espaço em branco

VIR:',';

VAR:('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

COMENTARIO_NAO_FECHADO: '{' ~('\n'|'\r'|'{'|'}')* '\n'? '\r'?; // Erro de comentário não fechado

CADEIA_LITERAL_NAO_FECHADA: '\'' ( ESC_SEQ | ~('\''|'\\') )* '\n'? '\r'?; // Erro de cadeia de caracteres não fechada

ERRO: ~('a');



