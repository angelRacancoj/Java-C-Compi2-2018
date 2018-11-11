package backEnd.analisisLexSintax;

import java_cup.runtime.*;
import backEnd.analisisLexSintax.sym;
import backEnd.semantic.semanticManager;
import backEnd.langConstants.languageConstants;

%%

%public
%class Lexer
%cup
%cupdebug
%line
%column

LineTerminator = \r|\n|\r\n|null
WhiteSpace     = {LineTerminator} | [ \t\f]

entero = [:digit:]+
decimal = {entero} [.][:digit:]+
cadena = "\"" [^*] ~"\""
identificador = ([_]|[:jletter:])([:jletterdigit:] | [_])*
boolID = "true" | "false"



%{
	private semanticManager semanticM;
	languageConstants lanC = new languageConstants();

	public Lexer(java.io.Reader in, semanticManager semanticM){
		this.zzReader = in;
		this.semanticM = semanticM;
	}
  
  	private Symbol symbol(int type) {
    	return new Symbol(type, yyline+1, yycolumn+1);
  	}

  	private Symbol symbol(int type, Object value) {
    	return new Symbol(type, yyline+1, yycolumn+1, value);
  	}

  	private void error(String message) {
  		String errorText = ("Error en linea line "+(yyline+1)+", columna "+(yycolumn+1)+" : "+message);
    	System.out.println(errorText);
    	semanticM.errorAndPlace(lanC.AN_LEXICO, errorText);
		semanticM.setErrorFound(true);
  	}

	private void imprimirToken(String textSalida){
		System.out.println("Leido: >> " + textSalida + " <<");
	}
%}

%%

<YYINITIAL>{
	
	"int" 				{imprimirToken(yytext()); return symbol(sym.NamInt, 		yytext());}
	"float"				{imprimirToken(yytext()); return symbol(sym.NamFloat, 		yytext());}
	"boolean"			{imprimirToken(yytext()); return symbol(sym.NamBoolean, 	yytext());}
	"string"			{imprimirToken(yytext()); return symbol(sym.NamString, 		yytext());}
	"main" 				{imprimirToken(yytext()); return symbol(sym.NamMain, 		yytext());}
	"if" 				{imprimirToken(yytext()); return symbol(sym.NamIf, 			yytext());}
	"while"				{imprimirToken(yytext()); return symbol(sym.NamWhile, 		yytext());}
	"else" 				{imprimirToken(yytext()); return symbol(sym.NamElse, 		yytext());}
	"printn" 			{imprimirToken(yytext()); return symbol(sym.PrintN, 		yytext());}
	"prints" 			{imprimirToken(yytext()); return symbol(sym.PrintS, 		yytext());}
	"break" 			{imprimirToken(yytext()); return symbol(sym.BreakCom,		yytext());}
	"+" 				{imprimirToken(yytext()); return symbol(sym.Mas, 			yytext());}
	"-" 				{imprimirToken(yytext()); return symbol(sym.Menos,	 		yytext());}
	"*" 				{imprimirToken(yytext()); return symbol(sym.Por, 			yytext());}
	"/" 				{imprimirToken(yytext()); return symbol(sym.Div, 			yytext());}
	">" 				{imprimirToken(yytext()); return symbol(sym.Mayor, 			yytext());}
	"<" 				{imprimirToken(yytext()); return symbol(sym.Menor, 			yytext());}
	"==" 				{imprimirToken(yytext()); return symbol(sym.Igual, 			yytext());}
	"=!" 				{imprimirToken(yytext()); return symbol(sym.Diferente, 		yytext());}
	"||" 				{imprimirToken(yytext()); return symbol(sym.Or, 			yytext());}
	"&&" 				{imprimirToken(yytext()); return symbol(sym.And, 			yytext());}
	"=" 				{imprimirToken(yytext()); return symbol(sym.Asignacion, 	yytext());}
	";" 				{imprimirToken(yytext()); return symbol(sym.EndCom, 		yytext());}
	"{" 				{imprimirToken(yytext()); return symbol(sym.CorAbierto, 	yytext());}
	"}" 				{imprimirToken(yytext()); return symbol(sym.CorCerrado, 	yytext());}
	"(" 				{imprimirToken(yytext()); return symbol(sym.ParAbierto, 	yytext());}
	")" 				{imprimirToken(yytext()); return symbol(sym.ParCerrado, 	yytext());}
	"," 				{imprimirToken(yytext()); return symbol(sym.Coma, 			yytext());}
	{boolID}		 	{imprimirToken(yytext()); return symbol(sym.Booleano,		yytext());}
	{entero} 			{imprimirToken(yytext()); return symbol(sym.Entero, 		yytext());}
	{decimal} 			{imprimirToken(yytext()); return symbol(sym.Decimal, 		yytext());}
	{cadena} 			{imprimirToken(yytext()); return symbol(sym.Cadena,		 	yytext());}
	{identificador} 	{imprimirToken(yytext()); return symbol(sym.Identificador, 	yytext());}
	{WhiteSpace} 		{/*Nothing to do*/}
	{LineTerminator} 	{/*Nothing to do*/}
}

[^] {error("Simbolo invalido <"+ yytext()+">");}
<<EOF>>                 { return symbol(sym.EOF); }