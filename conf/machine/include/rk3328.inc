SOC_FAMILY = "rk3328"

require conf/machine/include/arm/arch-arm64.inc
require conf/machine/include/soc-family.inc
require conf/machine/include/rockchip-defaults.inc

KERNEL_IMAGETYPE = "Image"
SERIAL_CONSOLES = "115200;ttyS2"

PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-rock64"
SPL_BINARY ?= "u-boot-spl-dtb.bin"

IMAGE_FSTYPES = "rockchip-gpt-img"
IMAGE_CLASSES += "rockchip-gpt-img"
