FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_rk3328 = " \
        file://rock64-rk3328_defconfig \
"

do_configure_prepend_rk3328() {
    cp ${WORKDIR}/*defconfig ${S}/configs
}

do_compile_append_rk3288 () {
	# copy to default search path
	if [ ${SPL_BINARY} ]; then
		cp ${B}/spl/${SPL_BINARY} ${B}/
	fi
}


