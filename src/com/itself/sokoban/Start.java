package com.itself.sokoban;

public class Start {
	public static void main(String[] args) {
		//打开游戏
		GameFrame gameFrame = new GameFrame("Pro推箱子3.0--第1关");
		//得到当前关卡数据
		Level level = LevelManger.getNowLevelData(1);
		//让游戏窗口加载关卡数据
		gameFrame.loadLevelData(level);
		//游戏窗口可见,玩家开始玩
		gameFrame.setVisible(true);
	}
}
