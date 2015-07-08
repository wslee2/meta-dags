BRAND_TYPE?="technomate"
BRAND_TYPE?="technomate"
BRAND_TYPE?="technomate"
BRAND_TYPE?="technomate"
BRAND_TYPE?="technomate"
BRAND_TYPE?="worldvision"

include openpli-dvb-usb.bb

RDEPENDS += " \
            dags-shutdown \
			splash-bootlogo \
			\
            gst-ffmpeg \
			dvb-usb-drivers-meta \
			enigma2-plugin-drivers-dvb-usb-dvbsky \
            enigma2-plugin-extensions-backupsuite \
            enigma2-plugin-extensions-permanenttimeshift \
            channelsettings-enigma2-meta \
            enigma2-plugin-extensions-epanel \
            opera-browser-util \
            opera-dumpait \
            \
            libav \
            ntp \
            x264 \
            busybox-cron \
            mpfr \
            tslib \
            channelsettings-enigma2-meta \
            oe-factory \
            "

ENIGMA2_PLUGINS += " \
                    enigma2-plugin-systemplugins-networkwizard \
                    enigma2-plugin-systemplugins-wirelesslan \
                    enigma2-plugin-extensions-permanenttimeshift \
                    enigma2-plugin-systemplugins-networkbrowser \
                    enigma2-plugin-systemplugins-autobouquetsmaker \
                    \
                    enigma2-plugin-systemplugins-videoenhancement \
                    enigma2-plugin-systemplugins-satelliteequipmentcontrol \
					enigma2-plugin-systemplugins-crossepg \
                    enigma2-plugin-extensions-cooltvguide \
                    enigma2-plugin-extensions-mytube \
					\
                    "

ENIGMA2_OPTIONAL += " \
                softcams-enigma2-meta \
                    "

IMAGE_INSTALL += " \
                  tslib \
                  mpfr \
                  gst-ffmpeg \
                  python-gdata \
                  python-textutils \
                  ntpdate \
                  tuxbox-links \
		          kernel-params \
		          busybox-cron \
				  \
                  opera-dumpait \
                  opera-browser-util \
                  enigma2-plugin-softcams-newcs \
                  enigma2-plugin-softcams-mgcamd \
                  enigma2-plugin-softcams-cccam221 \
				  enigma2-plugin-drivers-dvb-usb-dvbsky \
                  enigma2-plugin-extensions-hbbtv \
				  \
                  enigma2-plugin-systemplugins-autobouquetsmaker \
				  ${@base_contains("MACHINE_FEATURES", "dvbusb", "${DVB_USB_DRIVERS}", "",d)} \
				  ${@base_contains("BRAND_TYPE", "worldvision","\
                  enigma2-plugin-skins-force1 \
                  enigma2-plugin-extensions-backupsuite \
                  enigma2-plugin-extensions-epanel \
                  enigma2-plugin-systemplugins-3gmodemmanager \
                  enigma2-plugin-systemplugins-mountmanager", "",d)} \ 
                  \
                  \
                  oe-factory \
                  dags-shutdown \
				  parted \
				  openssl \
                  libav \
                  mtd-utils \
                  "
