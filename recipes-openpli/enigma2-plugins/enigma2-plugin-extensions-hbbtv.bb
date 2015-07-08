DESCRIPTION = "HbbTv"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://README.md;md5=4a881119de8945a8b2ca1da5e5ca1f58"

SRC_URI = "git://github.com/MOA-2011/enigma2-plugin-extensions-HbbTV.git"
PR = "10"

inherit gitpkgv

S = "${WORKDIR}/git"
SRC = "${S}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV"
DEST = "${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV"

PKGV = "810+git${GITPKGV}"
SRCREV = "${AUTOREV}"

FILES_${PN} = "/usr/lib/enigma2/python/Plugins/Extensions/HbbTV"

do_install_append() {
	install -d ${DEST}
	cp -rp ${SRC}/* ${DEST}
	# remove the files we do not want in our package
#	find ${DEST} -name '*.pyo' -exec rm {} \;
#	find ${DEST} -name '*.po' -exec rm {} \;
}
