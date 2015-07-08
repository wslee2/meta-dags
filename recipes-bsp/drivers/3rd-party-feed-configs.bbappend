DISTRO_FEED_PREFIX="http://en3homeftp.net/Openembedded2/OpenPLi4/"
#FEEDS = "3rd-party 3rd-party-${MACHINE}"
FEEDS = "3rd-party"

do_compile() {
    [ ! -d ${S}/${sysconfdir}/opkg ] && mkdir -p ${S}/${sysconfdir}/opkg
    for feed in ${FEEDS}; do
        echo "src/gz openpli-${feed} ${DISTRO_FEED_PREFIX}${feed}" > ${S}/${sysconfdir}/opkg/${feed}-feed.conf
    done
}
do_install() {
        install -d ${D}${sysconfdir}/opkg
        install -m 0644 ${S}/${sysconfdir}/opkg/* ${D}${sysconfdir}/opkg/
}
PACKAGE_EXTRA_ARCHS = "${MACHINE_ARCH}"

CONFFILES_${PN} += '${@ " ".join( [ ( "${sysconfdir}/opkg/%s-feed.conf" % feed ) for feed in "${FEEDS}".split() ] ) }'
