package com.jordan.blelibrary.config;

/**
 * Created by icean on 2017/2/8.
 */

public class BleConfig {
    public static final int BLE_SCAN_DEFAULT_TIMEOUT = 10;//扫描超时默认配置（单位：秒）

    public static final int BLE_OP_DISCOVERY_BEGIN = 1000;//开始查找设备
    public static final int BLE_OP_DISCOVERY_END = 1001;//查找设备结束
    public static final int BLE_OP_CONNECT_DEVICE = 2000;//链接设备
    public static final int BLE_OP_CONNECT_SERVICE = 3000;//链接设备中的某个服务
    public static final int BLE_OP_CONNECT_GATT = 4000;//链接设备特征（BLE专有）

    public static final int BLE_OP_RESULT_DISCOVERY_BEGIN_SUCCESS = 1000;
    public static final int BLE_OP_RESULT_DISCOVERY_BEGIN_FALSE = 1001;

    public static final int BLE_OP_RESULT_CONNECT_DEVICE_SUCCESS = 2000;
    public static final int BLE_OP_RESULT_CONNECT_DEVICE_FALSE = 2001;

    public static final int BLE_OP_RESULT_CONNECT_SERVICE_SUCCESS = 3000;
    public static final int BLE_OP_RESULT_CONNECT_SERVICE_FALSE = 3001;

    public static final int BLE_OP_RESULT_CONNECT_GATT_SUCCESS = 4000;
    public static final int BLE_OP_RESULT_CONNECT_GATT_FALSE = 4001;
}
