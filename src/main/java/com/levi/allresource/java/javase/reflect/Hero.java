package com.levi.allresource.java.javase.reflect;

public class Hero {
    public String name; //����
    public float hp; //Ѫ��
    public float armor; //����
    public int moveSpeed; //�ƶ��ٶ�

    //��Ĭ�ϵĹ��췽����
    Hero(String str){
        System.out.println("(Ĭ��)�Ĺ��췽�� s = " + str);
    }

    //�޲ι��췽��
    public Hero(){
        System.out.println("�����˹��С��޲ι��췽��ִ���ˡ�����");
    }

    //��һ�������Ĺ��췽��
    public Hero(char name){
        System.out.println("������" + name);
    }

    //�ж�������Ĺ��췽��
    public Hero(String name ,float hp){
        System.out.println("������"+name+"Ѫ����"+ hp);
    }

    //�ܱ����Ĺ��췽��
    protected Hero(boolean n){
        System.out.println("�ܱ����Ĺ��췽�� n = " + n);
    }

    //˽�й��췽��
    private Hero(float hp){
        System.out.println("˽�еĹ��췽��   Ѫ����"+ hp);
    }


}
