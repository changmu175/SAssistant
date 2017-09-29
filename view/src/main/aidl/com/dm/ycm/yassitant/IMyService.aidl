// IMyService.aidl
package com.dm.ycm.yassitant;

// Declare any non-default types here with import statements

interface IMyService {
    int sendMSG(String topic,String message);
    void isRunningNPCPackage(String packageName);
    boolean isNeedRestart(boolean isNeed);
}
