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
	//��ǰ�ؿ�ʹ�õĹ���ͼƬ
	String[] workerImgPaths;
	//��ǰ�ؿ����
	int index = 1;
	
	
	public GameFrame(String title) throws HeadlessException {
		super(title);//���๹�췽��
		super.setSize(23 * 48, 13 * 48 + 27);//���ô����С
		super.setLocation((1920 - 23 * 48) / 2 , (1080 - (13 * 48 + 27)) / 2);//���ô���λ�þ���
		super.setResizable(false);//���ô�С���ɱ�
	  
		this.initPanel();//��ʼ�����
		
		this.addEvent();	
	}
	void loadLevelData(Level level)	{
		panel.removeAll();
		
		this.panel.setBackground(level.bgcolor);
		this.box = this.addLabel(level.boxImgPath, level.boxLocation[0], level.boxLocation[1]);		
		this.goal = this.addLabel(level.goalImgPath, level.goalLocation[0], level.goalLocation[1]);
		this.worker = this.addLabel(level.workerImgPath[0], level.workerLocation[0], level.workerLocation[1]);
		this.initWalls(level.wallImgPath, level.wallsLocation);
		
		//���õ�ǰ��Ϸ�ؿ�ʹ�õĹ���ͼƬ
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
	 * ����Χǽ
	 * @param wallImgPath   Χǽʹ��ͼƬ
	 * @param wallsLocation Χǽλ��
	 */
	void initWalls(String wallImgPath, int[][] wallsLocation) {
		ImageIcon wallImg = new ImageIcon("imgs/" + wallImgPath);
		//23 * 2 + (13 - 2) * 2
		this.walls = new JLabel[68 + wallsLocation.length];
		//����Ƭ����ÿ���ש��
		for (int i = 0; i < walls.length; i++) {
			walls[i] = new JLabel(wallImg);
		}
		//��¼ʹ�õ��������е��Ǹ��±�
		int index = 0;
		//��һ��,��һ��,��һ��,��һ��.
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
		//����ϰ�Χǽ
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
				//������λ��
				worker.setBounds(worker.getBounds().x + x, worker.getBounds().y + y, 48, 48);
				/*************�жϹ����Ƿ�ǽ************/
				for (int i = 0; i < walls.length; i++) {
					if (worker.getBounds().intersects(walls[i].getBounds())) {
						worker.setBounds(worker.getBounds().x - x, worker.getBounds().y - y, 48, 48);
						break;
					}
				}	
				if (worker.getBounds().intersects(goal.getBounds())) {
					worker.setBounds(worker.getBounds().x - x, worker.getBounds().y - y, 48, 48);
				}
				//�ù���������
				if (worker.getBounds().intersects(box.getBounds())) {
					box.setBounds(box.getBounds().x + x, box.getBounds().y + y, 48, 48);
				}
				for (int i = 0; i < walls.length; i++) {
					if (box.getBounds().intersects(walls[i].getBounds())) {
						box.setBounds(box.getBounds().x - x, box.getBounds().y - y, 48, 48);
						worker.setBounds(worker.getBounds().x - x, worker.getBounds().y - y, 48, 48);
					}
				}
				//�ж���Ӯ
				if (box.getBounds().intersects(goal.getBounds())) {
					//������һ������
					index ++;
					Level level = LevelManger.getNowLevelData(index);
					if(level == null) {
						JOptionPane.showMessageDialog(null, "��ϲ��,ͨ����!");
						System.exit(0);
					} else {
						int result = JOptionPane.showConfirmDialog(null, "��Ҫ���" + index + "����?");
						if (result == JOptionPane.YES_OPTION) {
							setTitle("������3.0 -- " + index + "��");
							loadLevelData(level);	
						} else {
							JOptionPane.showMessageDialog(null, "�ټ�,��ӭ������!");
							System.exit(0);
						}
						
					}
 					
					
				}
			}
		});
	}
	
		
}
