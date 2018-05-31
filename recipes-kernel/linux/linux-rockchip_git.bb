require linux.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

DEPENDS += "openssl-native"

DESCRIPTION = "Linux kernel"
KERNEL_IMAGETYPE ?= "Image"

COMPATIBLE_MACHINE = "(rk3328)"

S = "${WORKDIR}/git"


PV = "4.17"
# Since it's actally 4.17-rcsomething skip the version check
KERNEL_VERSION_SANITY_SKIP="1"


#SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=https;branch=linux-4.17.y"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=https;branch=master"
SRCREV_pn-${PN} = "88a867653065dc14b0fdeeb626efb8d7ebe39be5"

# defconfig and config fragments
SRC_URI += " \
             file://0001-ARM-dts-add-gpu-node-to-exynos4.patch \
             file://0002-dt-bindings-add-switch-delay-property-for-mali-utgar.patch \
             file://0003-arm64-dts-add-switch-delay-for-meson-mali.patch \
             file://0004-arm64-dts-add-switch-delay-for-meson-mali.patch \
             file://0005-drm-lima-add-lima-uapi-header.patch \
             file://0006-drm-lima-add-mali-4xx-GPU-hardware-regs.patch \
             file://0007-drm-lima-add-lima-core-driver.patch \
             file://0008-drm-lima-add-GPU-device-functions.patch \
             file://0009-drm-lima-add-PMU-related-functions.patch \
             file://0010-drm-lima-add-L2-cache-functions.patch \
             file://0011-drm-lima-add-GP-related-functions.patch \
             file://0012-drm-lima-add-PP-related-functions.patch \
             file://0013-drm-lima-add-MMU-related-functions.patch \
             file://0014-drm-lima-add-BCAST-related-function.patch \
             file://0015-drm-lima-add-DLBU-related-functions.patch \
             file://0016-drm-lima-add-GPU-virtual-memory-space-handing.patch \
             file://0017-drm-lima-add-TTM-subsystem-functions.patch \
             file://0018-drm-lima-add-buffer-object-functions.patch \
             file://0019-drm-lima-add-GEM-related-functions.patch \
             file://0020-drm-lima-add-GEM-Prime-related-functions.patch \
             file://0021-drm-lima-add-GPU-schedule-using-DRM_SCHED.patch \
             file://0022-drm-lima-add-context-related-functions.patch \
             file://0023-drm-lima-add-makefile-and-kconfig.patch \
             file://0024-drm-lima-use-SPDX-identifiers-and-change-copyright.patch \
             file://0025-drm-lima-lima_reg.h-use-BIT.patch \
             file://0026-drm-lima-add-IRQF_SHARED-for-GP-irq.patch \
             file://0027-drm-lima-Kconfig-only-depend-on-ARM-or-ARM64.patch \
             file://0028-drm-lima-add-comments-for-lima_reg.h.patch \
             file://0029-drm-lima-wait-bo-fence-before-bo-close.patch \
             file://0030-drm-lima-refine-lima_gem_sync_bo.patch \
             file://0031-drm-lima-vm-will-be-freed-when-lima_sched_free_job.patch \
             \
             file://defconfig \
             file://bbr.fragment \
             file://iosched.fragment \
             file://block.fragment \
             file://btrfs.fragment \
             file://debug.fragment \
             file://systemd.fragment \
             file://cifs.fragment \
             file://nfs.fragment \
             file://unwinder.fragment \
             file://mali.fragment \
            "

KERNEL_CONFIG_FRAGMENTS_append = " \
                                  ${WORKDIR}/bbr.fragment \
                                  ${WORKDIR}/iosched.fragment \
                                  ${WORKDIR}/block.fragment \
                                  ${WORKDIR}/btrfs.fragment \
                                  ${WORKDIR}/debug.fragment \
                                  ${WORKDIR}/cifs.fragment \
                                  ${WORKDIR}/systemd.fragment \
                                  ${WORKDIR}/unwinder.fragment \
                                  ${WORKDIR}/mali.fragment \
                                 "
