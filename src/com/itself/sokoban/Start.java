package com.itself.sokoban;

public class Start {
	public static void main(String[] args) {
		//����Ϸ
		GameFrame gameFrame = new GameFrame("Pro������3.0--��1��");
		//�õ���ǰ�ؿ�����
		Level level = LevelManger.getNowLevelData(1);
		//����Ϸ���ڼ��عؿ�����
		gameFrame.loadLevelData(level);
		//��Ϸ���ڿɼ�,��ҿ�ʼ��
		gameFrame.setVisible(true);
	}
}
