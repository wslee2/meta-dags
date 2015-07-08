DESCRIPTION = "Backup Suite"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://usr/lib/enigma2/python/Plugins/Extensions/BackupSuite/GNU_LICENSE.txt;md5=8aab2037d6ccded90e5a3a3849bc60dc"

SRC_URI = "git://github.com/MOA-2011/enigma2-plugin-extensions-backupsuite"

inherit gitpkgv

S = "${WORKDIR}/git"
SRC = "${S}/usr/lib/enigma2/python/Plugins/Extensions/BackupSuite"
DEST = "${D}/usr/lib/enigma2/python/Plugins/Extensions/BackupSuite"

PKGV = "20+git${GITPKGV}"

RDEPENDS_${PN} = "mtd-utils mtd-utils-ubifs ofgwrite"

FILES_${PN} = "/usr/lib/enigma2/python/Plugins/Extensions/BackupSuite"

do_install_append() {
	install -d ${DEST}
	cp -rp ${SRC}/* ${DEST}
	# remove the files we do not want in our package
	find ${DEST} -name '*.pyo' -exec rm {} \;
	find ${DEST} -name '*.po' -exec rm {} \;
}
