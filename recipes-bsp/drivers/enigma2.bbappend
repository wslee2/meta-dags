BRAND_TYPE?="iqon"

ENIGMA2_BRANCH ?= "master"
SRC_URI="git://192.168.1.158/enigma2.pli4.0.git;branch=${ENIGMA2_BRANCH} \
         file://PPanel_tutorial.xml \
         file://CCcam.xml \
         file://enigma2_pre_start.sh \
         file://enigma2.sh \
         file://enigma2_end.sh \
         file://e2settings \
         file://factory.var \
         file://var \
		 file://profile \
"

S="${WORKDIR}/git"

do_configure_prepend() {
# DAGS remocon copy files.
}

do_install_append() {
    install -d 0755 ${D}/etc/
    install -d 0755 ${D}/usr/bin/
    install -d 0755 ${D}/usr/lib/
    install -d 0755 ${D}/etc/tuxbox/
    install -d 0755 ${D}/etc/ppanels/
    install -d 0755 ${D}/etc/enigma2/

    touch ${D}/etc/.brandtype
    echo ${BRAND_TYPE} > ${D}/etc/.brandtype
    touch ${D}/etc/.rcu

    ln -s /usr/bin/opkg ${D}/usr/bin/ipkg
    ln -s /usr/lib/opkg ${D}/usr/lib/ipkg
    
    install -m 0755 ${WORKDIR}/enigma2_pre_start.sh ${D}/usr/bin
    install -m 0755 ${WORKDIR}/enigma2.sh ${D}/usr/bin
    install -m 0755 ${WORKDIR}/enigma2_end.sh ${D}/usr/bin
    install -m 0755 ${WORKDIR}/factory.var ${D}/etc/factory.var.tar
    install -m 0755 ${WORKDIR}/e2settings ${D}/etc/.e2settings.tar
    install -m 0755 ${WORKDIR}/var ${D}/etc/var.tar
    install -m 0755 ${WORKDIR}/PPanel_tutorial.xml ${D}/etc/ppanels
    install -m 0755 ${WORKDIR}/profile ${D}/etc/enigma2


    touch ${D}/etc/.rcu

    # Current not applied under line.
    if [ "${MACHINE}" = "force1plus" -o "${MACHINE}" = "tmnano3tcombo" ];then
        ## rcu : tm-2t = 1, tm-twin = 2, iqon = 3, tm-single = 4, worldvision = 5(3)
        if [ "${BRAND_TYPE}" = "technomate" ]; then
            echo "4"  > ${D}/etc/.rcu
        else
            echo "3" > ${D}/etc/.rcu
        fi
    fi
    
## under comment is MegaSat Branding
#    if [ "${BRAND_TYPE}" == "iqon" ]; then
#       if [ "${MACHINE}" == "force2" ]; then
#          cp ${WORKDIR}/menu.xml ${D}/usr/share/enigma2/
#       fi
#    else
        install -m 0755 ${WORKDIR}/CCcam.xml ${D}/etc/ppanels/
#    fi 

    # Not implement input rcu-configure pic
}
