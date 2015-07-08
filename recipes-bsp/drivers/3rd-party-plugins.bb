SUMMARY = "Additional plugins for Enigma2"
MAINTAINER = "oe-alliance team"
PACKAGE_ARCH = "${MACHINE_ARCH}"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=8e37f34d0e40d32ea2bc90ee812c9131"

PACKAGES_DYNAMIC = "enigma2-plugin-(?!pli-).*"

PACKAGES += " \
    "

PROVIDES += " \
    enigma2-plugin-systemplugins-autobouquetsmaker \
     "
#    enigma2-plugin-systemplugins-transcodingsetup
#enigma2-plugin-extensions-ondemand
#enigma2-plugin-extensions-dlnabrowser
#enigma2-plugin-extensions-dlnaserver

DEPENDS = "\
    python-lxml python-simplejson python-pyusb \
    djmount \
    librtmp \
    ppp \
    wvdial \
    wvstreams \
    usbutils \
    "
#python-dnspython
#python-beautifulsoup
#python-pyamf
#python-icalendar
#usbmodeswitch
#usbmodeswitch-data
#hddtemp - it's very slow.

DESCRIPTION_enigma2-plugin-systemplugins-autobouquetsmaker = "Automatically build and update bouquets from the satellite stream."
RREPLACES_enigma2-plugin-systemplugins-autobouquetsmaker = "enigma2-plugin-extensions-autobouquets"
RCONFLICTS_enigma2-plugin-systemplugins-autobouquetsmaker = "enigma2-plugin-extensions-autobouquets"
DESCRIPTION_enigma2-plugin-extensions-dlnabrowser = "this is dlna/upnp browser using djmount"
RDEPENDS_enigma2-plugin-extensions-dlnabrowser = "djmount fuse-utils libfuse2 libupnp3 libneon27"
DESCRIPTION_enigma2-plugin-extensions-dlnaserver = "this is dlna server using minidlna"
RDEPENDS_enigma2-plugin-extensions-dlnaserver = "minidlna"
#RDEPENDS_enigma2-plugin-extensions-ondemand = "python-dnspython python-beautifulsoup python-lxml python-simplejson python-pyamf"
DESCRIPTION_enigma2-plugin-extensions-ondemand = "Watch on demand TV."

inherit autotools-brokensep gitpkgv pythonnative

SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"
PR = "r3"

SRC_URI = "git://github.com/MOA-2011/3rd-party-plugins.git;protocol=git"

EXTRA_OECONF = " \
    BUILD_SYS=${BUILD_SYS} \
    HOST_SYS=${HOST_SYS} \
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR} \
    --with-boxtype=${MACHINE} \
    --with-arch=${TARGET_ARCH} \
    "

ALLOW_EMPTY_${PN} = "1"
PACKAGES += "${PN}-meta"
FILES_${PN}-meta = "${datadir}/meta"

S = "${WORKDIR}/git"

python populate_packages_prepend() {
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', '%s', recursive=True, match_path=True, prepend=True, extra_depends="enigma2")
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.py$', 'enigma2-plugin-%s-src', '%s (source files)', recursive=True, match_path=True, prepend=True, extra_depends="enigma2")
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.la$', 'enigma2-plugin-%s-dev', '%s (development)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.a$', 'enigma2-plugin-%s-staticdev', '%s (static development)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/(.*/)?\.debug/.*$', 'enigma2-plugin-%s-dbg', '%s (debug)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\/.*\.po$', 'enigma2-plugin-%s-po', '%s (translations)', recursive=True, match_path=True, prepend=True)
}
