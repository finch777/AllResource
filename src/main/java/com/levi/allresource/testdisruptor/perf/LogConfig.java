package com.levi.allresource.testdisruptor.perf;

import java.sql.Timestamp;

/**
 * ��־���������ļ�
 * @author croot
 */
public class LogConfig {

    private String logFileName;
    private String logFilePath;
    private String saveDateSuffix = "";
    private String saveTimeSuffix = "";
    /**
     * ����ʱ��ӡ�ǣ���¼����ʱ������ֵ���������ڸı�ʱ���ļ������
     */
    private long saveTimeStamp;
    /**
     * �ļ���������
     */
    private int count;
    /**
     * �ļ����������ı�׼
     */
    private int checkCount = 2000;
    /**
     * �ļ����ȱ�׼
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
     * ���һ������һ����
     * @param millis
     * @return
     */
    public static long getEndTimeOfDay(long millis) {
        Timestamp ts = new Timestamp(millis);
        Timestamp endTime = Timestamp.valueOf(ts.toString().substring(0, 11) + "23:59:59");
        return endTime.getTime();
    }
}
