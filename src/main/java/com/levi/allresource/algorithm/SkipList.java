package com.levi.allresource.algorithm;

import java.util.Random;
import java.util.Stack;

// https://tinyurl.com/yjssujxn

/**
 * ���Ӷȷ�����
 *  ��ѯ���Ӷȣ�MlogN ���� M = 3����logN��˼��ΪʲôM=3? (���������״̬��ÿ������һ���ϼ�����)
 *
 *  �����ɾ���������ӶȾ�Ϊ logN
 * @param <T>
 */

class SLNode<T>{
    int key;
    T value;
    SLNode right, down;
    public SLNode(int key, T value) {
        this.key = key;
        this.value = value;
    }
}

public class SkipList<T>{

    // ͷ���
    SLNode headNode;
    // ��ǰ������������
    int highLevel;
    Random random;
    final int MAX_LEVEL = 32;
    SkipList() {
        random = new Random();
        // Ϊ�˱ȽϷ��㣬��ͷ����key����Ϊint������Сֵ
        headNode = new SLNode(Integer.MIN_VALUE, null);
        highLevel = 0;
    }

    /**
     * ��ѯ���޸Ĳ������޸�ֻ��Ҫ�ڲ�ѯ�Ļ��������������޸�value����
     * @param key
     * @return
     */
    public SLNode search(int key) {
        SLNode team = headNode;
        while (team != null) {
            if (key == team.key) return team;
            if (team.right == null) {
                team = team.down;
            } else if (team.right.key <= key) {
                team = team.right;
            } else {
                team = team.down;
            }
        }
        return null;
    }


    /**
     * ɾ������
     * @param key
     */
    public void delete(int key) {
        SLNode team = headNode;
        while (team != null) {
            if (team.right == null) {
                team = team.down;
            } else if (team.right.key == key) {
                team.right = team.right.right;
            } else if (team.right.key > key) {
                team = team.down;
            } else {
                team = team.right;
            }
        }
    }

    /**
     * �������
     */
    public void insert (SLNode node) {
        int key = node.key;
        SLNode stNode = search(key);
        if (stNode != null) {
            stNode.value = node.value;
            return;
        }

        SLNode team = headNode;
        Stack<SLNode> stack = new Stack<>();
        while (team != null) {
            if (team.right == null) {
                stack.push(team);
                team = team.down;
            } else if (team.right.key > key){
                stack.push(team);
                team = team.down;
            } else {
                team = team.right;
            }
        }

        SLNode downNode = null;
        int level= 1;
        while (!stack.empty()) {

            SLNode teamNode = stack.pop();
            SLNode newNode = new SLNode(node.key, node.value);
            newNode.down = downNode;
            downNode = newNode;

            if (teamNode.right == null) {
                teamNode.right = newNode;
            } else {
                newNode.right = teamNode.right;
                teamNode.right = newNode;
            }

            if (level > MAX_LEVEL) break;
            double nextDouble = random.nextDouble();
            if (nextDouble > 0.5) break;
            level++;
            if (level > highLevel) {
                highLevel=level;
                //��Ҫ����һ���µĽڵ�
                SLNode highHeadNode=new SLNode(Integer.MIN_VALUE, null);
                highHeadNode.down=headNode;
                headNode=highHeadNode;//�ı�head
                stack.add(headNode);//�´��׳�head
            }

        }

    }

    public void printList() {
        SLNode teamNode=headNode;
        int index=1;
        SLNode last=teamNode;
        while (last.down!=null){
            last=last.down;
        }
        while (teamNode!=null) {
            SLNode enumNode=teamNode.right;
            SLNode enumLast=last.right;
            System.out.printf("%-8s","head->");
            while (enumLast!=null&&enumNode!=null) {
                if(enumLast.key==enumNode.key)
                {
                    System.out.printf("%-5s",enumLast.key+"->");
                    enumLast=enumLast.right;
                    enumNode=enumNode.right;
                }
                else{
                    enumLast=enumLast.right;
                    System.out.printf("%-5s","");
                }

            }
            teamNode=teamNode.down;
            index++;
            System.out.println();
        }
    }

}
