package com.levi.allresource.testdisruptor.perf;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PerfManager {
    //ȷ�ϻر�����
    private static Map<String, Long[]> tickConfirmMap = new ConcurrentHashMap<String,Long[]>();
    //�ɽ��ر�����
    private static Map<String, Long[]> tickKnockMap = new ConcurrentHashMap<String,Long[]>();

    private static final CommPerfLogWriter perflogWriter = CommPerfLogWriter.getInstance();
    //perf��־��¼����
//    public static String perf_open = CommConfig.getProperty("perf_open");
    private static PerfManager instance;
    //��ȡ����
    public static PerfManager getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (PerfManager.class) {
            if (instance == null) {
                instance = new PerfManager();
            }
            return instance;
        }
    }

    /**
     * д��ȷ�ϻ���
     *
     * @param tick
     * @param uuid
     * @param value
     */
    public void pushConfirmMap(int tick,String uuid,long value){
        if (!tickConfirmMap.containsKey(uuid)) {
            Long[] list = new Long[]{Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE, (long) 0};
            tickConfirmMap.put(uuid, list);
        }
        Long[] list = tickConfirmMap.get(uuid);
        list[tick - 1] = value;
        list[list.length - 1]++;
        if (list[list.length - 1] == list.length - 1) {
            writeConfirmPerf(uuid);
        }
    }

    /**
     *д��ɽ�����
     * @param tick
     * @param uuid
     * @param value
     */
    public void pushKnockMap(int tick,String uuid,long value){
//        if("1".equals(1)) {
//
//        }
        if (!tickKnockMap.containsKey(uuid)) {
            Long[] list = new Long[]{Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE, (long) 0};
            tickKnockMap.put(uuid, list);
        }
        Long[] list = tickKnockMap.get(uuid);
        list[tick - 1] = value;
        //ÿ���һ��Tickֵ,������һ
        list[list.length - 1]++;
        //����tick�������ӡ
        if (list[list.length - 1] == (list.length - 1)) {
            writeKnockPerf(uuid);
        }
    }

    /**
     * ����uuid��ȡȷ��Tick��Ϣ
     * @param uuid
     * @return
     */
    public Long[] getConfirmByUuid(String uuid){
        return tickConfirmMap.get(uuid);
    }

    /**
     * ����uuid��ȡ�ɽ�Tick��Ϣ
     * @param uuid
     * @return
     */
    public Long[] getKnockByUuid(String uuid){
        return tickKnockMap.get(uuid);
    }

    /**
     * �ӻ������Ƴ�
     * @param uuid
     */
    private void removeConfirm(String uuid){
        tickConfirmMap.remove(uuid);
    }
    private void removeKnock(String uuid){
        tickKnockMap.remove(uuid);
    }

    /**
     * д��־
     * @param uuid
     */
    public void writeConfirmPerf(String uuid){
        try {
            Long[] list = PerfManager.getInstance().getConfirmByUuid(uuid);
            StringBuilder logBuilder = new StringBuilder();
            logBuilder.append(Thread.currentThread().getName() + "-Confirm-");
            if(ParaChecker.isValidPara(uuid)){
                logBuilder.append("uuid="+uuid).append(",");
            }
            //����list��ӡ
            for (int i=0;i<list.length-1;i++){
                Long l = list[i];
                if(ParaChecker.isNull(l)||ParaChecker.isValidPara(l)){
                    logBuilder.append("Tick"+(i+1)).append("="+l).append(",");
                }
            }
            perflogWriter.log(logBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            removeConfirm(uuid);

        }
    }

    public void writeKnockPerf(String uuid){
        Long[] list = PerfManager.getInstance().getKnockByUuid(uuid);
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append(Thread.currentThread().getName() + "-Knock-");
        if(ParaChecker.isValidPara(uuid)){
            logBuilder.append("uuid="+uuid).append(",");
        }
        for (int i=0;i<list.length-1;i++){
            Long l = list[i];
            if(ParaChecker.isNull(l)||ParaChecker.isValidPara(l)){
                logBuilder.append("Tick"+(i+1)).append("="+l).append(",");
            }
        }
        perflogWriter.log(logBuilder.toString());
        removeKnock(uuid);
    }


}