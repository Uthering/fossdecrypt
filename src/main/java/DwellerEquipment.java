import com.google.gson.JsonArray;

public class DwellerEquipment {
	class EqResources {
		int Nuka = 0;
		int Food = 0;
		int Energy = 0;
		int Water = 0;
		int StimPack = 0;
		int RadAway = 0;
		int Lunchbox = 0;
		int MrHandy = 0;
		int PetCarrier = 0;
		int CraftedOutfit = 0;
		int CraftedWeapon = 0;

		public EqResources() {
		}
	}

	class EqStorage {
		EqResources resources = new EqResources();
		EqResources bonus = new EqResources();

		public EqStorage() {
		}
	}

	class EqInventory {
		JsonArray items = new JsonArray();

		public EqInventory() {
		}
	}

	EqStorage storage = new EqStorage();
	EqInventory inventory = new EqInventory();

	public DwellerEquipment() {
	}
}
