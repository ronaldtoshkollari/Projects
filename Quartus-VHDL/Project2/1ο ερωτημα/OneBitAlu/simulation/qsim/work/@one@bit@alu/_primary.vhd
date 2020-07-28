library verilog;
use verilog.vl_types.all;
entity OneBitAlu is
    port(
        a               : in     vl_logic;
        b               : in     vl_logic;
        Cin             : in     vl_logic;
        Ain             : in     vl_logic;
        Bin             : in     vl_logic;
        Op              : in     vl_logic_vector(1 downto 0);
        Cout            : out    vl_logic;
        res             : out    vl_logic
    );
end OneBitAlu;
