package com.levi.allresource.testdisruptor.perf;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class CommPerfLogWriter {
    private CommunicationLogWriter logWriter;
    /**
     * ����¼��־�Ĺ��ܺ��б�
     * {"80001000", "00200460", "01700155", "01700160"};      //����
     */
    private String[] nodebug = null;

    private LogInfoQueue logInfoQueue = LogInfoQueue.getInstance();

    /**
     * ������
     */
    private CommPerfLogWriter() {
        init();
    }

    private static class Inner{
        private static CommPerfLogWriter INSTANCE = new CommPerfLogWriter();
    }
    /**
     * ���һ��ʵ��
     */
    public static CommPerfLogWriter getInstance() {
        return Inner.INSTANCE;
    }

    private void init() {
        LogConfig logConfig = new LogConfig();
        logConfig.setLogFileName("perf");
        logConfig.setLogFilePath("applog/perf");

        logWriter = new CommunicationLogWriter(logConfig);
    }

    /**
     * ͨ����־����ͨ��־��
     * @param dataPack
     * @throws IOException
     */
//    public void log(ProtoBufMessage dataPack) throws IOException {
//        AsyncFastLog log = AsyncFastLog.outGetOne();
//        log.setLogTypeEnum(LogTypeEnum.PERFLOGWRITER);
//        if (isLogThisDataPack(dataPack)) {
//            log.setLogInfo(dataPack);
//            logInfoQueue.add(log);
//        }
//    }

    /**
     * ͨ����־����ͨ��־��
     * ����ļ�����ָ����С�����½�һ���ļ�
     */
    public void log(String s) {
        AsyncFastLog log = AsyncFastLog.outGetOne();
        log.setLogTypeEnum(LogTypeEnum.PERFLOGWRITER);
        log.setLogInfo("[" + new Timestamp(System.currentTimeMillis()) + "]" + s);
        logInfoQueue.add(log);
    }

    /**
     * ��¼ͨѶ����������־��ָ����־�ļ�����
     * @param dmpPackage
     * @param fileName
     * @throws IOException
     */
//    public void log(ProtoBufMessage dmpPackage, String fileName) throws IOException {
//        if (isLogThisDataPack(dmpPackage)) {
//            log(dmpPackage.toString(), fileName);
//        }
//    }

    /**
     * ͨ����־��������־��ָ����־�ļ�����
     */
    public synchronized void log(String s, String fileName) {
        try {
            PrintWriter out =
                    new PrintWriter(
                            new BufferedWriter(new FileWriter(logWriter.getLogFileName(fileName, false), true)));

            out.println(s + "\n");
            out.close();
        } catch (Exception e) {
//            DebugWriter.print(e);
        }
    }


    public void handlerData(String logInfo){
        logWriter.log(logInfo);
    }

    /**
     * �Ƿ��¼��־
     */
//    private boolean isLogThisDataPack(ProtoBufMessage dataPack) {
//        if (nodebug == null) {
//            return true;
//        }
//
//        String funcCode = String.valueOf(dataPack.getReqMsgCode());
//        for (int i = 0; i < nodebug.length; i++) {
//            if (funcCode.equals(nodebug[i])) {
//                return false;
//            }
//        }
//
//        return true;
//    }

    static class CommunicationLogWriter extends LogWriter {
        public CommunicationLogWriter(LogConfig logConfig) {
            super(logConfig);
        }

        /**
         * ����ļ���
         */
        private String getLogFileName(String fileName, boolean isCreate) {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            String time = ts.toString();

            //ȡ�ļ�����׺
            String suffix = time.substring(0, 4) + time.substring(5, 7) + time.substring(8, 10);
            //ֻ����ͨͨ����־��Сʱ���ּ�¼
            if (fileName.equalsIgnoreCase(logConfig.getLogFileName())) {
                String timeSuffix = "_" + time.substring(11, 13) + time.substring(14, 16);

                //���������ͬ���ж��Ƿ������µ��ļ���
                if (suffix.equals(logConfig.getSaveDateSuffix())) {
                    if (isCreate) {
                        logConfig.setSaveTimeSuffix(timeSuffix);
                    }
                } else {
                    //������ڲ�ͬ����Ҫ�����µ��ļ�
                    logConfig.setSaveDateSuffix(suffix);
                    logConfig.setSaveTimeSuffix(suffix);
                }
                suffix += logConfig.getSaveTimeSuffix();
            }

            return logConfig.getLogFilePath() + "/" + fileName + "_" + suffix + ".log";
        }
    }
}