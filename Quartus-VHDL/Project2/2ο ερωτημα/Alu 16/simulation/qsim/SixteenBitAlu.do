onerror {quit -f}
vlib work
vlog -work work SixteenBitAlu.vo
vlog -work work SixteenBitAlu.vt
vsim -novopt -c -t 1ps -L cycloneii_ver -L altera_ver -L altera_mf_ver -L 220model_ver -L sgate work.SixteenBitAlu_vlg_vec_tst
vcd file -direction SixteenBitAlu.msim.vcd
vcd add -internal SixteenBitAlu_vlg_vec_tst/*
vcd add -internal SixteenBitAlu_vlg_vec_tst/i1/*
add wave /*
run -all
