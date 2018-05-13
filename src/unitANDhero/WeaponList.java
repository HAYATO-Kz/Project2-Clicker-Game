package unitANDhero;

/**
 * Enum class for weapon
 * @author Hayato Kawai
 *
 */
public enum WeaponList {
 Weapon1("1","Weapon1",10), Weapon2("2","Weapon2",100), Weapon3("3","Weapon3",500) , Weapon4("4","Weapon4",1000), Weapon5("5","Weapon5",5000), Weapon6("6","Weapon6",10_000),
 Weapon7("7","Weapon7",50_000), Weapon8("8","Weapon8",100_000), Weapon9("9","Weapon9",500_000), Weapon10("10","Weapon10",1_000_000), Weapon11("11","Weapon11",5_000_000), 
 Weapon12("12","Weapon12",10_000_000),LastSlot("13","Coming soon!!!",0);
	
	private String weaponSlot;
	private String weaponName;
	private int weaponDamage;
	
	WeaponList (String slot,String name , int damage) {
		weaponSlot=slot;
		weaponName=name;
		weaponDamage=damage;
	}
	
	public String getName() {
		return weaponName;
	}
	
	public String getSlot() {
		return weaponSlot;
	}
	
	public int getDamage() {
		return weaponDamage;
	}
	
	
}
