do_install_append() {
	install -d ${D}/lib
	ln -s libcrypto.so.0.9.8 ${D}/lib/libcrypto.so.0.9.7
	ln -s libssl.so.0.9.8 ${D}/lib/libssl.so.0.9.7
}
