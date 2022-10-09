package com.levi.allresource.testdisruptor.perf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 * ��־
 * @author croot
 */
public class LogWriter {
    private PrintWriter printer;
    protected LogConfig logConfig;

    public LogWriter(LogConfig logConfig) {
        this.logConfig = logConfig;
    }

    /**
     * Write the information into the log file.
     * @param msg
     */
    public void log(String msg) {
        PrintWriter out = getPrintWriter();
        if (out == null) {
            return;
        }
        out.println("");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        out.println(ts + "->" + msg);
        out.flush();
    }

    public void noTimeLog(String msg) {
        PrintWriter out = getPrintWriterForIgnoreFileSize();
        if (out == null) {
            return;
        }
        out.println(msg);
        out.flush();
    }

    private PrintWriter getPrintWriter() {
        try {
            if (printer != null) {
                //�����־�ļ�����һ�����ȣ�����ϵͳ���ڷ�������������ļ�������ʱ
                //��Ҫ���������µ���־�ļ�
                boolean isChange = false;
                if (logConfig.getCount() > logConfig.getCheckCount() || (isChange = isDateChange())) {
                    String absoluteFileName = getPath() + getLogFileName(logConfig.getLogFileName(), false);
                    File file = new File(absoluteFileName);
                    long size = file.length();

                    if (size > logConfig.getCheckFileSize() || isChange || !file.exists()) {
                        absoluteFileName = getPath() + getLogFileName(logConfig.getLogFileName(), true);
                        closeLogFile();
                        printer = new PrintWriter(new BufferedWriter(new FileWriter(absoluteFileName, true)));
                        logConfig.setCount(0);
                    }
                }
                logConfig.addCountAddGet();
                return printer;
            } else {
                String absoluteFileName = getPath() + getLogFileName(logConfig.getLogFileName(), false);
                printer = new PrintWriter(new BufferedWriter(new FileWriter(absoluteFileName, true)));
            }
        } catch (Exception e) {
//            DebugWriter.print(e);
            return null;
        }
        return printer;
    }

    //������־��С����
    private PrintWriter getPrintWriterForIgnoreFileSize() {
        try {
            if (printer != null) {
                //�����־�ļ�����һ�����ȣ�����ϵͳ���ڷ�������������ļ�������ʱ
                //��Ҫ���������µ���־�ļ�
                boolean isChange = false;
                if (logConfig.getCount() > logConfig.getCheckCount() || (isChange = isDateChange())) {
                    String absoluteFileName = getPath() + getLogFileName(logConfig.getLogFileName(), false);
                    File file = new File(absoluteFileName);
                    long size = file.length();

                    if (isChange || !file.exists()) {
                        absoluteFileName = getPath() + getLogFileName(logConfig.getLogFileName(), true);
                        closeLogFile();
                        printer = new PrintWriter(new BufferedWriter(new FileWriter(absoluteFileName, true)));
                        logConfig.setCount(0);
                    }
                }
                logConfig.addCountAddGet();
                return printer;
            } else {
                String absoluteFileName = getPath() + getLogFileName(logConfig.getLogFileName(), false);
                printer = new PrintWriter(new BufferedWriter(new FileWriter(absoluteFileName, true)));
            }
        } catch (Exception e) {
//            DebugWriter.print(e);
            return null;
        }
        return printer;
    }

    private String getPath() {
        String separator = System.getProperty("file.separator");
        if (separator.equals("\\")) {
            separator = separator + separator;
        } else if (separator.equals("/")) {
            logConfig.setLogFilePath(logConfig.getLogFilePath().replace('\\', '/'));
        }
        File f = new File(logConfig.getLogFilePath());
        if (!f.exists()) {
            f.mkdirs();
        }
        return logConfig.getLogFilePath() + separator;
    }

    private void closeLogFile() {
        try {
            printer.close();
        } catch (Exception e) {
//            DebugWriter.print(e);
        }
    }

    /**
     * ����ļ���
     */
    private String getLogFileName(String fileName, boolean isCreate) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String time = ts.toString();

        //ȡ�ļ�����׺
        String suffix = time.substring(0, 4) + time.substring(5, 7) + time.substring(8, 10);

        //�ɽ�������־��Сʱ���ּ�¼
        String timeSuffix = "_" + time.substring(11, 13) + time.substring(14, 16);

        //���������ͬ���ж��Ƿ������µ��ļ���
        if (suffix.equals(logConfig.getSaveDateSuffix())) {
            if (isCreate) {
                logConfig.setSaveTimeSuffix(timeSuffix);
            }
        } else {
            //������ڲ�ͬ����Ҫ�����µ��ļ�
            logConfig.setSaveDateSuffix(suffix);
            logConfig.setSaveTimeSuffix(timeSuffix);
        }
        suffix += logConfig.getSaveTimeSuffix();

        return fileName + "_" + suffix + ".log";
    }

    /**
     * �����Ƿ����仯
     * @return
     */
    private boolean isDateChange() {
        if (System.currentTimeMillis() > logConfig.getSaveTimeStamp()) {
            logConfig.setSaveTimeStamp(getEndTimeOfDay(System.currentTimeMillis()));
            return true;
        } else {
            return false;
        }
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
