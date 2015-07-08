BRAND_TYPE?="iqon"

MODULE = "OpenWebif"
DESCRIPTION = "Control your receiver with a browser"
LICENSE = "GPLv2"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LIC_FILES_CHKSUM = "file://README;firstline=10;lastline=12;md5=9c14f792d0aeb54e15490a28c89087f7"

DEPENDS = "python-cheetah-native"
RDEPENDS_${PN} = "python-textutils python-cheetah python-json python-unixadmin python-misc python-pyopenssl python-shell aio-grab python-compression"

inherit gitpkgv
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

DEPENDS += "enigma2"

SRC_URI = "git://github.com/MOA-2011/enigma2-plugin-extensions-openwebif.git;protocol=git \
        ${@base_contains("BRAND_TYPE","iqon","file://iq_${MACHINE}.jpg","", d)} \
        ${@base_contains("BRAND_TYPE","worldvision","file://wo_${MACHINE}.jpg","",d)} \
        ${@base_contains("BRAND_TYPE","technomate","file://${MACHINE}.jpg","",d)} \
        file://ed_op1plus.jpg \
        file://ed_op2plus.jpg \
        file://ed_op3plus.jpg \
        file://edision.png \
        file://ed_type0.png \
        file://iqon.png \
        file://sw_type0.png \
        file://te_type0.png \
        file://te_type1.png \
        file://te_type2.png \
        file://te_type3.png \
        file://te_type4.png \
        file://wo_type0.png \
        file://ed_type1.png \
        file://fusionhd.png \
        file://edision.html \
        file://ed_type0.html \
        file://iqon.html \
        file://sw_type0.html \
        file://te_type0.html \
        file://te_type1.html \
        file://te_type2.html \
        file://te_type3.html \
        file://te_type4.html \
        file://wo_type0.html \
        file://ed_type1.html \
        file://iq_force2.html \
        file://fusionhd.html \
        file://iq_force2.png \
        file://iq_force2solid.html \
        file://iq_force2solid.png \
        file://iq_force2plus.jpg \
        file://iq_force2eco.jpg \
        file://optimussos.jpg \
        file://optimussos3plus.jpg \
        file://te_tmnano2super.jpg \
        file://tmnanosecombo.jpg \
        file://ed_force1plus.jpg \
        file://tmnanosem2.jpg \
        file://fusionhd.jpg \
        file://ru.po \
        "

S="${WORKDIR}/git"

SRCREV_pn-${PN} ?= "${AUTOREV}"

do_configure_prepend() {
    cp ${WORKDIR}/ru.po ${S}/locale
}
# Just a quick hack to "compile" it
do_compile() {
#	cheetah-compile -R --nobackup ${S}/plugin
    python -O -m compileall ${S}
}

PLUGINPATH = "/usr/lib/enigma2/python/Plugins/Extensions/${MODULE}"

do_install_append() {
    install -d ${D}${PLUGINPATH}

    for f in $(find ${S}/locale -name *.po );do
        l=$(echo ${f%} | sed 's/\.po//' | sed 's/.*locale\///')
        mkdir -p ${D}${PLUGINPATH}/locale/${l%}/LC_MESSAGES
        msgfmt -o ${D}${PLUGINPATH}/locale/${l%}/LC_MESSAGES/OpenWebif.mo ${S}/locale/$l.po 
    done

    cp -rp ${S}/plugin/* ${D}${PLUGINPATH}
    cp -rf ${WORKDIR}/*.html ${D}${PLUGINPATH}/public/static/remotes/
    cp -rf ${WORKDIR}/*.png ${D}${PLUGINPATH}/public/images/remotes/

    if [ "${BRAND_TYPE}" = "technomate" ];then
        if [ "${MACHINE}" = "force2plus" ];then
            cp -rf ${WORKDIR}/${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/
        elif [ "${MACHINE}" = "tmnano2super" ];then
            cp -rf ${WORKDIR}/te_${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/
        else
            cp -rf ${WORKDIR}/${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/
        fi
    elif [ "${BRAND_TYPE}" = "worldvision" ];then
        cp -rf ${WORKDIR}/wo_${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/wo_${MACHINE}.jpg
    elif [ "${BRAND_TYPE}" = "edision" ];then
        if [ "${MACHINE}" = "optimussos1plus" ];then
            cp -rf ${WORKDIR}/ed_op1plus.jpg ${D}${PLUGINPATH}/public/images/boxes/ed_op1plus.jpg
        elif [ "${MACHINE}" = "optimussos2plus" ]; then
            cp -rf ${WORKDIR}/ed_op2plus.jpg ${D}${PLUGINPATH}/public/images/boxes/ed_op2plus.jpg
        elif [ "${MACHINE}" = "force1plus" ]; then
            cp -rf ${WORKDIR}/ed_force1plus.jpg ${D}${PLUGINPATH}/public/images/boxes/ed_force1plus.jpg
        elif [ "${MACHINE}" = "optimussos3plus" ]; then
            cp -rf ${WORKDIR}/ed_force1plus.jpg ${D}${PLUGINPATH}/public/images/boxes/ed_force1plus.jpg
        else
            cp -rf ${WORKDIR}/${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/
        fi
    elif [ "${BRAND_TYPE}" = "xsarius" ];then
        cp -rf ${WORKDIR}/${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/
    elif [ "${BRAND_TYPE}" = "iqon" ]; then
        if [ "${MACHINE}" = "force2" ]; then
            cp -rf ${WORKDIR}/iq_${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/
        else
            cp -rf ${WORKDIR}/iq_${MACHINE}.jpg ${D}${PLUGINPATH}/public/images/boxes/iq_${MACHINE}.jpg
        fi
    fi
}

FILES_${PN} = "${PLUGINPATH}"
