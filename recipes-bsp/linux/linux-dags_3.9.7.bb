DESCRIPTION = "Linux kernel for ${MACHINE}"
LICENSE = "GPL"
SECTION = "kernel"
KV = "3.9.7-r1"

DATE = "20150624"

MACHINE_KERNEL_PR_append = ".5"

SRC_URI[md5sum] = "5d404b7cfc11cc2317ab18faf447e836"
SRC_URI[sha256sum] = "e74230afef9663c16d76dbe3ef505364f1f7bf9d272c34b1ed29f2c0f517db19"

LIC_FILES_CHKSUM = "file://${WORKDIR}/linux-${KV}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRC_URI = "http://en3homeftp.net/pub/src/linux-${KV}-${DATE}.tar.gz \
           file://sit2_op.o \
    	   file://${CHIP}_defconfig \
"

S = "${WORKDIR}/linux-${KV}"

inherit kernel

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_OUTPUT = "vmlinux"
KERNEL_IMAGETYPE = "vmlinux"
KERNEL_IMAGEDEST = "/tmp"

FILES_kernel-image = "${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}*"

do_configure_prepend() {
    oe_machinstall -m 0644 ${WORKDIR}/${CHIP}_defconfig ${S}/.config
    cp ${WORKDIR}/sit2_op.o ${S}/drivers/media/dvb-frontends/sit2_op.o
    oe_runmake oldconfig
}

kernel_do_install_append() {
    ${STRIP} ${D}${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
    gzip -9c ${D}${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION} > ${D}${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
    rm -rf ${D}${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
}

pkg_postinst_kernel-image () {
    if [ "x$D" == "x" ]; then
        if [ -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz ] ; then
            flash_erase /dev/mtd6 0 0
            nandwrite -p /dev/mtd6 /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
            rm -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
        fi
    fi
    true
}
