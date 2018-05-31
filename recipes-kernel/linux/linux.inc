DESCRIPTION = "Linux Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel siteinfo

PATCHTOOL = "git"
PATCH_COMMIT_FUNCTIONS = "1"

# Specify the commandline for your device

do_configure_prepend() { 
        sed -e '/CONFIG_LOCALVERSION/d' \
            -e '/CONFIG_LOCALVERSION_AUTO/d' \
	    < '${WORKDIR}/defconfig' >>'${B}/.config'

	echo 'CONFIG_LOCALVERSION=""' >>${B}/.config
        echo '# CONFIG_LOCALVERSION_AUTO is not set' >>${B}/.config

        # Check for kernel config fragments.  The assumption is that the config
        # fragment will be specified with the absolute path.  For example:
        #   * ${WORKDIR}/config1.cfg
        #   * ${S}/config2.cfg
        # Iterate through the list of configs and make sure that you can find
        # each one.  If not then error out.
        # NOTE: If you want to override a configuration that is kept in the kernel
        #       with one from the OE meta data then you should make sure that the
        #       OE meta data version (i.e. ${WORKDIR}/config1.cfg) is listed
        #       after the in kernel configuration fragment.
        # Check if any config fragments are specified.
        if [ ! -z "${KERNEL_CONFIG_FRAGMENTS}" ]
        then
            for f in ${KERNEL_CONFIG_FRAGMENTS}
            do  
                # Check if the config fragment was copied into the WORKDIR from
                # the OE meta data
                if [ ! -e "$f" ]
                then
                    echo "Could not find kernel config fragment $f"
                    exit 1
                fi
            done

            # Now that all the fragments are located merge them.
            ( cd ${WORKDIR} && ${S}/scripts/kconfig/merge_config.sh -m -r -O ${B} ${B}/.config ${KERNEL_CONFIG_FRAGMENTS} 1>&2 )
        fi

	yes '' | oe_runmake -C ${S} O=${B} oldconfig
        oe_runmake -C ${S} O=${B} savedefconfig && cp ${B}/defconfig ${WORKDIR}/defconfig.saved
}

# Automatically depend on lzop/lz4-native if CONFIG_KERNEL_LZO/LZ4 is enabled
python () {
    try:
        defconfig = bb.fetch2.localpath('file://defconfig', d)
    except bb.fetch2.FetchError:
        return

    try:
        configfile = open(defconfig)
    except IOError:
        return

    if 'CONFIG_KERNEL_LZO=y\n' in configfile.readlines():
        depends = d.getVar('DEPENDS', False)
        d.setVar('DEPENDS', depends + ' lzop-native')

    if 'CONFIG_KERNEL_LZ4=y\n' in configfile.readlines():
        depends = d.getVar('DEPENDS', False)
        d.setVar('DEPENDS', depends + ' lz4-native')
}
