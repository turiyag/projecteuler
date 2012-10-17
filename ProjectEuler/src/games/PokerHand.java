package games;

public class PokerHand {
	public static final int	SUIT_SPADES		= 3;
	public static final int	SUIT_HEARTS		= 2;
	public static final int	SUIT_CLUBS		= 1;
	public static final int	SUIT_DIAMONDS	= 0;
	
	public static final int	CARD_JACK		= 11;
	public static final int	CARD_QUEEN		= 12;
	public static final int	CARD_KING		= 13;
	public static final int	CARD_ACE		= 14;
	
	private final int[]		_iCards;				// _iCards[i]/4 = value of
													// card, _iCards[i]%4 = suit
	private String			_sInit;				// Initial string
													
	public PokerHand() {
		_iCards = new int[5];
	}
	
	public PokerHand(final String sInit) {
		_sInit = sInit;
		_iCards = new int[5];
		_iCards[0] = stoc(sInit.substring(0, 2));
		_iCards[1] = stoc(sInit.substring(3, 5));
		_iCards[2] = stoc(sInit.substring(6, 8));
		_iCards[3] = stoc(sInit.substring(9, 11));
		_iCards[4] = stoc(sInit.substring(12, 14));
	}
	
	public int GetCard(final int i) {
		return _iCards[i];
	}
	
	public int stoc(final String sCard) {
		final char cVal = sCard.charAt(0);
		final char cSuit = sCard.charAt(1);
		int iCard;
		if (cSuit == 'S') {
			iCard = SUIT_SPADES;
		} else if (cSuit == 'H') {
			iCard = SUIT_HEARTS;
		} else if (cSuit == 'C') {
			iCard = SUIT_CLUBS;
		} else {
			iCard = SUIT_DIAMONDS;
		}
		
		if (cVal == '2') {
			iCard += 2 * 4;
		} else if (cVal == '3') {
			iCard += 3 * 4;
		} else if (cVal == '4') {
			iCard += 4 * 4;
		} else if (cVal == '5') {
			iCard += 5 * 4;
		} else if (cVal == '6') {
			iCard += 6 * 4;
		} else if (cVal == '7') {
			iCard += 7 * 4;
		} else if (cVal == '8') {
			iCard += 8 * 4;
		} else if (cVal == '9') {
			iCard += 9 * 4;
		} else if (cVal == 'T') {
			iCard += 10 * 4;
		} else if (cVal == 'J') {
			iCard += CARD_JACK * 4;
		} else if (cVal == 'Q') {
			iCard += CARD_QUEEN * 4;
		} else if (cVal == 'K') {
			iCard += CARD_KING * 4;
		} else if (cVal == 'A') {
			iCard += CARD_ACE * 4;
		}
		
		return iCard;
	}
	
	static public int compare(final PokerHand ph1, final PokerHand ph2) {
		int iVal1;
		int iVal2;
		
		iVal1 = HasRoyal(ph1);
		iVal2 = HasRoyal(ph2);
		iVal1 /= 4;
		iVal2 /= 4;
		if (iVal1 > iVal2) {
			return 1;
		} else if (iVal1 < iVal2) {
			return -1;
		}
		
		iVal1 = HasSgtFlush(ph1);
		iVal2 = HasSgtFlush(ph2);
		iVal1 /= 4;
		iVal2 /= 4;
		if (iVal1 > iVal2) {
			return 1;
		} else if (iVal1 < iVal2) {
			return -1;
		}
		
		iVal1 = HasFour(ph1);
		iVal2 = HasFour(ph2);
		iVal1 /= 4;
		iVal2 /= 4;
		if (iVal1 > iVal2) {
			return 1;
		} else if (iVal1 < iVal2) {
			return -1;
		}
		iVal1 = HasFullHouse(ph1);
		iVal2 = HasFullHouse(ph2);
		iVal1 /= 4;
		iVal2 /= 4;
		if (iVal1 > iVal2) {
			return 1;
		} else if (iVal1 < iVal2) {
			return -1;
		}
		iVal1 = HasFlush(ph1);
		iVal2 = HasFlush(ph2);
		iVal1 /= 4;
		iVal2 /= 4;
		if (iVal1 > iVal2) {
			return 1;
		} else if (iVal1 < iVal2) {
			return -1;
		}
		iVal1 = HasStraight(ph1);
		iVal2 = HasStraight(ph2);
		iVal1 /= 4;
		iVal2 /= 4;
		if (iVal1 > iVal2) {
			return 1;
		} else if (iVal1 < iVal2) {
			return -1;
		}
		iVal1 = HasThree(ph1);
		iVal2 = HasThree(ph2);
		iVal1 /= 4;
		iVal2 /= 4;
		if (iVal1 > iVal2) {
			return 1;
		} else if (iVal1 < iVal2) {
			return -1;
		}
		iVal1 = HasTwoPair(ph1);
		iVal2 = HasTwoPair(ph2);
		iVal1 /= 4;
		iVal2 /= 4;
		if (iVal1 > iVal2) {
			return 1;
		} else if (iVal1 < iVal2) {
			return -1;
		}
		iVal1 = HasPair(ph1);
		iVal2 = HasPair(ph2);
		iVal1 /= 4;
		iVal2 /= 4;
		if (iVal1 > iVal2) {
			return 1;
		} else if (iVal1 < iVal2) {
			return -1;
		}
		iVal1 = HighCard(ph1);
		iVal2 = HighCard(ph2);
		iVal1 /= 4;
		iVal2 /= 4;
		if (iVal1 > iVal2) {
			return 1;
		} else if (iVal1 < iVal2) {
			return -1;
		}
		
		iVal1 = NextHighCard(ph1, iVal1 * 4);
		iVal2 = NextHighCard(ph2, iVal2 * 4);
		iVal1 /= 4;
		iVal2 /= 4;
		if (iVal1 > iVal2) {
			return 1;
		} else if (iVal1 < iVal2) {
			return -1;
		}
		
		iVal1 = NextHighCard(ph1, iVal1 * 4);
		iVal2 = NextHighCard(ph2, iVal2 * 4);
		iVal1 /= 4;
		iVal2 /= 4;
		if (iVal1 > iVal2) {
			return 1;
		} else if (iVal1 < iVal2) {
			return -1;
		}
		
		iVal1 = NextHighCard(ph1, iVal1 * 4);
		iVal2 = NextHighCard(ph2, iVal2 * 4);
		iVal1 /= 4;
		iVal2 /= 4;
		if (iVal1 > iVal2) {
			return 1;
		} else if (iVal1 < iVal2) {
			return -1;
		}
		
		iVal1 = NextHighCard(ph1, iVal1 * 4);
		iVal2 = NextHighCard(ph2, iVal2 * 4);
		iVal1 /= 4;
		iVal2 /= 4;
		if (iVal1 > iVal2) {
			return 1;
		} else if (iVal1 < iVal2) {
			return -1;
		}
		System.out.println("ERROR: NO WINNER, IDENTICAL HANDS?");
		ph1.print();
		System.out.println();
		ph2.print();
		System.out.println();
		return 0;
	}
	
	static public int HighCard(final PokerHand ph) {
		int iMax = 0;
		
		for (int i = 0; i < 5; i++) {
			if (ph.GetCard(i) > iMax) {
				iMax = ph.GetCard(i);
			}
		}
		return iMax;
	}
	
	static public int NextHighCard(final PokerHand ph, final int iCard) {
		int iMax = 0;
		
		for (int i = 0; i < 5; i++) {
			if (ph.GetCard(i) > iMax) {
				if (ph.GetCard(i) < iCard) {
					iMax = ph.GetCard(i);
				}
			}
		}
		return iMax;
	}
	
	static public int HasPair(final PokerHand ph) {
		for (int i = 0; i < 4; i++) {
			for (int k = i + 1; k < 5; k++) {
				if (ph.GetCard(i) / 4 == ph.GetCard(k) / 4) {
					if (HasThree(ph) / 4 != ph.GetCard(i) / 4) {
						if (HasFour(ph) / 4 != ph.GetCard(i) / 4) {
							return ph.GetCard(i);
						}
					}
				}
			}
		}
		return 0;
	}
	
	static public int HasTwoPair(final PokerHand ph) {
		final int iFirstPair = HasPair(ph);
		for (int j = 0; j < 4; j++) {
			if (iFirstPair / 4 != ph.GetCard(j) / 4) {
				for (int k = j + 1; k < 5; k++) {
					if (ph.GetCard(j) / 4 == ph.GetCard(k) / 4) {
						return ph.GetCard(j);
					}
				}
			}
		}
		return 0;
	}
	
	static public int HasThree(final PokerHand ph) {
		for (int i = 0; i < 3; i++) {
			for (int k = i + 1; k < 4; k++) {
				if (ph.GetCard(i) / 4 == ph.GetCard(k) / 4) {
					for (int j = k + 1; j < 5; j++) {
						if (ph.GetCard(i) / 4 == ph.GetCard(j) / 4) {
							if (HasFour(ph) / 4 != ph.GetCard(i) / 4) {
								return ph.GetCard(i);
							}
						}
					}
				}
			}
		}
		return 0;
	}
	
	static public int HasStraight(final PokerHand ph) {
		final int iMax = HighCard(ph);
		for (int i = 0; i < 5; i++) {
			if (ph.GetCard(i) / 4 == iMax / 4 - 1) {
				for (int j = 0; j < 5; j++) {
					if (ph.GetCard(j) / 4 == iMax / 4 - 2) {
						for (int k = 0; k < 5; k++) {
							if (ph.GetCard(k) / 4 == iMax / 4 - 3) {
								for (int l = 0; l < 5; l++) {
									if (ph.GetCard(l) / 4 == iMax / 4 - 4) {
										return iMax;
									}
								}
							}
						}
					}
				}
			}
		}
		return 0;
	}
	
	static public int HasFlush(final PokerHand ph) {
		if (ph.GetCard(0) % 4 == ph.GetCard(1) % 4) {
			if (ph.GetCard(0) % 4 == ph.GetCard(2) % 4) {
				if (ph.GetCard(0) % 4 == ph.GetCard(3) % 4) {
					if (ph.GetCard(0) % 4 == ph.GetCard(4) % 4) {
						return HighCard(ph);
					}
				}
			}
		}
		return 0;
	}
	
	static public int HasFullHouse(final PokerHand ph) {
		if (HasPair(ph) > 0) {
			return HasThree(ph);
		}
		return 0;
	}
	
	static public int HasFour(final PokerHand ph) {
		for (int i = 0; i < 2; i++) {
			for (int k = i + 1; k < 3; k++) {
				if (ph.GetCard(i) / 4 == ph.GetCard(k) / 4) {
					for (int j = k + 1; j < 4; j++) {
						if (ph.GetCard(i) / 4 == ph.GetCard(j) / 4) {
							for (int h = j + 1; h < 5; h++) {
								if (ph.GetCard(i) / 4 == ph.GetCard(h) / 4) {
									return ph.GetCard(i);
								}
							}
						}
					}
				}
			}
		}
		return 0;
	}
	
	static public int HasSgtFlush(final PokerHand ph) {
		if (HasStraight(ph) > 0) {
			return HasFlush(ph);
		}
		return 0;
	}
	
	static public int HasRoyal(final PokerHand ph) {
		final int iRet = HasSgtFlush(ph);
		if (iRet / 4 == CARD_ACE) {
			return iRet;
		}
		return 0;
	}
	
	public void print() {
		System.out.print(_sInit);
	}
}
