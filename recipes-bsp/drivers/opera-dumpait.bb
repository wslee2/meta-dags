DESCRIPTION = "dumpait"
PRIORITY = "required"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.GPLv3;md5=5ed852a46d22220a8b07a68e564d84c7"

SRCREV="${AUTOREV}"
PR = "r0"
DESTDIR = "enigma2/python/Plugins/Extensions/HbbTV"
SRC_URI = "git://192.168.1.158/code.vuplus.com.git.dumpait.git;branch=master"

inherit autotools pkgconfig

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}/usr/lib/${DESTDIR}
    install -m 0755 ${WORKDIR}/build/src/dumpait ${D}/usr/lib/${DESTDIR}
}

FILES_${PN} = "${libdir}/${DESTDIR}/dumpait"

PACKAGES = "${PN}"

