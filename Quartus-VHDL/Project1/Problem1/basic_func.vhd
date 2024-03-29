library ieee;
use ieee.std_logic_1164.all;

package basic_func is
	--AND2 declaration 
	component myAND2
		port (in1,in2 : in std_logic; out1 : out std_logic);
	end component;
	
	--AND3 declaration
	component myAND3
		port (in1,in2,in3 : in std_logic; out1 : out std_logic);
	end component;
	
	--AND4 declaration
	component myAND4
		port (in1,in2,in3,in4 : in std_logic; out1 : out std_logic);
	end component;
	
	--OR5 declaration
	component myOR5
		port (in1,in2,in3,in4,in5 : in std_logic; out1 : out std_logic);
	end component;
	
	--NOT1 declaration
	component myNOT1
		port (in1 : in std_logic; out1 : out std_logic);
	end component;
	
end package basic_func;

   --package body basic_func is
library ieee;
use ieee.std_logic_1164.all;
	-- 2 input AND gate
	entity myAND2 is
		port (in1,in2 : in std_logic; out1 : out std_logic);
	end myAND2;
	
	architecture model_conc of myAND2 is
	begin
			out1 <= in1 and in2;
	end model_conc;
	
library ieee;
use ieee.std_logic_1164.all;
	-- 3 input AND gate
	entity myAND3 is
		port (in1,in2,in3 : in std_logic; out1 : out std_logic);
	end myAND3;
	
	architecture model_conc2 of myAND3 is 
	begin 
			out1 <= in1 and in2 and in3;
	end model_conc2;
	
library ieee;
use ieee.std_logic_1164.all;
	-- 4 input AND gate
	entity myAND4 is 
		port (in1,in2,in3,in4 : in std_logic; out1 : out std_logic);
	end myAND4;
	
	architecture model_conc3 of myAND4 is
	begin 
			out1 <= in1 and in2 and in3 and in4;
	end model_conc3;
	
library ieee;
use ieee.std_logic_1164.all;
	-- 5 input OR gate
	entity myOR5 is
		port (in1,in2,in3,in4,in5 : in std_logic; out1 : out std_logic);
	end myOR5;
	
	architecture model_conc4 of myOR5 is
	begin 
			out1 <= in1 or in2 or in3 or in4 or in5;
	end model_conc4;
	
library ieee;
use ieee.std_logic_1164.all;
	-- 1 input NOT gate
	entity myNOT1 is
		port (in1 : in std_logic; out1 : out std_logic);
	end myNOT1;
	
	architecture model_conc5 of myNOT1 is
	begin
			out1 <= not in1;
	end model_conc5;
	
--end package body basic_func;
	
	