LIBRARY ieee,my_package;
USE ieee.std_logic_1164.all,my_package.basic_func.all;

ENTITY OneBitAlu IS
	PORT(a,b,Cin,Ain,Bin : IN std_logic;
			Op : IN std_logic_vector(1 DOWNTO 0);
			Cout,res : OUT std_logic);
END OneBitAlu;

ARCHITECTURE structure OF OneBitAlu IS
	SIGNAL  m0,m1,m2,m3,m4,m5 : std_logic;
BEGIN
	U0:mux2to1 PORT MAP(a,Ain,m0);
	U1:mux2to1 PORT MAP(b,Bin,m1);
	U2:myAND2 PORT MAP(m0,m1,m2);
	U3:myOR2 PORT MAP(m0,m1,m3);
	U4:fulladd PORT MAP(m0,m1,Cin,Cout,m4);
	U5:myXOR2 PORT MAP(m0,m1,m5);
	U6:mux4to1 PORT MAP(m2,m3,m4,m5,Op(1 DOWNTO 0),res);
END structure;

LIBRARY ieee,my_package;
USE ieee.std_logic_1164.all,my_package.basic_func.all;
package OneBitAlu_package is
component OneBitAlu IS
	PORT(a,b,Cin,Ain,Bin : IN std_logic;
			Op : IN std_logic_vector(1 DOWNTO 0);
			Cout,res : OUT std_logic);
END component;
end OneBitAlu_package;