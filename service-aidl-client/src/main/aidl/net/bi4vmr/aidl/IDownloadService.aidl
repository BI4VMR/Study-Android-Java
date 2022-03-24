package net.bi4vmr.aidl;

interface IDownloadService {
    // 开始下载
    void start(String URL);
    // 获取进度
    double getProgress();
}