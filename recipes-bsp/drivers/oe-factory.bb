DESCRIPTION = "factory image entered file"
LICENSE = "CLOSED"
PR = "r1"
S = "${WORKDIR}"

do_install() {
	mkdir -p ${D}/etc/
	touch ${D}/etc/.run_factory_test
}
