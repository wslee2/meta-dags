DESCRIPTION = "epanel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://README.md;md5=4a881119de8945a8b2ca1da5e5ca1f58"

SRC_URI = "git://github.com/MOA-2011/enigma2-plugin-extensions-epanel.git"
PR = "5.6"

inherit gitpkgv

S = "${WORKDIR}/git"
SRC = "${S}/usr/lib/enigma2/python/Plugins/Extensions/epanel"
DEST = "${D}/usr/lib/enigma2/python/Plugins/Extensions/epanel"

PKGV = "20+git${GITPKGV}"
SRCREV = "${AUTOREV}"

FILES_${PN} = "/usr/lib/enigma2/python/Plugins/Extensions/epanel"

do_install_append() {
	install -d ${DEST}
	cp -rp ${SRC}/* ${DEST}
    cp -rp ${S}/usr/script ${D}/usr/
	# remove the files we do not want in our package
	find ${DEST} -name '*.pyo' -exec rm {} \;
	find ${DEST} -name '*.po' -exec rm {} \;
}
