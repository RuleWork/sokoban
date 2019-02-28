package com.itself.sokoban;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
	JPanel panel;
	JLabel box;
	JLabel worker;
	JLabel goal;
	JLabel[] walls;
	//当前关卡使用的工人图片
	String[] workerImgPaths;
	//当前关卡编号
	int index = 1;
	
	
	public GameFrame(String title) throws HeadlessException {
		super(title);//父类构造方法
		super.setSize(23 * 48, 13 * 48 + 27);//设置窗体大小
		super.setLocation((1920 - 23 * 48) / 2 , (1080 - (13 * 48 + 27)) / 2);//设置窗体位置居中
		super.setResizable(false);//设置大小不可变
	  
		this.initPanel();//初始化面板
		
		this.addEvent();	
	}
	void loadLevelData(Level level)	{
		panel.removeAll();
		
		this.panel.setBackground(level.bgcolor);
		this.box = this.addLabel(level.boxImgPath, level.boxLocation[0], level.boxLocation[1]);		
		this.goal = this.addLabel(level.goalImgPath, level.goalLocation[0], level.goalLocation[1]);
		this.worker = this.addLabel(level.workerImgPath[0], level.workerLocation[0], level.workerLocation[1]);
		this.initWalls(level.wallImgPath, level.wallsLocation);
		
		//设置当前游戏关卡使用的工人图片
		this.workerImgPaths = level.workerImgPath;
		panel.repaint();
	}
	void initPanel() {
		this.panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);
		super.setContentPane(panel);
	}
	
	JLabel addLabel(String imgPath, int x, int y) {
		ImageIcon img = new ImageIcon("imgs/" + imgPath);
		JLabel label = new JLabel(img);
		panel.add(label);
		label.setBounds(x * 48, y * 48, 48, 48);
		return label;
	}
	/**
	 * 加载围墙
	 * @param wallImgPath   围墙使用图片
	 * @param wallsLocation 围墙位置
	 */
	void initWalls(String wallImgPath, int[][] wallsLocation) {
		ImageIcon wallImg = new ImageIcon("imgs/" + wallImgPath);
		//23 * 2 + (13 - 2) * 2
		this.walls = new JLabel[68 + wallsLocation.length];
		//把照片贴到每块瓷砖上
		for (int i = 0; i < walls.length; i++) {
			walls[i] = new JLabel(wallImg);
		}
		//记录使用到了数组中的那个下标
		int index = 0;
		//上一块,下一块,左一块,右一块.
		for (int i = 0; i < 23; i++) {
			panel.add(walls[index]);
			walls[index++].setBounds(i * 48, 0, 48, 48);
			
			panel.add(walls[index]);
			walls[index++].setBounds(i * 48, 12 * 48, 48, 48);
		}
		for (int i = 1; i <= 11; i++) {
			panel.add(walls[index]);
			walls[index++].setBounds(0, i * 48, 48, 48);
			
			panel.add(walls[index]);
			walls[index++].setBounds(22 * 48, i * 48, 48, 48);
		}
		//添加障碍围墙
		for (int i = 0; i < wallsLocation.length; i++) {
			int[] wallLocation = wallsLocation[i];	//{10, 6},
			panel.add(walls[index]);
			walls[index++].setBounds(wallLocation[0] * 48, wallLocation[1] * 48, 48, 48);
		}
		
	}
	void addEvent() {
		super.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				int x = 0, y = 0;
				int workerImgIndex = 0;
				String workerImgPath = workerImgPaths[0];
				if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
					x = -48;
				} else if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
					y = -48;
					workerImgIndex = 1;
				} else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
					x = 48;
					workerImgIndex = 2;
				} else if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
					y = 48;
					workerImgIndex = 3;
				}
				ImageIcon workerImg = new ImageIcon("imgs/" + workerImgPaths[workerImgIndex]);
				worker.setIcon(workerImg);
				//设置新位置
				worker.setBounds(worker.getBounds().x + x, worker.getBounds().y + y, 48, 48);
				/*************判断工人是否穿墙************/
				for (int i = 0; i < walls.length; i++) {
					if (worker.getBounds().intersects(walls[i].getBounds())) {
						worker.setBounds(worker.getBounds().x - x, worker.getBounds().y - y, 48, 48);
						break;
					}
				}	
				if (worker.getBounds().intersects(goal.getBounds())) {
					worker.setBounds(worker.getBounds().x - x, worker.getBounds().y - y, 48, 48);
				}
				//让工人推箱子
				if (worker.getBounds().intersects(box.getBounds())) {
					box.setBounds(box.getBounds().x + x, box.getBounds().y + y, 48, 48);
				}
				for (int i = 0; i < walls.length; i++) {
					if (box.getBounds().intersects(walls[i].getBounds())) {
						box.setBounds(box.getBounds().x - x, box.getBounds().y - y, 48, 48);
						worker.setBounds(worker.getBounds().x - x, worker.getBounds().y - y, 48, 48);
					}
				}
				//判断输赢
				if (box.getBounds().intersects(goal.getBounds())) {
					//加载下一关数据
					index ++;
					Level level = LevelManger.getNowLevelData(index);
					if(level == null) {
						JOptionPane.showMessageDialog(null, "恭喜你,通关了!");
						System.exit(0);
					} else {
						int result = JOptionPane.showConfirmDialog(null, "你要玩第" + index + "关吗?");
						if (result == JOptionPane.YES_OPTION) {
							setTitle("推箱子3.0 -- " + index + "关");
							loadLevelData(level);	
						} else {
							JOptionPane.showMessageDialog(null, "再见,欢迎再来玩!");
							System.exit(0);
						}
						
					}
 					
					
				}
			}
		});
	}
	
		
}
