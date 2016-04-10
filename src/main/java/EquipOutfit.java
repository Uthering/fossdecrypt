
public class EquipOutfit extends EquipItem {
	public static transient String defId = "jumpsuit";
	public static transient String defType = "Outfit";

	public EquipOutfit() {
		id = defId;
		type = defType;
	}

	public EquipOutfit(String outfitId) {
		id = outfitId;
		type = defType;
	}
}
