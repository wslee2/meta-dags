DESCRIPTION = "NetworkBrowser"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://README.md;md5=062c46a2b61e6a7df2024d5ba7154c85"

SRC_URI = "git://github.com/MOA-2011/enigma2-plugin-systemplugins-networkbrowser"

inherit gitpkgv

S = "${WORKDIR}/git"
SRC = "${S}/usr/lib/enigma2/python/Plugins/SystemPlugins/NetworkBrowser"
DEST = "${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/NetworkBrowser"

PKGV = "20+git${GITPKGV}"
SRCREV="${AUTOREV}"

FILES_${PN} = "/usr/lib/enigma2/python/Plugins/SystemPlugins/NetworkBrowser"

do_install_append() {
	install -d ${DEST}
	cp -rp ${SRC}/* ${DEST}
	# remove the files we do not want in our package
	find ${DEST} -name '*.pyo' -exec rm {} \;
	find ${DEST} -name '*.po' -exec rm {} \;
}
