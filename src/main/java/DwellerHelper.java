import java.util.Formatter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class DwellerHelper {
	public static void listDwellers(JsonObject vault) {
		JsonArray dws = vault.getAsJsonObject("dwellers").getAsJsonArray("dwellers");
		System.out.println("Dwellers total=" + dws.size());

		final Gson gson = new Gson();
		final StringBuilder sb = new StringBuilder();
		final Formatter fmt = new Formatter(sb);

		dws.forEach(raw -> {
			try {
				Dweller d = gson.fromJson(raw, Dweller.class);
				sb.setLength(0);

				sb.append("Dweller[");
				fmt.format("%3d", d.serializeId);
				sb.append("]: ");
				sb.append(d.showGender());
				sb.append(" SPECIAL=");
				sb.append(d.stats.listSPECIAL());
				sb.append(" [");
				sb.append(d.name);
				sb.append(" ");
				sb.append(d.lastName);
				sb.append("]");
				if (!d.equipedWeapon.isDefault(EquipWeapon.defId)) {
					sb.append(" weapon=" + d.equipedWeapon);
				}
				if (!d.equipedOutfit.isDefault(EquipOutfit.defId)) {
					sb.append(" outfit=" + d.equipedOutfit);
				}
				System.out.println(sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		fmt.close();
		sb.setLength(0);
	}

	public static void updateDwellersStats(JsonObject vault, int toLevel) {
		JsonArray dws = vault.getAsJsonObject("dwellers").getAsJsonArray("dwellers");
		dws.forEach(raw -> {
			JsonObject dw = raw.getAsJsonObject();
			JsonArray sts = dw.getAsJsonObject("stats").getAsJsonArray("stats");
			for (int i = 1; i < 8; i++) {
				JsonObject s = sts.get(i).getAsJsonObject();
				if (s.get("value").getAsInt() < toLevel) {
					s.addProperty("value", toLevel);
				}
			}
		});
	}

	public static void addItemsForDwellers(JsonObject vault, EquipWeapon wpn, EquipOutfit otf) {
		JsonArray dws = vault.getAsJsonObject("dwellers").getAsJsonArray("dwellers");
		int wpCnt = 0;
		int ofCnt = 0;

		Gson gson = new Gson();
		JsonElement jsWp = gson.toJsonTree(wpn);
		JsonElement jsOf = gson.toJsonTree(otf);

		for (int i = 0; i < dws.size(); i++) {
			try {
				Dweller d = gson.fromJson(dws.get(i), Dweller.class);
				if (!d.equipedWeapon.id.equals(wpn.id)) {
					wpCnt++;
				}
				if (!d.equipedOutfit.id.equals(otf.id)) {
					ofCnt++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		JsonArray items = vault.getAsJsonObject("vault").getAsJsonObject("inventory").getAsJsonArray("items");
		for (int i = 0; i < wpCnt; i++) {
			items.add(jsWp);
		}
		for (int i = 0; i < ofCnt; i++) {
			items.add(jsOf);
		}
		System.out.println("Added " + wpCnt + " " + wpn);
		System.out.println("Added " + ofCnt + " " + otf);
	}

	public static void addUberDwellers(JsonObject vault, int upToSize) {
		JsonArray dws = vault.getAsJsonObject("dwellers").getAsJsonArray("dwellers");
		int count = upToSize - dws.size();
		if (count <= 0) {
			return;
		}
		int lastId = dws.size() + 1;
		Gson gson = new Gson();
		for (int i = 0; i < count; i++) {
			Dweller d = new Dweller(lastId + i);
			d.stats.updateStats(10);
			d.equipedOutfit.id = "PowerArmor_MkVI";
			d.equipedWeapon.id = "Fatman_Mirv";
			JsonElement jsD = gson.toJsonTree(d);
			dws.add(jsD);
		}
	}
}
