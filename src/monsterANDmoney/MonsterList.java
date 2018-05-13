package monsterANDmoney;

/**
 * Enum class for monster.
 * @author Hayato Kawai
 *
 */
public enum MonsterList {
	YellowSlime("YellowSlime",100,10),Bird("Bird2",150,20),Cat("Cat",200,30),PinkSlime("PinkSlime",250,40),Skull("Skull",300,50),
	GreenOrc("GreenOrc",350,60),OrangeOrc("OrangeOrc",400,70),PinkBird("PinkBird",450,80),BlueOrc("BlueOrc",500,90),Demon("Demon",550,100);
	
	private final int HP;
	private final int reward;
	private final String name;

	private MonsterList(String name,int hp,int reward) {
		this.name=name;
		this.HP = hp;
		this.reward = reward;
	}

	/**
	 * @return HP of monster
	 */
	public int getHP() {
		return this.HP;
	}

	/**
	 * @return reward of monster.
	 */
	public int getReward() {
		return this.reward;
	}

	/**
	 * @return name of monster
	 */
	public String getName() {
		return this.name;
	}

}
