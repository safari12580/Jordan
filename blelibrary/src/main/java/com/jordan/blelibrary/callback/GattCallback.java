package com.jordan.blelibrary.callback;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothProfile;

import com.jordan.blelibrary.config.BleConfig;

/**
 * Created by icean on 2017/2/8.
 */

public class GattCallback extends BluetoothGattCallback {

    private BleOperationCallback mOpCallback;

    public GattCallback(BluetoothGatt current_gatt, BleOperationCallback op_callback) {
        mOpCallback = op_callback;
    }

    @Override
    public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
        super.onConnectionStateChange(gatt, status, newState);
        if (newState == BluetoothProfile.STATE_CONNECTED) {
            //Notification client;
            if (null != mOpCallback) {
                mOpCallback.onOperationResult(BleConfig.BLE_OP_CONNECT_DEVICE, BleConfig.BLE_OP_RESULT_CONNECT_DEVICE_SUCCESS);
            }
        } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
            if (null != mOpCallback) {
                mOpCallback.onOperationResult(BleConfig.BLE_OP_CONNECT_DEVICE, BleConfig.BLE_OP_RESULT_CONNECT_DEVICE_FALSE);
            }
        }
    }

    @Override
    public void onServicesDiscovered(BluetoothGatt gatt, int status) {
        super.onServicesDiscovered(gatt, status);
        if (!(BluetoothGatt.GATT_SUCCESS == status)) {
            if (null != mOpCallback) {
                mOpCallback.onOperationResult(BleConfig.BLE_OP_CONNECT_DEVICE, BleConfig.BLE_OP_RESULT_CONNECT_DEVICE_FALSE);
            }
        } else {
            if (null != mOpCallback) {
                mOpCallback.onOperationResult(BleConfig.BLE_OP_CONNECT_DEVICE, BleConfig.BLE_OP_RESULT_CONNECT_DEVICE_SUCCESS);
            }
        }

    }

    @Override
    public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
        super.onCharacteristicRead(gatt, characteristic, status);
    }

    @Override
    public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
        super.onCharacteristicWrite(gatt, characteristic, status);
    }

    @Override
    public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
        super.onCharacteristicChanged(gatt, characteristic);
    }
}
