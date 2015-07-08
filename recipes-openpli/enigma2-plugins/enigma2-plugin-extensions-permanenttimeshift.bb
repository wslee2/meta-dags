DESCRIPTION = "permanent timeshift"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://README.md;md5=e68219f8a00cd437bcc355d1a7d3253c"

SRC_URI = "git://github.com/MOA-2011/enigma2-plugin-extensions-permanenttimeshift1.3h.git"

inherit gitpkgv

S = "${WORKDIR}/git"
SRC = "${S}/usr/lib/enigma2/python/Plugins/Extensions/PermanentTimeshift"
DEST = "${D}/usr/lib/enigma2/python/Plugins/Extensions/PermanentTimeshift"

PKGV = "20+git${GITPKGV}"
SRCREV = "${AUTOREV}"

FILES_${PN} = "/usr/lib/enigma2/python/Plugins/Extensions/PermanentTimeshift"

do_install_append() {
	install -d ${DEST}
	cp -rp ${SRC}/* ${DEST}
	# remove the files we do not want in our package
	find ${DEST} -name '*.pyo' -exec rm {} \;
	find ${DEST} -name '*.po' -exec rm {} \;
}
