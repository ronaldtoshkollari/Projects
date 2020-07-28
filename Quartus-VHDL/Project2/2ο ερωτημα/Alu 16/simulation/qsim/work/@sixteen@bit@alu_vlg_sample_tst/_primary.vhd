library verilog;
use verilog.vl_types.all;
entity SixteenBitAlu_vlg_sample_tst is
    port(
        a               : in     vl_logic_vector(0 to 15);
        ainv            : in     vl_logic;
        b               : in     vl_logic_vector(0 to 15);
        binv            : in     vl_logic;
        carry           : in     vl_logic_vector(15 downto 0);
        cin             : in     vl_logic;
        oper            : in     vl_logic_vector(1 downto 0);
        sampler_tx      : out    vl_logic
    );
end SixteenBitAlu_vlg_sample_tst;
