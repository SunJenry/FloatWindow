package com.sun.floatwindow.basefloat;

import android.os.Build;
import android.support.annotation.StringDef;
import android.text.TextUtils;

import com.blankj.utilcode.util.DeviceUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sun on 2018/7/5.
 */

public class RomUtils {
    private static final String TAG = "RomUtils";

    static final String ROM_MIUI = "MIUI";
    static final String ROM_EMUI = "EMUI";
    static final String ROM_VIVO = "VIVO";
    static final String ROM_OPPO = "OPPO";
    static final String ROM_FLYME = "FLYME";
    static final String ROM_SMARTISAN = "SMARTISAN";
    static final String ROM_QIKU = "QIKU";
    static final String ROM_LETV = "LETV";
    static final String ROM_LENOVO = "LENOVO";
    static final String ROM_NUBIA = "NUBIA";
    static final String ROM_ZTE = "ZTE";
    static final String ROM_COOLPAD = "COOLPAD";
    static final String ROM_UNKNOWN = "UNKNOWN";

    @StringDef({
            ROM_MIUI, ROM_EMUI, ROM_VIVO, ROM_OPPO, ROM_FLYME,
            ROM_SMARTISAN, ROM_QIKU, ROM_LETV, ROM_LENOVO, ROM_ZTE,
            ROM_COOLPAD, ROM_UNKNOWN
    })
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RomName {
    }

    private static final String SYSTEM_VERSION_MIUI = "ro.miui.ui.version.name";
    private static final String SYSTEM_VERSION_EMUI = "ro.build.version.emui";
    private static final String SYSTEM_VERSION_VIVO = "ro.vivo.os.version";
    private static final String SYSTEM_VERSION_OPPO = "ro.build.version.opporom";
    private static final String SYSTEM_VERSION_FLYME = "ro.build.display.id";
    private static final String SYSTEM_VERSION_SMARTISAN = "ro.smartisan.version";
    private static final String SYSTEM_VERSION_LETV = "ro.letv.eui";
    private static final String SYSTEM_VERSION_LENOVO = "ro.lenovo.lvp.version";

    private static String getSystemProperty(String propName) {
        return SystemProperties.get(propName, null);
    }

    @RomName
    public static String getRomName() {
        if (isMiuiRom()) {
            return ROM_MIUI;
        }
        if (isHuaweiRom()) {
            return ROM_EMUI;
        }
        if (isVivoRom()) {
            return ROM_VIVO;
        }
        if (isOppoRom()) {
            return ROM_OPPO;
        }
        if (isMeizuRom()) {
            return ROM_FLYME;
        }
        if (isSmartisanRom()) {
            return ROM_SMARTISAN;
        }
        if (is360Rom()) {
            return ROM_QIKU;
        }
        if (isLetvRom()) {
            return ROM_LETV;
        }
        if (isLenovoRom()) {
            return ROM_LENOVO;
        }
        if (isZTERom()) {
            return ROM_ZTE;
        }
        if (isCoolPadRom()) {
            return ROM_COOLPAD;
        }
        return ROM_UNKNOWN;
    }

    public static String getDeviceManufacture() {
        if (isMiuiRom()) {
            return "小米";
        }
        if (isHuaweiRom()) {
            return "华为";
        }
        if (isVivoRom()) {
            return ROM_VIVO;
        }
        if (isOppoRom()) {
            return ROM_OPPO;
        }
        if (isMeizuRom()) {
            return "魅族";
        }
        if (isSmartisanRom()) {
            return "锤子";
        }
        if (is360Rom()) {
            return "奇酷";
        }
        if (isLetvRom()) {
            return "乐视";
        }
        if (isLenovoRom()) {
            return "联想";
        }
        if (isZTERom()) {
            return "中兴";
        }
        if (isCoolPadRom()) {
            return "酷派";
        }
        return DeviceUtils.getManufacturer();
    }

    public static boolean isMiuiRom() {
        return !TextUtils.isEmpty(getSystemProperty(SYSTEM_VERSION_MIUI));
    }

    public static boolean isHuaweiRom() {
        return !TextUtils.isEmpty(getSystemProperty(SYSTEM_VERSION_EMUI));
    }

    public static boolean isVivoRom() {
        return !TextUtils.isEmpty(getSystemProperty(SYSTEM_VERSION_VIVO));
    }

    public static boolean isOppoRom() {
        return !TextUtils.isEmpty(getSystemProperty(SYSTEM_VERSION_OPPO));
    }

    public static boolean isMeizuRom() {
        String meizuFlymeOSFlag = getSystemProperty(SYSTEM_VERSION_FLYME);
        return !TextUtils.isEmpty(meizuFlymeOSFlag) && meizuFlymeOSFlag.toUpperCase().contains(ROM_FLYME);
    }

    public static boolean isSmartisanRom() {
        return !TextUtils.isEmpty(getSystemProperty(SYSTEM_VERSION_SMARTISAN));
    }

    public static boolean is360Rom() {
        String manufacturer = Build.MANUFACTURER;
        return !TextUtils.isEmpty(manufacturer) && manufacturer.toUpperCase().contains(ROM_QIKU);
    }

    public static boolean isLetvRom() {
        return !TextUtils.isEmpty(getSystemProperty(SYSTEM_VERSION_LETV));
    }

    public static boolean isLenovoRom() {
        return !TextUtils.isEmpty(getSystemProperty(SYSTEM_VERSION_LENOVO));
    }

    public static boolean isCoolPadRom() {
        String model = Build.MODEL;
        String fingerPrint = Build.FINGERPRINT;
        return (!TextUtils.isEmpty(model) && model.toLowerCase().contains(ROM_COOLPAD))
                || (!TextUtils.isEmpty(fingerPrint) && fingerPrint.toLowerCase().contains(ROM_COOLPAD));
    }

    public static boolean isZTERom() {
        String manufacturer = Build.MANUFACTURER;
        String fingerPrint = Build.FINGERPRINT;
        return (!TextUtils.isEmpty(manufacturer) && (fingerPrint.toLowerCase().contains(ROM_NUBIA)
                || fingerPrint.toLowerCase().contains(ROM_ZTE)))
                || (!TextUtils.isEmpty(fingerPrint) && (fingerPrint.toLowerCase().contains(ROM_NUBIA)
                || fingerPrint.toLowerCase().contains(ROM_ZTE)));
    }

    public static boolean isDomesticSpecialRom() {
        return RomUtils.isMiuiRom()
                || RomUtils.isHuaweiRom()
                || RomUtils.isMeizuRom()
                || RomUtils.is360Rom()
                || RomUtils.isOppoRom()
                || RomUtils.isVivoRom()
                || RomUtils.isLetvRom()
                || RomUtils.isZTERom()
                || RomUtils.isLenovoRom()
                || RomUtils.isCoolPadRom();
    }

    public static boolean isSmartisanR1() {
        return Build.MODEL.contains("DE106");
    }

    /**
     * Vivo沙雕刘海屏判断
     *
     * @return
     */
    public static boolean isVivoStupidNotch() {
        return isVivoX21() || isVivoX21S() || isVivoX23() || isVivoZ1() || isVivoZ3() ||
                isVivoY81s() || isVivoY83() || isVivoY85() || isVivoY93() || isVivoY97();
    }

    public static boolean isVivoX21() {
        return Build.MODEL.contains("vivo X21");
    }

    public static boolean isVivoX21S() {
        return Build.MODEL.contains("V1814");
    }

    public static boolean isVivoX23() {
        //X23普通 幻彩版
        return Build.MODEL.contains("V1809") || Build.MODEL.contains("V1816");
    }

    public static boolean isVivoZ1() {
        return Build.MODEL.contains("V1730");
    }

    public static boolean isVivoZ3() {
        return Build.MODEL.contains("V1813BA");
    }

    public static boolean isVivoY81s() {
        return Build.MODEL.contains("V1732");
    }

    public static boolean isVivoY83() {
        return Build.MODEL.contains("Y83");
    }

    public static boolean isVivoY85() {
        return Build.MODEL.contains("vivo Y85");
    }

    public static boolean isVivoY93() {
        return Build.MODEL.contains("V1818");
    }

    public static boolean isVivoY97() {
        return Build.MODEL.contains("V1813A") || Build.MODEL.contains("V1813T");
    }

    public static boolean isHonorV10() {
        return Build.MODEL.contains("BKL-AL00");
    }

    public static boolean isHonor10() {
        return Build.MODEL.contains("COL-AL10");
    }

    public static boolean isMiPad4() {
        return TextUtils.equals(Build.MODEL, "MI PAD 4");
    }
}
