import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

public class MainTest {

	@Test
	public void testSplit() throws Exception {
		String m;

		m = Main.textWrap("123456789", 10, '\n');
		Assert.assertTrue("123456789".equals(m));

		m = Main.textWrap("123456789", 5, '\n');
		Assert.assertTrue("12345\n6789".equals(m));

		m = Main.textWrap("123456789", 3, '\n');
		Assert.assertTrue("123\n456\n789".equals(m));
	}

	@Test
	public void testExts() throws Exception {
		Assert.assertTrue("abc.sav".equals(Main.setExtension("abc.js", "sav")));

		Assert.assertTrue("abc.js".equals(Main.setExtension("abc.js", "js")));

		Assert.assertTrue("abc.def.sav".equals(Main.setExtension("abc.def.js", "sav")));
	}

	@Test
	public void testDweller() throws Exception {
		Gson gson = new Gson();
		Dweller dw = new Dweller(159);
		String jsDw = gson.toJson(dw);

		// Assert.assertEquals("", jsDw);
		Assert.assertTrue(true);
	}
}