SUMMARY = "3rd Party plugins for Enigma2"
MAINTAINER = "4d team"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=d3b0f3ed78ab42bd104edcc5f55bef35"

inherit gitpkgv pythonnative pkgconfig deploy

SRCREV = "${AUTOREV}"
#PV = "r1"
#PKGV = "git${GITPKGV}"
PR = "r1"

SRC_URI = "git://github.com/MOA-2011/enigma2-3rdparty-plugins.git"

EXTRA_OECONF = " \
    BUILD_SYS=${BUILD_SYS} \
    HOST_SYS=${HOST_SYS} \
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR} \
"

ALLOW_EMPTY_${PN} = "1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"
DEPLOY_DIR = "${TMPDIR}/deploy"

THIRDPARTY_PLUGINS = " \
    enigma2-plugin-extensions-epanel_5.5-r1_mips32el.ipk \
    enigma2-plugin-extensions-permanenttimeshift_1.0+git20+529c5d8-r0_mips32el.ipk \
    enigma2-plugin-extensions-hbbtv_git810+6d4c446-r7_mips32el.ipk \
    "

do_install() {
}

python populate_packages_prepend () {
    p = ""
    plugins = bb.data.getVar('THIRDPARTY_PLUGINS', d, 1)
    if bb.data.getVar('THIRDPARTY_MACHINE_PLUGINS', d, 1) is not None:
        plugins += bb.data.getVar('THIRDPARTY_MACHINE_PLUGINS', d, 1)
    if bb.data.getVar('THIRDPARTY_EXTRA_PLUGINS', d, 1) is not None:
        plugins += bb.data.getVar('THIRDPARTY_EXTRA_PLUGINS', d, 1)

    if plugins is not None:
        for package in plugins.split():
            p += package.split('_')[0] + " "

    bb.data.setVar('PACKAGES', p, d)
}

do_deploy() {
    install -d 0755 ${WORKDIR}/deploy-ipk/mips32el

    for i in ${THIRDPARTY_PLUGINS}; do
        if [ -f $i ]; then
            install -m 0644 $i ${WORKDIR}/deploy-ipk/mips32el;
            install -m 0644 $i ${DEPLOY_DIR}/ipk/mips32el;
        fi
    done;

    pkgdir=${DEPLOY_DIR_IPK}/mips32el
    if [ -e $pkgdir ]; then
        chmod 0755 $pkgdir
    fi
}

addtask do_deploy before do_package_write after do_package_write_ipk
