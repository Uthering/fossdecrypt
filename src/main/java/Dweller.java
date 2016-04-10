public class Dweller {
	// class describes most ordinary dweller, i.e. no special features as for uncommon one
	long serializeId = -1L;
	String name = null;
	String lastName = null;
	DwellerHappiness happiness = new DwellerHappiness();
	DwellerHealth health = new DwellerHealth();
	DwellerExp experience = new DwellerExp();
	DwellerRels relations = new DwellerRels();
	DwellerEquipment equipment = new DwellerEquipment();
	int gender = 2;
	DwellerStats stats = new DwellerStats();
	boolean pregnant = false;
	boolean babyReady = false;
	boolean assigned = false;
	boolean sawIncident = false;
	boolean WillGoToWasteland = false;
	boolean WillBeEvicted = false;
	boolean IsEvictedWaitingForFollowers = false;
	long skinColor = 0xffffffff;
	long hairColor = 0xffffffff;
	long outfitColor = 0xffffffff;
	int pendingExperienceReward = 0;
	String hair = "09";
	EquipOutfit equipedOutfit = new EquipOutfit();
	EquipWeapon equipedWeapon = new EquipWeapon();
	long savedRoom = -1L;
	long lastChildBorn = -1L;
	String rarity = "Normal";
	long deathTime = -1L;

	public Dweller(long id) {
		serializeId = id;
		name = "Name" + id;
		lastName = "Fam" + id;
	}

	public String showGender() {
		switch (gender) {
		case 1:
			return "F";
		case 2:
			return "M";
		}
		return "?";
	}
}
