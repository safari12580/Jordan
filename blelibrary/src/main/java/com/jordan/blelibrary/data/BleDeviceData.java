package com.jordan.blelibrary.data;

import android.bluetooth.BluetoothDevice;

/**
 * Created by icean on 2017/2/8.
 */

public final class BleDeviceData {
    private BluetoothDevice mBleDevice;
    private int mRssi;

    public BleDeviceData(BluetoothDevice current_device, int rssi) {
        mBleDevice = current_device;
        mRssi = rssi;
    }

    public BluetoothDevice getmBleDevice() {
        return mBleDevice;
    }

    public int getmRssi() {
        return mRssi;
    }

    @Override
    public String toString() {
        return "BleDeviceData{" +
                "mBleDevice=" + mBleDevice +
                ", mRssi=" + mRssi +
                '}';
    }
}
