library verilog;
use verilog.vl_types.all;
entity OneBitAlu_vlg_sample_tst is
    port(
        a               : in     vl_logic;
        Ain             : in     vl_logic;
        b               : in     vl_logic;
        Bin             : in     vl_logic;
        Cin             : in     vl_logic;
        Op              : in     vl_logic_vector(1 downto 0);
        sampler_tx      : out    vl_logic
    );
end OneBitAlu_vlg_sample_tst;
