DESCRIPTION = "force1 skin"
MAINTAINER = "2boom"
LICENSE = "Proprietary"
require conf/license/openpli-gplv2.inc

inherit gitpkgv

SRCREV = "${AUTOREV}"
PKGV = "0.1+git${GITPKGV}"
PACKAGE_ARCH = "all"
PR = "1.0"

SRC_URI = "git://github.com/MOA-2011/skin-FORCE1.git;protocol=git"

FILES_${PN} = "/usr/share/enigma2/"

S = "${WORKDIR}/git"

do_compile() {
}

do_install() {
	install -d ${D}/usr/share
	cp -rp ${S}/usr/share/* ${D}/usr/share/
	chmod -R a+rX ${D}/usr/share/enigma2/
}
