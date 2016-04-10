
public class EquipWeapon extends EquipItem {
	public static transient String defId = "Fist";
	public static transient String defType = "Weapon";

	public EquipWeapon() {
		id = defId;
		type = defType;
	}

	public EquipWeapon(String weaponId) {
		id = weaponId;
		type = defType;
	}
}
