
public class EquipItem {
	String id;
	String type;
	boolean hasBeenAssigned = false;
	boolean hasRandonWeaponBeenAssigned = false;

	public EquipItem() {
	}

	public String toString() {
		return type + "{" + id + "}";
	}

	public boolean isDefault(String defId) {
		return (null != id && id.equals(defId));
	}
}
