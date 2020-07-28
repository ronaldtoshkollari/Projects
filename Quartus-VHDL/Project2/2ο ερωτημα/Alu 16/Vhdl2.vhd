library ieee;
use work.all,ieee.std_logic_1164.all;

package basic_func2 is

	component control_unit is
		port(ain,bin,cin :in std_logic;
			  op :in std_logic_vector(1 downto 0);
			  opcode : in std_logic_vector(2 downto 0));
	end component;
	component alu1 is 
		port (a,b,ain,bin,cin		:in std_logic;
				op							:in std_logic_vector(1 downto 0);
				res						:out std_logic_vector(15 downto 0);
				overflow 				:out std_logic);
	end component;
end basic_func2;
------------------------------------------------
library ieee;
use work.all,ieee.std_logic_1164.all;

entity control_unit is
	port (ain,bin,cin :in std_logic;
			op :in std_logic_vector(1 downto 0);
			opcode : in std_logic_vector(2 downto 0));
end control_unit;
architecture cu of control_unit is 
begin
	process(opcode,ain,bin,cin)
	begin
		if opcode = '000' then 
			U0:mux2to1 PORT MAP(a,0,m0);
			U1:mux2to1 PORT MAP(b,0,m1);
			U2:myAND2 PORT MAP(m0,m1,m2);
		else if opcode = '001' then
			U0:mux2to1 PORT MAP(a,0,m0);
			U1:mux2to1 PORT MAP(b,0,m1);
			U2:myOR2 PORT MAP(m0,m1,m3);
		else if opcode = '011' then
			U0:mux2to1 PORT MAP(a,0,m0);
			U1:mux2to1 PORT MAP(b,0,m1);
			U2:fulladd PORT MAP(m0,m1,0,Cout,m4);
		else if opcode = '010' then
			U0:mux2to1 PORT MAP(a,0,m0);
			U1:mux2to1 PORT MAP(b,1,m1);
			U2:fulladd PORT MAP(m0,m1,1,Cout,m4);
		else if opcode = '101' then
			U0:mux2to1 PORT MAP(a,1,m0);
			U1:mux2to1 PORT MAP(b,1,m1);
			U2:myAND2 PORT MAP(m0,m1,m2);
		else if opcode = '100' then
			U0:mux2to1 PORT MAP(a,0,m0);
			U1:mux2to1 PORT MAP(b,0,m1);
			U2:myXOR2 PORT MAP(m0,m1,m5);
		end if;
	end process;
end cu;
------------------------------------------------
library ieee;
use work.all,ieee.std_logic_1164.all;

entity alu1 is 
	port (a,b,ain,bin,cin		:in std_logic;
			op							:in std_logic_vector(1 downto 0);
			res						:out std_logic_vector(15 downto 0);
			overflow 				:out std_logic);
end alu1;
architecture myAlu1 of alu1 is 
begin

end myAlu1;
	