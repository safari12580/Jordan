package com.jordan.blelibrary.callback;

import android.bluetooth.BluetoothDevice;

/**
 * Created by icean on 2017/2/8.
 */

public interface BleOperationCallback {
    void onOperationResult(int op_code, int op_result);
    void onDiscoveryDevice(BluetoothDevice current_device);
    void onDescoveryService();
    void onReadCharacteristic(byte[] current_data);
    void onWriteCharacteristic(byte[] current_data);
}
