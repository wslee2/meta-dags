DISTRO_FEED_PREFIX="http://en3homeftp.net/Openembedded2/OpenPLi4/"

do_compile() {
    mkdir -p ${S}/${sysconfdir}/opkg
    for feed in all ${PACKAGE_EXTRA_ARCHS} ${MACHINE_ARCH}; do
        echo "src/gz openpli-${feed} ${DISTRO_FEED_PREFIX}${feed}" > ${S}/${sysconfdir}/opkg/${feed}-feed.conf
    done
}
do_install () {
    install -d ${D}${sysconfdir}/opkg
    install -m 0644 ${S}/${sysconfdir}/opkg/* ${D}${sysconfdir}/opkg/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

CONFFILES_${PN} += '${@ " ".join( [ ( "${sysconfdir}/opkg/%s-feed.conf" % feed ) for feed in "all ${PACKAGE_EXTRA_ARCHS} ${MACHINE_ARCH}".split() ] ) }'
