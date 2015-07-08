DESCRIPTION = "3GModemManager"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://README.md;md5=1f14448a49f939ba36350992ffcdefd5"

SRC_URI = "git://github.com/MOA-2011/enigma2-plugin-systemplugins-3gmodemmanager.git"

inherit gitpkgv

S = "${WORKDIR}/git"
SRC = "${S}/usr/lib/enigma2/python/Plugins/SystemPlugins/3GModemManager"
DEST = "${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/3GModemManager"

PKGV = "20+git${GITPKGV}"
SRCREV="${AUTOREV}"

FILES_${PN} = "/usr/lib/enigma2/python/Plugins/SystemPlugins/3GModemManager"

do_install_append() {
	install -d ${DEST}
	cp -rp ${SRC}/* ${DEST}
	# remove the files we do not want in our package
	find ${DEST} -name '*.pyo' -exec rm {} \;
	find ${DEST} -name '*.po' -exec rm {} \;
}
