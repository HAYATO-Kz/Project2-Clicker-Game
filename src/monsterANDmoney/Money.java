package monsterANDmoney;

import java.util.Observable;
import java.util.Observer;

import Register.Database;

/**
 * Class about purse such as add money to purse.
 * @author Hayato Kawai
 */
public class Money extends Observable {
	
	private Database data = Database.getInstance();
	private int purse= data.money;
	
	/**
	 * add money to purse
	 * @param reward
	 */
	public void addMoney(int money) {
		purse+= money;
		setChanged();
		notifyObservers();
	}
	
	public  int getMoney() {
		return purse;
	}
}
