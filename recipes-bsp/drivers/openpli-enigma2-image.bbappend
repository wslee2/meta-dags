BRAND_TYPE?="technomate"

include openpli-dvb-usb.bb

RRECOMMENDS_${PN} += " \
        python-pycrypto \
        gst-plugins-ugly-cdio \
        gst-plugins-bad-rtmp \
        gst-plugins-bad-fragmented \
        gst-plugins-ugly-asf \
        gst-plugins-bad-vcdsrc \
        gst-plugins-bad-cdxaparse \
        gst-plugins-good-flv \
        gst-plugins-bad-mms \
        python-json \
        python-pyopenssl \
        python-compression \
        python-html \
        python-textutils \
        "

RDEPENDS += " \
            dags-shutdown \
			splash-bootlogo \
            3rd-party-plugins \
			\
			transtreamproxy \
			filestreamproxy \
            gst-ffmpeg \
			dvb-usb-drivers-meta \
			enigma2-plugin-drivers-dvb-usb-dvbsky \
            enigma2-plugin-extensions-backupsuite \
            enigma2-plugin-extensions-epanel \
            enigma2-plugin-extensions-permanenttimeshift \
			enigma2-plugin-systemplugins-networkbrowser \
			enigma2-plugin-extensions-audiosync \
            enigma2-plugin-pli-softcamsetup \
            enigma2-plugin-softcams-cccam-config \            
            enigma2-plugin-softcams-newcs-config \
            \
            libav \
            ntp \
            x264 \
            busybox-cron \
            mpfr \
            tslib \
            "

ENIGMA2_PLUGINS += " \
                    enigma2-plugin-systemplugins-networkwizard \
                    enigma2-plugin-systemplugins-wirelesslan \
                    enigma2-plugin-extensions-permanenttimeshift \
                    enigma2-plugin-systemplugins-autobouquetsmaker \
                    \
                    enigma2-plugin-systemplugins-videoenhancement \
                    enigma2-plugin-systemplugins-satelliteequipmentcontrol \
		    enigma2-plugin-systemplugins-crossepg \
                    enigma2-plugin-extensions-cooltvguide \
                    enigma2-plugin-extensions-mytube \
		    enigma2-plugin-extensions-dvdplayer \
		    enigma2-plugin-systemplugins-networkbrowser \
		    enigma2-plugin-extensions-audiosync \
                    enigma2-plugin-pli-softcamsetup \
                    enigma2-plugin-drivers-dvb-usb-dvbsky \
                    enigma2-plugin-softcams-cccam-config \            
                    enigma2-plugin-softcams-newcs-config \
		    enigma2-plugin-extensions-ngsetting \
                    "

ENIGMA2_OPTIONAL += " \
                softcams-enigma2-meta \
                    "

IMAGE_INSTALL += " \
				  ${ENIGMA2_PLUGINS} \
                  tslib \
                  mpfr \
                  gst-ffmpeg \
                  python-gdata \
                  python-textutils \
                  ntpdate \
                  tuxbox-links \
		          kernel-params \
		          busybox-cron \
				  transtreamproxy \
				  filestreamproxy \
				  hbbtv-dumpait \
                  opera-browser-util \
                  enigma2-plugin-softcams-newcs \
                  enigma2-plugin-softcams-mgcamd \
                  enigma2-plugin-softcams-cccam221 \
				  enigma2-plugin-extensions-permanenttimeshift \
				  enigma2-plugin-drivers-dvb-usb-dvbsky \
                  enigma2-plugin-extensions-hbbtv \
				  \
                  enigma2-plugin-systemplugins-autobouquetsmaker \
                  enigma2-plugin-systemplugins-tempfancontrol \
                  \
				  ${@base_contains("MACHINE_FEATURES", "dvbusb", "${DVB_USB_DRIVERS}", "",d)} \
				  ${@base_contains("BRAND_TYPE", "worldvision","\
                  enigma2-plugin-extensions-backupsuite \
                  enigma2-plugin-extensions-epanel \
                  enigma2-plugin-systemplugins-3gmodemmanager \
                  enigma2-plugin-systemplugins-mountmanager", "",d)} \ 
				  \
				  ${@base_contains("BRAND_TYPE", "technomate", "\
			      enigma2-plugin-extensions-remotechannelstreamconverter \
			      enigma2-plugin-extensions-ngsetting", "",d)} \
                  \
                  dags-shutdown \
				  parted \
				  openssl \
                  libav \
                  mtd-utils \
                  mtd-utils-ubifs \
                  "
#            channelsettings-enigma2-meta
