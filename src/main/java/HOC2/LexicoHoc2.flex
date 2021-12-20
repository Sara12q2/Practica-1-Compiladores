package HOC2;
import java_cup.runtime.*;
import java.io.Reader;
%%
%class AnalizadorLexico
%line
%column
%char
%cup

%{
    private Symbol symbol(int type){
        return new Symbol(type, yyline,yycolumn);
    }

    private Symbol symbol(int type, Object value){
        return new Symbol(type,yyline,yycolumn,value);
    }
%}

LetraMin = [a-z]
Digito = [0-9]
%%
[ \t]+                  { ;}
"\r""\n"                {return symbol(AnalizadorSintacticoSym.Enter);}
{Digito}+(\.{Digito}+)? {return symbol(AnalizadorSintacticoSym.NUM, new Float(yytext()));}
"="                     {return symbol(AnalizadorSintacticoSym.Asig);}
"/"                     {return symbol(AnalizadorSintacticoSym.opDiv);}
"*"                     {return symbol(AnalizadorSintacticoSym.opProd);}
"-"                     {return symbol(AnalizadorSintacticoSym.opResta);}
"+"                     {return symbol(AnalizadorSintacticoSym.opSuma);}
")"                     {return symbol(AnalizadorSintacticoSym.ParDer);}
"("                     {return symbol(AnalizadorSintacticoSym.ParIzq);}
{LetraMin}              {int IndVar; IndVar=(int)(yytext().charAt(0))-(int)'a'; return symbol(AnalizadorSintacticoSym.VAR,new Integer(IndVar));}
. {return symbol(AnalizadorSintacticoSym.error);}