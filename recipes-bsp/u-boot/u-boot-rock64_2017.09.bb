
FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-rockchip:"

include u-boot-rockchip.inc

SRCREV = "ec1524dabf0654053295969a3c713738408e578a"

SRC_URI = " \
	git://github.com/ayufan-rock64/linux-u-boot.git;branch=rockchip-master \
	file://binutils-2.28-ld-fix.patch \
"

S = "${WORKDIR}/git"

