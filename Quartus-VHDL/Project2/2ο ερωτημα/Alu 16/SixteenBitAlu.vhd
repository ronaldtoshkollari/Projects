library ieee;
use ieee.std_logic_1164.all;
library work;
use work.OneBitAlu_package.all;
use work.control_unit_package.all;


entity SixteenBitAlu is
	port(a,b :in std_logic_vector(0 to 15);
		  overf:inout std_logic;
		  opcode :in std_logic_vector(2 downto 0);
		  carry :inout std_logic_vector(15 downto 0);
		  ainv,binv,cin : in std_logic;
		  oper :in std_logic_vector(1 downto 0);
		  resu :out std_logic_vector(15 downto 0));
end SixteenBitAlu;

architecture structure of SixteenBitAlu is
	signal op : std_logic_vector(1 downto 0);
	signal m1,m2,m3 : std_logic;
begin
	u1:control_unit port map (opcode(2 downto 0),op(1 downto 0),m1,m2,m3);
	u2:OneBitAlu port map(a(0),b(0),m3,m1,m2,op(1 downto 0),resu(0),carry(0));
	u3:
	for i in 1 to 14 generate
	u4:OneBitAlu port map(a(i),b(i),carry(i-1),m1,m2,op(1 downto 0),resu(i),carry(i));
	end generate u3;
	u5:OneBitAlu port map(a(15),b(15),carry(14),m1,m2,op(1 downto 0),resu(15),overf);
end structure;