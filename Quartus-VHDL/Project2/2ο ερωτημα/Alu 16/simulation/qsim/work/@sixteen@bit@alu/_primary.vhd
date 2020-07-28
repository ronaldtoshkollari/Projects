library verilog;
use verilog.vl_types.all;
entity SixteenBitAlu is
    port(
        a               : in     vl_logic_vector(0 to 15);
        b               : in     vl_logic_vector(0 to 15);
        carry           : inout  vl_logic_vector(15 downto 0);
        ainv            : in     vl_logic;
        binv            : in     vl_logic;
        cin             : in     vl_logic;
        oper            : in     vl_logic_vector(1 downto 0);
        resu            : out    vl_logic_vector(15 downto 0)
    );
end SixteenBitAlu;
