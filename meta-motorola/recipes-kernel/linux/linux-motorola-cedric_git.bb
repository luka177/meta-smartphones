require recipes-kernel/linux/linux.inc

SECTION = "kernel"

# Mark archs/machines that this kernel supports
COMPATIBLE_MACHINE = "cedric"

DESCRIPTION = "Linux kernel for the Motorola G5 device based on the official \
source from Motorola"

ANDROID_BOOTIMG_CMDLINE = "androidboot.hardware=qcom user_debug=30 msm_rtb.filter=0x237"
ANDROID_BOOTIMG_KERNEL_RAM_BASE = "0x80000000"
ANDROID_BOOTIMG_RAMDISK_RAM_BASE = "0x01000000"
ANDROID_BOOTIMG_TAGS_RAM_BASE = "0x00000100"

inherit kernel_android

SRC_URI = " \
  git://github.com/reivilibre/android_kernel_motorola_msm8937.git;branch=halium-7.1 \
"
S = "${WORKDIR}/git"

do_configure_prepend() {
    cp -v -f ${S}/arch/arm/configs/cedric_defconfig ${WORKDIR}/defconfig
}

SRCREV = "348fe0b00ca7d10855fdd0e4d1119ef668be3aa6"

KV = "3.18.49"
PV = "${KV}+gitr${SRCPV}"
# for bumping PR bump MACHINE_KERNEL_PR in the machine config
inherit machine_kernel_pr

do_install_append () {
    rm -rf ${D}/usr/src/usr

}
