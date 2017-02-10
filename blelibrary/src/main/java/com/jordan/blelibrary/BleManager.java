package com.jordan.blelibrary;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;

import com.jordan.blelibrary.callback.BleOperationCallback;
import com.jordan.blelibrary.callback.DeviceScanCallback;
import com.jordan.blelibrary.callback.GattCallback;
import com.jordan.blelibrary.config.BleConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by icean on 2017/2/8.
 */

public class BleManager {

    private Context mContext;
    private BluetoothManager mBluetoothManager;//蓝牙管理类
    private BluetoothAdapter mBluetoothAdapter;//蓝牙核心组件
    private BluetoothGatt mBluetoothGatt;//蓝牙服务
    private BluetoothGattCharacteristic mTargetGattCharacteristic;//蓝牙特征
    private BluetoothGattCharacteristic mSppGattCharacteristic;//SPP特征

    private BleOperationCallback mOpCallback;
    private DeviceScanCallback mScanCallback;
    private GattCallback mGattCallback;


    private Handler mBleHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case BleConfig.BLE_OP_DISCOVERY_END:
                    mBluetoothAdapter.stopLeScan(mScanCallback);
                    mIsScanDevice = false;
                    break;
            }
        }
    };

    private boolean mHasBleFeature;
    private boolean mIsScanDevice;

    public BleManager(Context context) {
        mContext = context;
        mBluetoothManager = (BluetoothManager)context.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();
        mHasBleFeature = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
    }

    public boolean isTurnOn(){
        return mBluetoothAdapter.isEnabled();
    }

    public boolean startScanLEDevice(int scan_timeout){
        if (!mHasBleFeature)
            return false;

        //If bluetooth device is scanning now, we need to stop it
        if (mIsScanDevice) {
            mIsScanDevice = false;
            mBluetoothAdapter.stopLeScan(mScanCallback);
        }
        //Start scan device
        boolean start_result = mBluetoothAdapter.startLeScan(mScanCallback);
        mIsScanDevice = true;
        if (null != mOpCallback) {
            mOpCallback.onOperationResult(BleConfig.BLE_OP_DISCOVERY_BEGIN,
                    start_result ? BleConfig.BLE_OP_RESULT_DISCOVERY_BEGIN_SUCCESS : BleConfig.BLE_OP_RESULT_DISCOVERY_BEGIN_FALSE);
        }

        //After some seconds, we need to stop it
        int timeout = scan_timeout == -1 ? BleConfig.BLE_SCAN_DEFAULT_TIMEOUT : scan_timeout;
        mBleHandler.sendEmptyMessageDelayed(BleConfig.BLE_OP_DISCOVERY_END, timeout);

        return start_result;
    }

    public void connectToDevice(BluetoothDevice current_device) {
        if (!mHasBleFeature) {
            if (null != mOpCallback) {
                mOpCallback.onOperationResult(BleConfig.BLE_OP_CONNECT_DEVICE, BleConfig.BLE_OP_RESULT_CONNECT_DEVICE_FALSE);
            }
            return;
        }

        mBluetoothGatt = current_device.connectGatt(mContext, false, mGattCallback);
    }

    public void disconnectToDevice(){
        if (!mHasBleFeature) {
            if (null != mOpCallback) {
                mOpCallback.onOperationResult(BleConfig.BLE_OP_CONNECT_DEVICE, BleConfig.BLE_OP_RESULT_CONNECT_DEVICE_FALSE);
            }
            return;
        }

        if (null != mBluetoothGatt) {
            mBluetoothGatt.discoverServices();
            mBluetoothGatt.disconnect();
        }
    }

    public void findServicesInGatt() {
        if (!mHasBleFeature) {
            return;
        }
        if (null != mBluetoothGatt) {
            mBluetoothGatt.discoverServices();
        }
    }

    public List<BluetoothGattService> getServicesInGatt() {
        if (null == mBluetoothGatt) {
            return null;
        } else {
            return mBluetoothGatt.getServices();
        }
    }

    public HashMap<BluetoothGattService, List<BluetoothGattCharacteristic>> getServiceMap(ArrayList<BluetoothGattService> all_services){
        if (null == all_services || all_services.size() == 0) {
            return null;
        }
        HashMap<BluetoothGattService, List<BluetoothGattCharacteristic>> service_map = new HashMap<>();
        for (BluetoothGattService current_service : all_services){
            List<BluetoothGattCharacteristic> all_characteristics = current_service.getCharacteristics();
            service_map.put(current_service, all_characteristics);
        }
        return service_map;
    }

    public void setNotificationToCharacteristic(BluetoothGattCharacteristic current_characteristic) {
        mTargetGattCharacteristic = current_characteristic;
        if (null != mBluetoothGatt)
            mBluetoothGatt.setCharacteristicNotification(mTargetGattCharacteristic, true);
    }

    public void readCharacteristic(){
        if (null != mBluetoothGatt)
            mBluetoothGatt.readCharacteristic(mTargetGattCharacteristic);
    }

    public void writeCharacteristic(){
        if (null != mBluetoothGatt)
            mBluetoothGatt.readCharacteristic(mTargetGattCharacteristic);
    }

    public void writeSppCharacteristic(){
        if (null != mBluetoothGatt && null != mSppGattCharacteristic) {
            mBluetoothGatt.writeCharacteristic(mSppGattCharacteristic);
        }
    }



    public boolean hasBleFeature(){
        return mHasBleFeature;
    }

    public void setScanDeviceCallback(DeviceScanCallback scan_callback) {
        mScanCallback = scan_callback;
    }

    public void setBleOpCallback(BleOperationCallback op_callback) {
        mOpCallback = op_callback;
        if (null != mScanCallback)
            mScanCallback.setOpCallBack(mOpCallback);
    }

    public void setGattCallback(GattCallback callback) {
        mGattCallback = callback;
    }
}
