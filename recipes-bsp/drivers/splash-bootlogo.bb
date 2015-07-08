BRAND_TYPE?="technomate"

DESCRIPTION = "first bootlogo splash image"
SECTION = "base"
PRIORITY = "required"
PACKAGE_ARCH = "${MACHINE_ARCH}"

require conf/license/openpli-gplv2.inc

PV = "1.0"
PR = "r0"

S = "${WORKDIR}/"

SRC_URI = " \
		file://te_splash.bmp \
        file://wo_splash.bmp \
        file://ed_splash.bmp \
		file://splash1.bmp \
		file://splash2.bmp \
		file://splash3.bmp \
        file://7362.splash1.bmp \
        file://iq_splash.bmp \
        file://factory.bmp \
			"

inherit deploy

do_deploy() {
}

do_install() {
	if [ ! -d "${DEPLOY_DIR_IMAGE}" ];then
	   mkdir -p ${DEPLOY_DIR_IMAGE}
	fi

    install -m 0644 ${WORKDIR}/*.bmp ${DEPLOY_DIR_IMAGE}/
    ${@base_contains("CHIP","7362","cp -f ${WORKDIR}/7362.splash1.bmp ${DEPLOY_DIR_IMAGE}/splash1.bmp", "cp -f ${WORKDIR}/splash1.bmp ${DEPLOY_DIR_IMAGE}", d)}
}

addtask deploy before do_build
