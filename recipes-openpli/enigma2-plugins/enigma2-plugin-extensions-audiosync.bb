DESCRIPTION = "AudioSync"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://README.md;md5=b784c311fa69c66d1c315910770dce6f"

SRC_URI = "git://github.com/MOA-2011/enigma2-plugin-extensions-audiosync.git"
PR = "10"

inherit gitpkgv

S = "${WORKDIR}/git"
SRC = "${S}/usr/lib/enigma2/python/Plugins/Extensions/AudioSync"
DEST = "${D}/usr/lib/enigma2/python/Plugins/Extensions/AudioSync"

PKGV = "810+git${GITPKGV}"
SRCREV = "${AUTOREV}"

FILES_${PN} = "/usr/lib/enigma2/python/Plugins/Extensions/AudioSync"

do_install_append() {
	install -d ${DEST}
	cp -rp ${SRC}/* ${DEST}
	# remove the files we do not want in our package
#	find ${DEST} -name '*.pyo' -exec rm {} \;
#	find ${DEST} -name '*.po' -exec rm {} \;
}
