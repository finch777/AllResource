package com.levi.algorithm_practice.testdisruptor.perf;

import java.sql.Timestamp;

/**
 * 日志类型配置文件
 * @author croot
 */
public class LogConfig {

    private String logFileName;
    private String logFilePath;
    private String saveDateSuffix = "";
    private String saveTimeSuffix = "";
    /**
     * 保存时间印记，记录当天时间的最大值，用于日期改变时的文件名变更
     */
    private long saveTimeStamp;
    /**
     * 文件检查计数器
     */
    private int count;
    /**
     * 文件检查计数器的标准
     */
    private int checkCount = 2000;
    /**
     * 文件长度标准
     */
    private long checkFileSize = 50000000;

    public LogConfig() {
        saveTimeStamp = getEndTimeOfDay(System.currentTimeMillis());
    }

    public String getLogFileName() {
        return logFileName;
    }
    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }
    public String getLogFilePath() {
        return logFilePath;
    }
    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }
    public String getSaveDateSuffix() {
        return saveDateSuffix;
    }
    public void setSaveDateSuffix(String saveDateSuffix) {
        this.saveDateSuffix = saveDateSuffix;
    }
    public String getSaveTimeSuffix() {
        return saveTimeSuffix;
    }
    public void setSaveTimeSuffix(String saveTimeSuffix) {
        this.saveTimeSuffix = saveTimeSuffix;
    }
    public long getSaveTimeStamp() {
        return saveTimeStamp;
    }
    public void setSaveTimeStamp(long saveTimeStamp) {
        this.saveTimeStamp = saveTimeStamp;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int addCountAddGet() {
        this.count++;
        return count;
    }
    public int getCheckCount() {
        return checkCount;
    }
    public void setCheckCount(int checkCount) {
        this.checkCount = checkCount;
    }
    public long getCheckFileSize() {
        return checkFileSize;
    }
    public void setCheckFileSize(long checkFileSize) {
        this.checkFileSize = checkFileSize;
    }

    /**
     * 获得一天的最后一毫秒
     * @param millis
     * @return
     */
    public static long getEndTimeOfDay(long millis) {
        Timestamp ts = new Timestamp(millis);
        Timestamp endTime = Timestamp.valueOf(ts.toString().substring(0, 11) + "23:59:59");
        return endTime.getTime();
    }
}
