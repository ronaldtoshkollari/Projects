entity ask3 IS
    port (x1, x2, x3 :IN BIT;
	       f              : OUT BIT);
END ask3;

ARCHITECTURE LogicFunc OF ask3 IS
BEGIN
f <= (NOT x1 AND x2) OR (NOT x1 AND x3) OR (x2 AND x3) OR (x1 AND NOT x2 AND NOT x3);
END LogicFunc;