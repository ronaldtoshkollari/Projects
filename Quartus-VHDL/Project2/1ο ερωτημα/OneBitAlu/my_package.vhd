LIBRARY ieee;
USE ieee.std_logic_1164.all;

PACKAGE basic_func IS
	--AND declaration
	COMPONENT myAND2
		PORT(in1,in2 : IN std_logic;
			  out1 : OUT std_logic);
	END COMPONENT;
	
	--OR declaration
	COMPONENT myOR2
		PORT(in1,in2 : IN std_logic;
			  out1 : OUT std_logic);
	END COMPONENT;
	--fulladd declaration
	COMPONENT fulladd 
		PORT(in1,in2,in3 : IN std_logic;
			  out1,out2 : OUT std_logic);
	END COMPONENT;
	--XOR declaration
	COMPONENT myXOR2
		PORT(in1,in2 : IN std_logic;
			  out1 : OUT std_logic);
	END COMPONENT;
	--MUX2TO1 declaration
	COMPONENT mux2to1
		PORT(in1,in2 : IN std_logic;
			  out1 :OUT std_logic);
	END COMPONENT;
	--MUX4TO1 declaration
	COMPONENT mux4to1
		PORT(in1,in2,in3,in4 : IN std_logic;
			  s : IN std_logic_vector(1 DOWNTO 0);
			  f : OUT std_logic);
	END COMPONENT;
END basic_func;

LIBRARY ieee;
USE ieee.std_logic_1164.all;

	--myAND gate
	ENTITY myAND2 IS
		PORT(in1,in2 : IN std_logic;
			  out1 : OUT std_logic);
	END myAND2;
	ARCHITECTURE model_conc OF myAND2 IS
	BEGIN
		out1 <= in1 AND in2;
	END model_conc;
	
LIBRARY ieee;
USE ieee.std_logic_1164.all;

	--myOR gate
	ENTITY myOR2 IS
		PORT(in1,in2 : IN std_logic;
			  out1 : OUT std_logic);
	END myOR2;
	ARCHITECTURE model_conc1 OF myOR2 IS
	BEGIN
		out1 <= in1 OR in2;
	END model_conc1;
	
LIBRARY ieee;
USE ieee.std_logic_1164.all;

	--fulladd gate
	ENTITY fulladd IS
		PORT(in1,in2,in3 : IN std_logic;
			  out1,out2 : OUT std_logic);
	END fulladd;
	ARCHITECTURE model_conc2 OF fulladd IS
	BEGIN
		out1 <= (in2 AND in3) OR (in1 AND in3) OR (in1 AND in2);
		out2 <= (in1 AND NOT in2 AND NOT in3) OR (NOT in1 AND in2 AND NOT in3) OR (NOT in1 AND NOT in2 AND in3) OR (in1 AND in2 AND in3);
	END model_conc2;
	
LIBRARY ieee;
USE ieee.std_logic_1164.all;

	--myXOR gate
	ENTITY myXOR2 IS
		PORT(in1,in2 : IN std_logic;
			  out1 : OUT std_logic);
	END myXOR2;
	ARCHITECTURE model_conc3 OF myXOR2 IS 
	BEGIN
		out1 <= (in1 AND NOT in2) OR (NOT in1 AND in2);
	END model_conc3;

LIBRARY ieee;
USE ieee.std_logic_1164.all;
	
	--mux2to1 gate 
	ENTITY mux2to1 IS 
		PORT(in1,in2 : IN std_logic;
			  out1 : OUT std_logic);
	END mux2to1;
	ARCHITECTURE model_conc4 OF mux2to1 IS
	BEGIN
		PROCESS (in1,in2)
		BEGIN
			IF in2 = '0' THEN
				out1 <= in1;
			END IF;
			IF in2 = '1' THEN
				out1 <= NOT in1;
			END IF;
		END PROCESS;
	END model_conc4;
	
LIBRARY ieee;
USE ieee.std_logic_1164.all;

	--mux4to1 gate
	ENTITY mux4to1 IS
		PORT(in1,in2,in3,in4 : IN std_logic;
			  s : IN std_logic_vector(1 DOWNTO 0);
			  f : OUT std_logic);
	END mux4to1;
	ARCHITECTURE model_conc5 OF mux4to1 IS
	BEGIN
		WITH s SELECT
			f <= in1 WHEN "00",
				  in2 WHEN "01",
				  in3 WHEN "10",
				  in4 WHEN OTHERS ;
	END model_conc5;