Task         : 0 : (00|11)*((01|10)(00|11)*(01|10)(00|11)*)*
Real         : 2 : [+-]?(\d+|\d+\.|\.\d+|\d+\.\d+)([eE][+-]?\d+)?
Integer      : 3 : [+-]?\d+
KeyWord      : 5 : (define|if|cond|lambda)
OpenBracket  : 3 : \(
CloseBracket : 4 : \)
Space        : 1 : \s+
ID           : 1 : [\w\+\-\*/%!_\d]+
