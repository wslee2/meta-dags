DESCRIPTION = "dumpait"

PRIORITY = "required"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.GPLv3;md5=5ed852a46d22220a8b07a68e564d84c7"

SRC_URI = "git://192.168.1.158/hbbtv-dumpait"
S = "${WORKDIR}/git"
DESTDIR = "enigma2/python/Plugins/Extensions/HbbTV"

inherit gitpkgv
SRCREV = "b83432e0f273738a2957f4a97fab91bd1a034a59"
PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit autotools-brokensep pkgconfig

do_install() {
	install -d ${D}/usr/lib/${DESTDIR}
	install -m 0755 ${S}/src/dumpait ${D}/usr/lib/${DESTDIR}
}

FILES_${PN} = "${libdir}/${DESTDIR}/dumpait"
FILES_${PN}-dbg += "${libdir}/${DESTDIR}/.debug/dumpait"
