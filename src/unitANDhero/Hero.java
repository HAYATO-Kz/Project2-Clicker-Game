package unitANDhero;

import java.util.Observable;

import Alert.AlertBox;
import Register.Database;
import javafx.scene.image.Image;
import monsterANDmoney.Money;

/**
 * class for hero's activity
 * @author Hayato Kawai
 *
 */
public class Hero extends Observable {

	Money m;
	Database data = Database.getInstance();
	WeaponList weaponUse ;
	WeaponList nextWeapon ; 
	WeaponList[] weaponbox = WeaponList.values();
	private int weaponslot = data.weaponSlot;
	private int Heroattack = data.heroDamage;
	private int save = data.save;

	public Hero(Money m) {
		this.m = m;
		
	}
	
	/**
	 * @return slot of weapon that hero use
	 */
	public int getWeaponSlot() {
		return weaponslot;
	}
	
	/**
	 * set weapon from weaponslot
	 * @param set
	 */
	public void setWeapon(int set) {	
		if (weaponslot + 1 < weaponbox.length-1) {  
			setSlot(set);
			if(save == 1) save = 0;
			else if (save==0) Heroattack = weaponUse.getDamage();
			setChanged();
			notifyObservers();
		}
		else if(weaponslot+1 == weaponbox.length-1) {
			setSlot(0);
			Heroattack = weaponUse.getDamage();
			setChanged();
			notifyObservers();
		}else AlertBox.display("error", "Money is not enough");
	}

	/**
	 * set weaponUse and next weapon
	 * @param set is value of weaponbox that want to pass.
	 */
	public void setSlot(int set) {
		weaponslot += set;
		weaponUse = weaponbox[weaponslot];
		nextWeapon = weaponbox[weaponslot + 1];
	}

	/**
	 * return hero' damage
	 */
	public int getHeroattack() {
		return Heroattack;
	}

	/**
	 * get weapon's image
	 * @param word is word that use to separate between weaponuse and nextweapon.
	 * @return image of weapon
	 */
	public Image getWeaponImage(String word) {
		String url = null;
		if (word.equalsIgnoreCase("use"))
			url = "/picture/" + weaponUse.getName() + ".png";
		else if (word.equalsIgnoreCase("next"))
			url = "/picture/" + nextWeapon.getName() + ".png";
		Image weaponUseImage = new Image(url);
		return weaponUseImage;
	}

	/**
	 * upgrade hero's damage
	 */
	public void upgradeWeapon() {
		int upgrade = weaponUse.getDamage()/15;
		if(upgrade == 0) upgrade = 1;
		if (m.getMoney() >= weaponUse.getDamage()/5) {
			m.addMoney(-weaponUse.getDamage()/5);
			Heroattack += upgrade;
		}else AlertBox.display("error", "Money is not enough");
	}

	/**
	 * if money in purse enough, buy new weapon
	 */
	public void buyNewWeapon() {
		if (m.getMoney() >= nextWeapon.getDamage()) {
			m.addMoney(-1 * nextWeapon.getDamage());
			setWeapon(1);
		}
	}

	public int getUseWeaponDamage() {
		return Heroattack;
	}

	public int getNextWeaponDamage() {
		return nextWeapon.getDamage();
	}

	public String getUseWeaponName() {
		return weaponUse.getName();
	}

	public String getNextWeaponName() {
		return nextWeapon.getName();
	}
}
