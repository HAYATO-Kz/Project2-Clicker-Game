package unitANDhero;

/**
 * Enum class for unit
 * @author Hayato Kawai
 */
public enum Unitlist {
	
	A("A",1),B("B",5),C("C",25),D("D",50);
	
	private String unitName;
	private int unitDamage;
	
	Unitlist (String unitname, int unitdamage) {
			unitDamage=unitdamage;
			unitName = unitname;
	}
	
	public String getName() {
		return unitName;
	}
	
	public int getDamage() {
		return unitDamage;
	}
}
