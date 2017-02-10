package com.jordan.blelibrary.callback;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import com.jordan.blelibrary.data.BleDeviceData;

import java.util.ArrayList;

/**
 * Created by icean on 2017/2/8.
 */

public final class DeviceScanCallback implements BluetoothAdapter.LeScanCallback {

    private Activity mContext;
    private ArrayList<BluetoothDevice> mAllBleDevices;
    private BleOperationCallback mOpCallback;

    public DeviceScanCallback(Activity context) {
        mContext = context;
        mAllBleDevices = new ArrayList<BluetoothDevice>();
    }

    public DeviceScanCallback(Activity context, BleOperationCallback callback) {
        this(context);
        mOpCallback = callback;
    }

    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
        if (null == mAllBleDevices) {
            mAllBleDevices = new ArrayList<>();
        }

        if (!mAllBleDevices.contains(device)) {
            mAllBleDevices.add(device);
        }

        if (null != mOpCallback)
            mOpCallback.onDiscoveryDevice(device);
    }

    public void setOpCallBack(BleOperationCallback callBack) {
        mOpCallback = callBack;
    }

    public ArrayList<BluetoothDevice> getAllDevices(){
        return mAllBleDevices;
    }
}
