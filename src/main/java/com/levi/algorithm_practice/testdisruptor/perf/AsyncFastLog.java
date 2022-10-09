package com.levi.algorithm_practice.testdisruptor.perf;

public class AsyncFastLog extends PoolObjectBase{
    private LogTypeEnum logTypeEnum;
    private Object logInfo;
    private int headler;
    private String tag;
    private Throwable e;

    public Throwable getE() {
        return e;
    }

    public void setE(Throwable e) {
        this.e = e;
    }

    public LogTypeEnum getLogTypeEnum() {
        return logTypeEnum;
    }

    public void setLogTypeEnum(LogTypeEnum logTypeEnum) {
        this.logTypeEnum = logTypeEnum;
    }

    public Object getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(Object logInfo) {
        this.logInfo = logInfo;
    }

    public int getHeadler() {
        return headler;
    }

    public void setHeadler(int headler) {
        this.headler = headler;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private final static ObjectPool<AsyncFastLog> POOL = new ObjectPool<AsyncFastLog>(AsyncFastLog.class, 10000, 10000, true);

    public static AsyncFastLog outGetOne(){
        return POOL.GetOne();
    }
    public static void outPutOne(AsyncFastLog log){
        if(log != null) {
            log.clear();
            POOL.PutOne(log);
        }
    }

    public void clear(){
        logTypeEnum = null;
        logInfo = null;
        e = null;
    }
}
