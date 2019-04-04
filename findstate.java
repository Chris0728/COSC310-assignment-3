
public class findstate {
	public static short findState(String input) {
		// To state = 20
		for (int i = 0; i < main.keywordLen(11); i++) {
			if (input.contains(main.getKeyWord(11, i)))
				return 20;
		}
		// To state = 0
		for (int i = 0; i < main.keywordLen(10); i++) {
			if (input.contains(main.getKeyWord(10, i)))
				return 0;
		}
		// Generic correct (for state = 4, 7, 9, 15)
		for (int i = 0; i < main.keywordLen(8); i++) {
			if (input.contains(main.getKeyWord(8, i)))
				if (main.getState() == 4 ||main.getState() == 7 ||main.getState() == 9 ||main.getState() == 15)
					return 18;
		}
		// Generic wrong (for state = 4, 7, 9, 15)
		for (int i = 0; i < main.keywordLen(9); i++) {
			if (input.contains(main.getKeyWord(9, i))) {
				if (main.getState() == 4)
					return 21;
				if (main.getState() == 7)
					return 22;
				if (main.getState() == 9)
					return 23;
				if (main.getState() == 15)
					return 0;
			}
		}
		// From 0 to 1
		for (int i = 0; i < main.keywordLen(0); i++) {
			if (main.getState() == 0 && input.contains(main.getKeyWord(0, i)))
				return 1;
		}
		// From 1 to 2(has item), 8(not in stock), 10(no item)
		for (String s : main.clothingItems) {
			if (main.getState() == 1 && (input.contains(s) || input.equals(s))) {
				if (Math.random() > 0.5)
					return 2;
				else
					return 8;
			}
		for(String d : main.accessories) {
			if(main.getState() == 1 && (input.contains(d) || input.equals(d))) {
				if(Math.random() > 0.3)
					return 8; //out of stock
				else
					return 24; //items on sale
			}
		}
		}
		if (main.getState() == 1)
			return 10;
		// From 2 to 3
		for (int i = 0; i < main.keywordLen(1); i++) {
			if (main.getState() == 2 && input.contains(main.getKeyWord(1, i)))
				return 3;
		}
		// From 3, 21 to 4(valid), 5(invalid)
		if (main.getState() == 3 || main.getState() == 21) {
			for (char c : input.toCharArray()) {
				if (input.length() != 16 || !Character.isDigit(c)) {
					return 5;
				}
				return 4;
			}
		}
		// From 2 to 6
		for (int i = 0; i < main.keywordLen(2); i++) {
			if (main.getState() == 2 && input.contains(main.getKeyWord(2, i)))
				return 6;
		}
		// From 6 to 7
		if (main.getState() == 6)
			return 7;
		// From 8, 14 to 9
		if (main.getState() == 8 || main.getState() == 14)
			return 9;
		// From 0 to 11
		for (int i = 0; i < main.keywordLen(3); i++) {
			if (main.getState() == 0 && input.contains(main.getKeyWord(3, i)))
				return 11;
		}
		// From 0 to 12
		for (int i = 0; i < main.keywordLen(4); i++) {
			if (main.getState() == 0 && input.contains(main.getKeyWord(4, i)))
				return 12;
		}
		// From 12 to 13(has order), 16(no order)
		if (main.getState() == 12) {
			if (Math.random() > 0.5)
				return 13;
			else
				return 16;
		}
		// From 13 to 14
		for (int i = 0; i < main.keywordLen(5); i++) {
			if (main.getState() == 13 && input.contains(main.getKeyWord(5, i)))
				return 14;
		}
		// From 13 to 15
		for (int i = 0; i < main.keywordLen(6); i++) {
			if (main.getState() == 13 && input.contains(main.getKeyWord(6, i)))
				return 15;
		}
		// From 0 to 17
		for (int i = 0; i < main.keywordLen(7); i++) {
			if (main.getState() == 0 && input.contains(main.getKeyWord(7, i)))
				return 17;
		}
		// From 17 to 18
		if (main.getState() == 17)
			return 18;
		// From 22 to 7
		if (main.getState() == 22)
			return 7;
		// From 23 to 9
		if (main.getState() == 23)
			return 9;
		main.setbkState(main.getState());
		return 19;
}
}
