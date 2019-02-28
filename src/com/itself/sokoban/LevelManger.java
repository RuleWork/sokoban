package com.itself.sokoban;

import java.awt.Color;
/**
 * 关卡数据管理者
 * @author acer
 *
 */
public class LevelManger {
	/*
	 * 得到当前游戏关卡的数据  index关卡编号
	 */
	public static Level getNowLevelData(int index) {
		Level level = new Level();
		if (index == 1) {
			level.bgcolor = Color.GRAY;
			level.boxImgPath = "box2.png";
			level.boxLocation = new int[] {14, 6};
			level.goalImgPath = "goal2.png";
			level.goalLocation = new int[]{16,8};
			level.workerImgPath = new String[] {
					"workerLeft2.png",//0
					"workerUp2.png",//1
					"workerRight2.png",//2
					"workerDown2.png"//3
			};
			level.workerLocation = new int[]{6, 5};
			level.wallImgPath = "wall2.png";
			level.wallsLocation = new int[][]{
				{19, 1},
				{15, 2},{19,2},
				{15, 3},{18, 3},{19, 3},
	{4, 4},{5, 4},{6, 4},{7, 4},{8, 4},{9, 4},{10, 4},{11, 4},{12, 4},{12, 4},{17, 4},{19, 4},
				{4, 5},{5, 5},{15, 5},{17, 5},
	{4, 6},{5, 6},{6, 6},{7, 6},{8, 6},{9, 6},{10, 6},{11, 6},{12, 6},{13, 6},{15, 6},{17, 6},
				{7, 7},{8, 7},{13,7},{15, 7},{17, 7},
	{7, 8},{8, 8},{10, 8},{11, 8},{12, 8},{13, 8},{15, 8},{17, 8},
				{12, 9},{16, 9},{17, 9},
				{12, 10},
				{12, 11}
			};
		} else if (index == 2) {
			level.bgcolor = Color.BLUE;
			level.boxImgPath = "box.png";
			level.boxLocation = new int[] {7, 9};
			level.goalImgPath = "goal.png";
			level.goalLocation = new int[]{16, 5};
			level.workerImgPath = new String[] {
					"workerLeft.png",//0
					"workerUp.png",//1
					"workerRight.png",//2
					"workerDown.png"//3
			};
			level.workerLocation = new int[]{5, 3};
			level.wallImgPath = "wall.png";
			level.wallsLocation = new int[][]{
				{7, 1},{8, 1},{9, 4},{14, 1},{20, 1},{20, 1},
				{12, 2},{14, 2},{16, 2},{20, 2},
	{6, 3},{7, 3},{8, 3},{9, 3},{17, 3},{18, 3},{20, 3},{21, 3},
	{7, 4},{8, 4},{9, 4},{11, 4},{14, 4},{15, 4},
	{9, 5},{11, 5},{15, 5},{19, 5},
	{7, 6},{9, 6},{11, 6},{16, 6},{17, 6},{18, 6},{19, 6},
	{4, 7},{5, 7},{6, 7},{7, 7},{9, 7},{11, 7},
	{1, 8},{2, 8},{3, 8},{11, 8},{12, 8},{13, 8},{14, 8},{15, 8},{16, 8},{17, 8},{18, 8},{19, 8},{21, 8},
	{1, 9},{2, 9},{5, 9},{6, 9},{8, 9},{9, 9},{11, 9},{12, 9},{13, 9},{14, 9},{15, 9},{16, 9},{17, 9},{18, 9},{19, 9},{21, 9},
	{1, 10},{2, 10},{4, 10},{5, 10},{6, 10},{8, 10},{9, 10},{11, 10},{12, 10},{21, 10},
	{1, 11},{2, 11},{8, 11},{8, 11},{14, 11},{15, 11},{16, 11},{17, 11},{18, 11},{19, 11},{20, 11},{21, 11}


			};
		} else if (index == 3) {
			level.bgcolor = Color.YELLOW;
			level.boxImgPath = "box2.png";
			level.boxLocation = new int[] {15, 9};
			level.goalImgPath = "goal2.png";
			level.goalLocation = new int[]{11,6};
			level.workerImgPath = new String[] {
					"workerLeft2.png",//0
					"workerUp2.png",//1
					"workerRight2.png",//2
					"workerDown2.png"//3
			};
			level.workerLocation = new int[]{7, 4};
			level.wallImgPath = "wall2.png";
			level.wallsLocation = new int[][] {
				{8, 1},
				{8, 2},{11, 2},{12, 2},{14, 2},{15, 2},{17, 2},{18, 2},{19, 2},{20, 2},{21, 2},
	{8, 3},{10, 3},{11, 3},{13, 3},{21, 3},
	{8, 4},{18, 4},{21, 4},
	{8, 5},{10, 5},{11, 5},{12, 5},{13, 5},{14, 5},{15,5}, {17, 5},{18, 5},{21, 5},
	{8, 6},{18, 6},{21, 6},
	{8, 7},{10, 7},{11, 7},{13, 7},{14, 7},{15, 7},{16, 7},{17, 7},{18, 7},{21, 7},
	{14, 8},
	{8, 9},{11, 9},
	{8, 10},{9, 10},{10, 10},{11,10},{12, 10},{13, 10},{9, 10},{11, 10},{12, 10},{21, 10},
	{15, 11},{16, 11},{17, 11},{18, 11},{19, 11},{20, 11},{21, 11}
			};
		}else {
			return null;
		}
		
		return level;
	}
}
