package com.levi.allresource.testdisruptor;

import com.lmax.disruptor.RingBuffer;

public class Producer {

    private final RingBuffer<LongEvent> ringBuffer;

    public Producer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(int i){
        long sequence = ringBuffer.next();//������һ���¼����
        System.out.println("��ǰ�������ڶ����е�λ�ã�"+sequence);

        try {
            LongEvent event = ringBuffer.get(sequence);//��ȡ����Ŷ�Ӧ���¼�����
//                long data = getEventData();//��ȡҪͨ���¼����ݵ�ҵ������
            long data = i;
            event.setValue1(System.nanoTime());
            event.setValue2(System.nanoTime());
        } finally{
            ringBuffer.publish(sequence);//�����¼�
            System.out.println(Thread.currentThread().getName()+"��������������У��ڣ�"+i+"������");
        }
    }


}
