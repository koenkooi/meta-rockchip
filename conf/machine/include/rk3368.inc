# Copyright (C) 2016 Romain Perier
# Released under the MIT license (see COPYING.MIT for the terms)

require conf/machine/include/arm/arch-armv8.inc

DEFAULTTUNE="aarch64"
PREFERRED_PROVIDER_virtual/kernel = "linux-dummy"
SERIAL_CONSOLES = "115200;ttyS2"
KERNEL_IMAGETYPE = "Image"
KBUILD_DEFCONFIG = "defconfig"
