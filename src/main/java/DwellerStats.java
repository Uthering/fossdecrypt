
public class DwellerStats {

	class Stat {
		int value = 1;
		int mod = 0;
		int exp = 0;

		public Stat() {
		}

		public Stat(int val) {
			value = val;
		}

		public String showVal() {
			if (10 == value) {
				return "X";
			}
			return Integer.toString(value);
		}
	}

	Stat[] stats = new Stat[] { new Stat(), // none
			new Stat(), // (S)trength
			new Stat(), // (P)erception
			new Stat(), // (E)ndurance
			new Stat(), // (C)harm
			new Stat(), // (I)ntelligence
			new Stat(), // (A)gility
			new Stat() // (L)uck
	};

	public DwellerStats() {
	}

	public String listSPECIAL() {
		StringBuilder sb = new StringBuilder();

		sb.append(stats[1].showVal());
		sb.append(stats[2].showVal());
		sb.append(stats[3].showVal());
		sb.append(stats[4].showVal());
		sb.append(stats[5].showVal());
		sb.append(stats[6].showVal());
		sb.append(stats[7].showVal());

		return sb.toString();
	}

	public void updateStats(int max) {
		// zero-index stat is kind of not-used one
		for (int i = 1; i < stats.length; i++) {
			if (stats[i].value < max) {
				stats[i].value = max;
			}
		}
	}
}
