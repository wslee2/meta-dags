DESCRIPTION = "Tuxbox common files"
LICENSE = "CLOSED"
MAINTAINER = "PLi team"

PR = "r1"

VERSION := "${PV}"
PV = "${VERSION}+svn${SRCPV}"

SRC_URI = "${PLISVNURL}/${PLISVNBRANCH}/cdk/cdk;module=root \
           file://ru_satellites.xml \
           file://satellites.xml \
           file://terrestrial.xml \
           "

FILES_${PN} = "/"

S = "${WORKDIR}"

inherit allarch

TRANSPONDER_LISTS = "satellites.xml terrestrial.xml"

do_install() {
	install -d ${D}/etc/tuxbox/
	install -d ${D}/usr/share/tuxbox
	install -m 0644 ${S}/root/share/tuxbox/scart.conf ${D}/etc/tuxbox/scart.conf

	install -m 0644 ${S}/root/etc/timezone.xml ${D}/etc/tuxbox/timezone.xml
	ln -sf /etc/tuxbox/timezone.xml ${D}/etc/

	ln -sf /usr/share ${D}/share

	for i in ${TRANSPONDER_LISTS}; do
		install -m 0644 ${S}/root/share/tuxbox/$i ${D}/etc/tuxbox/$i
		ln -sf /etc/tuxbox/$i ${D}/etc/;
		ln -sf /etc/tuxbox/$i ${D}/usr/share/;
		ln -sf /etc/tuxbox/$i ${D}/usr/share/tuxbox/;
    done

    install -m 0644 ${S}/terrestrial.xml ${D}/etc/tuxbox
    ${@base_contains("BRAND_TYPE", "worldvision","cp -rf ${S}/ru_satellites.xml ${D}/etc/tuxbox/satellites.xml", "cp -rf ${S}/satellites.xml ${D}/etc/tuxbox",d)}
}
