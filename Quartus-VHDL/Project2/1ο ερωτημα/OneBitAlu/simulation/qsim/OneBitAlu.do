onerror {quit -f}
vlib work
vlog -work work OneBitAlu.vo
vlog -work work OneBitAlu.vt
vsim -novopt -c -t 1ps -L cycloneii_ver -L altera_ver -L altera_mf_ver -L 220model_ver -L sgate work.OneBitAlu_vlg_vec_tst
vcd file -direction OneBitAlu.msim.vcd
vcd add -internal OneBitAlu_vlg_vec_tst/*
vcd add -internal OneBitAlu_vlg_vec_tst/i1/*
add wave /*
run -all
