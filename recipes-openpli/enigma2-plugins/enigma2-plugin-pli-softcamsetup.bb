DESCRIPTION = "SoftcamSetup"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://README.md;md5=225e02d4eaa12679b238905538070a58"

SRC_URI = "git://github.com/MOA-2011/enigma2-plugin-pli-softcamsetup"
#PR = "10"

inherit gitpkgv

S = "${WORKDIR}/git"
SRC = "${S}/usr/lib/enigma2/python/Plugins/PLi/SoftcamSetup"
DEST = "${D}/usr/lib/enigma2/python/Plugins/PLi/SoftcamSetup"

#PKGV = "10+git${GITPKGV}"
SRCREV = "${AUTOREV}"

FILES_${PN} = "/usr/lib/enigma2/python/Plugins/PLi/SoftcamSetup"

do_install_append() {
	install -d ${DEST}
	cp -rp ${SRC}/* ${DEST}
	# remove the files we do not want in our package
#	find ${DEST} -name '*.pyo' -exec rm {} \;
#	find ${DEST} -name '*.po' -exec rm {} \;
}
