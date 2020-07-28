library ieee;
library my_package;
use my_package.basic_func.all;
use ieee.std_logic_1164.all;

entity control_unit is
	port(opcode :in std_logic_vector(2 downto 0);
		  oper : out std_logic_vector(1 downto 0);
		  ainv,binv,cin : out std_logic);
end control_unit;

architecture behaivoral of control_unit is 
begin
  process (opcode(2 downto 0))
  begin
	if opcode = "000" then
		
		oper <= "00";
		ainv <= '0';
		binv <= '0';
		cin <= '0';	
	elsif opcode = "001" then 
		oper <= "01";
		ainv <= '0';
		binv <= '0';
		cin <= '0';	
	elsif opcode = "011" then 
		oper <= "11";
		ainv <= '0';
		binv <= '0';
		cin <= '0';	
	elsif opcode = "010" then 
		oper <= "11";
		ainv <= '0';
		binv <= '1';
		cin <= '1';
	elsif opcode = "101" then 
		oper <= "00";
		ainv <= '1';
		binv <= '1';
		cin <= '0';
	elsif opcode = "100" then 
		oper <= "10";
		ainv <= '0';
		binv <= '0';
		cin <= '0';
	elsif opcode = "110" then 
		oper <= "10";
		ainv <= '1';
		binv <= '0';
		cin <= '1';
	elsif opcode = "111" then 
		oper <= "11";
		ainv <= '1';
		binv <= '0';
		cin <= '1';
	end if;
	end process;
end behaivoral;

library ieee;
use ieee.std_logic_1164.all;
package control_unit_package is 
component control_unit is 
	port(opcode :in std_logic_vector(2 downto 0);
		  oper : out std_logic_vector(1 downto 0);
		  ainv,binv,cin : out std_logic);
end component;
end control_unit_package;
