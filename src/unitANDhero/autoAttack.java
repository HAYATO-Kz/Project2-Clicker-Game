package unitANDhero;

import java.util.ArrayList;

import Alert.AlertBox;
import monsterANDmoney.Money;
import monsterANDmoney.Monster;

/**
 *  This class is class for unit's team activity such as auto attack ,
 *   add unit to team, upgrade team's damage and member in team.
 * @author Hayato Kawai
 *
 */
public class autoAttack implements Runnable{
	
	private ArrayList<Unitlist> team = new ArrayList<Unitlist>();
	Money m;
	Monster mon;
	private int alldamage = 0;
	public autoAttack(Money m , Monster mon) {
		this.m=m;
		this.mon=mon;
	}
	
	/**
	 * auto attack for unit's team
	 * attack monster for every 0.1 second
	 */
	@Override
	public void run() {
			while(true) {
				mon.attack(alldamage);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
	}
	
	/**
	 * add unit to team
	 * @param unit that want to add
	 * @param status is status of member
	 * @return 1 if can add member
	 */
	public int addmember(Unitlist unit,int slot, int status) {
		if(m.getMoney()>= memberSpawnCost(slot)&& status ==0) {
			alldamage += unit.getDamage();
			team.add(unit);
			m.addMoney(-memberSpawnCost(slot));
			return 1;
		} else if(status ==1) {
			alldamage += unit.getDamage();
			team.add(unit);
		}else {
			AlertBox.display("error", "Money is not enough");
		}
		return 0;
	}
	
	/**
	 * upgrade team's damage
	 * @param damage is value of integer that want to upgrade to unit's team.
	 * @param status is status of member
	 */
	public void upgrade(int damage , int slot, int status) {
		if(m.getMoney() >= memberUpgradeCost(slot)&& status ==0) {
		alldamage += damage;
		m.addMoney(-memberUpgradeCost(slot));
		}else AlertBox.display("error", "Money is not enough");
	}
	
	/**
	 * @param slot of member
	 * @return cost of member spawning
	 */
	public int memberSpawnCost(int slot) {
		switch(slot)  {
		case 1:
			return Unitlist.A.getDamage()*500;
		case 2:
			return Unitlist.B.getDamage()*500;
		case 3:
			return Unitlist.C.getDamage()*500;
		case 4:
			return Unitlist.D.getDamage()*500;
		}
		return 0;
	}
	
	/**
	 * @param slot of member
	 * @return cost of member upgrade
	 */
	public int memberUpgradeCost(int slot) {
		switch(slot)  {
		case 1:
			return Unitlist.A.getDamage()*250;
		case 2:
			return Unitlist.B.getDamage()*250;
		case 3:
			return Unitlist.C.getDamage()*250;
		case 4:
			return Unitlist.D.getDamage()*250;
		}
		return 0;
	}
	
	
	/**
	 * check unit is in team or not
	 * @param member that want to check
	 * @return true if member already in team,
	 * return false if member isn't in team
	 */
	public boolean isMember(Unitlist member) {
		if(team.contains(member)) return true;
		else return false;
	}
	
	/**
	 * If unit is not in team, add her to team.
	 * If unit already in team, upgrade team damage with unit damage*500.
	 * @param unit that want to know. 
	 * @param status is status of member
	 * @return true if can addmember
	 */
	public boolean member(int unit , int status) {
		Unitlist member = null;
		switch(unit)  {
		case 1:
			member = Unitlist.A;
			break;
		case 2:
			member = Unitlist.B;
			break;
		case 3:
			member = Unitlist.C;
			break;
		case 4:
			member = Unitlist.D;
			break;
		}
		
			if(!isMember(member))	{
				if(addmember(member,unit,status)==1) return true;
				
			}
			else upgrade(member.getDamage()*2,unit,status);
			return false;
			
	}
	
//	public void getUnitStatus(int unit) {
//		switch(unit)  {
//		case 1:
//			member = Unitlist.A;
//			break;
//		case 2:
//			member = Unitlist.B;
//			break;
//		case 3:
//			member = Unitlist.C;
//			break;
//		case 4:
//			member = Unitlist.D;
//			break;
//		}
//	}
}
