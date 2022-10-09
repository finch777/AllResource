package com.levi.allresource.java.juc.pro_con;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ��ʵ�֣�
 * ��������
 * ��������
 * ��������д�Ķ���ʵ��������������ģ��
 * */
public class MyBlockingQueue<E> {

    private int capacity;
    private final AtomicInteger count = new AtomicInteger();

    // �����нڵ�����
    static class Node<E> {
        E item;
        Node<E> next;
        Node(E x) {
            item = x;
        }
    }

    // ����ͷβ�ڵ�ָ��
    private Node<E> head;
    private Node<E> tail;
    // ������
    private final ReentrantLock takeLock = new ReentrantLock();
    private final ReentrantLock putLock = new ReentrantLock();

    // ��ȷ����
    private final Condition notEmpty = takeLock.newCondition();
    private final Condition notFull = putLock.newCondition();

    public MyBlockingQueue() {
        this(Integer.MAX_VALUE); // 2^31 - 1    0x7fffffff
    }

    public MyBlockingQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException();
        this.capacity = capacity;
        tail = head = new Node<E>(null);
    }


    private void signalNotEmpty() {
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            notEmpty.signal();
        }finally {
            takeLock.unlock();
        }
    }

    private void signalNotFull() {
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            notFull.signal();
        }finally {
            takeLock.unlock();
        }
    }


    private void enqueue(Node<E> node) {
        tail.next = node;
        tail = tail.next;
    }

    public void put(E e) throws InterruptedException {
        if (e == null) throw new NullPointerException();
        int c = -1;
        final ReentrantLock putLock = this.putLock;
        final AtomicInteger count = this.count;
        Node<E> node = new Node<>(e);
        putLock.lockInterruptibly();
        try {
            if (count.get() == capacity) {
                notFull.await();
            }
            enqueue(node);
            c = count.getAndIncrement();
            if (c + 1 < capacity) {
                notFull.signal();
            }

        } finally {
            putLock.unlock();
        }
        // �����-1�����0��˵�����������Ԫ�ؿ������ѣ���ȥ����take����
        if (c == 0) {
            signalNotEmpty();
        }
    }


    private E dequeue(){
        Node<E> h = head;
        Node<E> first = h.next;
        h.next = h;
        E e = first.item;
        first.item = null;
        head = first;
        return e;
    }

    public E take() throws InterruptedException {
        E e;
        int c = -1;
        final ReentrantLock takeLock = this.takeLock;
        AtomicInteger count = this.count;

        takeLock.lockInterruptibly();
        try {
            if (count.get() == 0) {
                notEmpty.await();
            }
            e = dequeue();
            c = count.getAndDecrement();
            if (c > 1) {
                notEmpty.signal();
            }
        } finally {
            takeLock.unlock();
        }

        if (c == capacity) {
            signalNotFull();
        }
        return e;
    }

}
