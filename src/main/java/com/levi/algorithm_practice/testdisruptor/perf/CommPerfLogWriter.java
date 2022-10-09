package com.levi.algorithm_practice.testdisruptor.perf;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class CommPerfLogWriter {
    private CommunicationLogWriter logWriter;
    /**
     * 不记录日志的功能号列表
     * {"80001000", "00200460", "01700155", "01700160"};      //保留
     */
    private String[] nodebug = null;

    private LogInfoQueue logInfoQueue = LogInfoQueue.getInstance();

    /**
     * 构造器
     */
    private CommPerfLogWriter() {
        init();
    }

    private static class Inner{
        private static CommPerfLogWriter INSTANCE = new CommPerfLogWriter();
    }
    /**
     * 获得一个实例
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
     * 通信日志（普通日志）
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
     * 通信日志（普通日志）
     * 如果文件超过指定大小，则新建一个文件
     */
    public void log(String s) {
        AsyncFastLog log = AsyncFastLog.outGetOne();
        log.setLogTypeEnum(LogTypeEnum.PERFLOGWRITER);
        log.setLogInfo("[" + new Timestamp(System.currentTimeMillis()) + "]" + s);
        logInfoQueue.add(log);
    }

    /**
     * 记录通讯包（特殊日志，指定日志文件名）
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
     * 通信日志（特殊日志，指定日志文件名）
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
     * 是否记录日志
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
         * 获得文件名
         */
        private String getLogFileName(String fileName, boolean isCreate) {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            String time = ts.toString();

            //取文件名后缀
            String suffix = time.substring(0, 4) + time.substring(5, 7) + time.substring(8, 10);
            //只对普通通信日志按小时、分记录
            if (fileName.equalsIgnoreCase(logConfig.getLogFileName())) {
                String timeSuffix = "_" + time.substring(11, 13) + time.substring(14, 16);

                //如果日期相同，判断是否生成新的文件名
                if (suffix.equals(logConfig.getSaveDateSuffix())) {
                    if (isCreate) {
                        logConfig.setSaveTimeSuffix(timeSuffix);
                    }
                } else {
                    //如果日期不同，则要生成新的文件
                    logConfig.setSaveDateSuffix(suffix);
                    logConfig.setSaveTimeSuffix(suffix);
                }
                suffix += logConfig.getSaveTimeSuffix();
            }

            return logConfig.getLogFilePath() + "/" + fileName + "_" + suffix + ".log";
        }
    }
}