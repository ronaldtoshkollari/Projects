library verilog;
use verilog.vl_types.all;
entity OneBitAlu_vlg_check_tst is
    port(
        Cout            : in     vl_logic;
        res             : in     vl_logic;
        sampler_rx      : in     vl_logic
    );
end OneBitAlu_vlg_check_tst;
