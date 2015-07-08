do_install() {
    install -d ${D}/usr/bin
    install -m 0755 ${S}/CCcam.${TARGET_ARCH} ${D}/usr/bin/${CAMNAME}
}

