/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

grammar sys;


r: IDENT NUM time ip x* user xout (mac xx)? proto xuser ipfull xtra ipfull1 xtra1 (xipfull xtra xipfull xtra2 xipfull xtra3)?; 
time: NUM COLN NUM COLN NUM;
ip: NUM DOT NUM DOT NUM DOT NUM ;
ipfull: NUM DOT NUM DOT NUM DOT NUM COLN NUM ;
ipfull1: NUM DOT NUM DOT NUM DOT NUM COLN NUM ;
xipfull: NUM DOT NUM DOT NUM DOT NUM COLN NUM ;

//x: (IDENT | COMMA | COLN | BRAC | HYPHN | NUM)+ (LTHAN | BRAC)*;
x: IDENT COMMA IDENT* HYPHN? IDENT* COLN IDENT IDENT? COLN (IDENT COLN)? LTHAN? ;
user: (IDENT | HYPHN | DOT | NUM)+ ;
xout: (GTHAN/*| BRAC*/)? IDENT COLN IDENT+ HYPHN IDENT+ (DOT IDENT)? COMMA IDENT (HYPHN IDENT)?;
mac: (MAC | NUM) COLN (MAC | NUM) COLN (MAC | NUM) COLN (MAC | NUM) COLN (MAC | NUM) COLN (MAC | NUM);
xx: COMMA IDENT;
proto: IDENT ;
xuser: (IDENT | BRAC | COMMA)+ ;
xtra: HYPHN GTHAN ;
xtra1: COMMA IDENT (BRAC | NUM);
xtra2: BRAC xtra;
xtra3: COMMA IDENT NUM;

MAC: ('0'..'9')('a'..'f') | ('a'..'f')('0'..'9') | ('a'..'f')('a'..'f');
IDENT: ('a'..'z' | 'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')* ;
NUM: ('0'..'9')+ ;
LTHAN: '<' ;
GTHAN: '>' ;
COLN: ':';
COMMA: ',';
BRAC: '(' | ')' ;
HYPHN: '-';
DOT: '.';
WS : (' ' | '\t' | '\r' )+ -> skip ;