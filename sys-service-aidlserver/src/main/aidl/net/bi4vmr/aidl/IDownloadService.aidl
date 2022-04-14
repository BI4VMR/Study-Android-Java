package net.bi4vmr.aidl;

import net.bi4vmr.aidl.ItemBean;

interface IDownloadService {
    // 开始下载
    void start(String URL);
    // 获取进度
    double getProgress();
    //
    void addTask(in ItemBean item);
    //
    List<ItemBean> getTask();
}