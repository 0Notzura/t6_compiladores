# t6_compiladores
Linguagem para organização de arquivos:

os tokens são os nomes das funções e alguns auxiliares:

auxiliares:

COMENTARIO: '{' ~('\n'|'\r'|'{'|'}')* '}' '\n'?'\r'? {skip();}; // Comentário


fragment
ESC_SEQ	:'\\\'';

WS: (' ' | '\n' | '\r' | '\t') {skip();}; // Espaço em branco

VIR:',';

VAR:('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

COMENTARIO_NAO_FECHADO: '{' ~('\n'|'\r'|'{'|'}')* '\n'? '\r'?; // Erro de comentário não fechado

CADEIA_LITERAL_NAO_FECHADA: '\'' ( ESC_SEQ | ~('\''|'\\') )* '\n'? '\r'?; // Erro de cadeia de caracteres não fechada

ERRO: ~('a');

funções:

lerEntrada:recebe os dados de entrada do usuario e coloca em uma variavel

lerArquivo:le o texto de um arquivo e o colca em uma variavel

escreverNoArquivo:escreve os dados em um arquivo

mostrarArquivo:pega o texto do arquivo e mostra no console

criarArquivo:cria um arquivo com texto dentro

apagarArquivo:apaga um arquivo

unirArquivo:junta o texto de dois arquivos em um terceiro

criarPasta:cria uma pasta

apagarPasta:apaga uma pasta

incluirPasta:muda a segunda pasta para dentro da primeira

Todas as funções tem () e dentro deles são passados os caminhos para o arquivo ou pasta, e o texto 
