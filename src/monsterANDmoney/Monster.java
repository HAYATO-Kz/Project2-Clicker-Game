package monsterANDmoney;

import java.util.Observable;
import java.util.Random;

import Register.Database;
import javafx.scene.image.Image;
/**
 * Class for control activity with the monster such as attack, set new monster and return level and round.
 * @author Hauato Kawai
 *
 */
public class Monster extends Observable {
	private Database data = Database.getInstance();
	private Money m ;
	private int MonsterHP ;
	private int MonsterReward;
	private int level =data.level;
	private int round = data.round;
	private String MonsterName;
	
	public Monster(Money m) {
		this.m =m;
	}
	
	/**
	 * attack monster  if monster die, add monster's reward to purse and set level and round
	 * @param attack damage that want to attack monster
	 */
	public void attack (int attack) {
		MonsterHP -= attack;
		if(MonsterHP<=0) {
			m.addMoney(MonsterReward);
			if(round==10) {
				round =1;
				level++;
			}else {				
				round++;
			}
			newMonster(level);
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * set monster by random.
	 * @param level is the values that affect monster's HP and monster's reward.
	 */
	public void newMonster(int level) {
		MonsterList mon = null;
		Random rand = new Random();
		int n = rand.nextInt(10) + 1;
		int stat = 1;
		switch (n) {
		case 1:
			mon = MonsterList.GreenOrc;
			break;
		case 2:
			mon = MonsterList.Bird;
			break;
		case 3:
			mon = MonsterList.PinkSlime;
			break;
		case 4:
			mon = MonsterList.Cat;
			break;
		case 5:
			mon = MonsterList.Skull;
			break;
		case 6:
			mon = MonsterList.YellowSlime;
			break;
		case 7:
			mon = MonsterList.OrangeOrc;
			break;
		case 8:
			mon = MonsterList.PinkBird;
			break;
		case 9:
			mon = MonsterList.BlueOrc;
			break;
		case 10:
			mon = MonsterList.Demon;
			break;
		}
		for(int i = 1; i<= level ;i++) {
			stat = (int) (stat*(2));
		}
		int HP = stat/2;
		int reward = stat/10;
		if(level == 1||HP<=0||reward<=0) {
			HP = 1; reward=1;
		}
		setMonster(mon.getName(), mon.getHP()*HP, mon.getReward()*reward);
	}
	
	/**
	 * set monster's Hp, monster's name and monster's reward.
	 * @param name of monster
	 * @param hp of monster
	 * @param reward of monster
	 */
	public void setMonster(String name,int hp, int reward) {
		MonsterHP = hp;
		MonsterName = name;
		MonsterReward = reward;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * @return monster's HP.
	 */
	public int MonsterHp() {
		return MonsterHP;
	}
	
	/**
	 * @return monster's name.
	 */
	public String getName() {
		return MonsterName;
	}

	/**
	 * @return monster's reward.
	 */
	public int getReward() {
		return MonsterReward;
	}
	
	/**
	 * @return normally image for monster.
	 */
	public Image getMonsterImage() {
		Image monsterImage = new Image ("/picture/"+MonsterName+".png");
		return monsterImage;
	}
	
	/**
	 * @return image when monster was attacked by player.
	 */
	public Image getMonsterzImage() {
		Image monsterImage = new Image ("/picture/"+MonsterName+"z.png");
		return monsterImage;
	}
	
	/**
	 * @return level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * @return round
	 */
	public int getRound() {
		return round;
	}
}
