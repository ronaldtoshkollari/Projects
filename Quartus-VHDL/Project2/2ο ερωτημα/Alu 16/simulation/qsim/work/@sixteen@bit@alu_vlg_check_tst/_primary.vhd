library verilog;
use verilog.vl_types.all;
entity SixteenBitAlu_vlg_check_tst is
    port(
        carry           : in     vl_logic_vector(15 downto 0);
        resu            : in     vl_logic_vector(15 downto 0);
        sampler_rx      : in     vl_logic
    );
end SixteenBitAlu_vlg_check_tst;
