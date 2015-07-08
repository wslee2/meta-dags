DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

KV = "3.9.7"
KV_EXTRA = ""
PV = "${KV}+${SRCDATE}"

SRCDATE = "20150707"

SRC_URI = "http://en3homeftp.net/release/images/oedrivers/bcmlinuxdvb_${CHIP}-${KV}-${SRCDATE}.tar.gz"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

inherit module

do_compile() {
}

FILES_${PN} += "${sysconfdir}/modules-load.d/_${MACHINE}.conf"

do_install() {
		install -d ${D}/lib/modules/${KV}/extra
		for f in ${S}/lib/modules/${KV}/extra/*.ko; do
			install -m 0644 $f ${D}/lib/modules/${KV}/extra;
		done
		install -d ${D}/${sysconfdir}/modules-load.d
		for i in `ls ${D}/lib/modules/${KV}/extra | grep \\.ko | sed -e 's/.ko//g'`; do
		    echo $i _hwtype=\$hwtypenum >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE}.conf
		done
}

SRC_URI[md5sum] = "df695b69e645bce991c5a09a1738f0c8"
SRC_URI[sha256sum] = "bee6bb63bd8f72a9bbefd131785f22f846f806c84bcd3bc2a4cbe6148284c646"


#require dags-dvb-modules-7362.inc

