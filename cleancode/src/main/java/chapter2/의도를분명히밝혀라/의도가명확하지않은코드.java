package chapter2.의도를분명히밝혀라;

import java.util.ArrayList;
import java.util.List;

public class 의도가명확하지않은코드 {

	private List<int[]> theList = new ArrayList<int[]>();

	public List<int[]> getThem() {
		List<int[]> list1 = new ArrayList<int[]>();
		for (int[] x : theList)
			if (x[0] == 4)
				list1.add(x);
		return list1;
	}
}
