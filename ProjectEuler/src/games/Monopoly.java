package games;

public class Monopoly {
	private int					_iPlayerPos;
	private int					_iCCIndex;
	private int					_iChanceIndex;
	private static final int	CARD_COUNT		= 16;
	private static final int	SQUARE_COUNT	= 40;
	private static final int	SQ_GO			= 0;
	private static final int	SQ_JAIL			= 10;
	private static final int	SQ_C1			= 11;
	private static final int	SQ_E3			= 24;
	private static final int	SQ_H2			= 39;
	private static final int	SQ_R1			= 5;
	private static final int	SQ_R2			= 15;
	private static final int	SQ_R3			= 25;
	private static final int	SQ_R4			= 35;
	private static final int	SQ_U1			= 12;
	private static final int	SQ_U2			= 28;
	private static final int	SQ_CC1			= 2;
	private static final int	SQ_CC2			= 17;
	private static final int	SQ_CC3			= 33;
	private static final int	SQ_CH1			= 7;
	private static final int	SQ_CH2			= 22;
	private static final int	SQ_CH3			= 36;
	private static final int	SQ_G2J			= 30;
	
	public Monopoly() {
		moveTo(0);
	}
	
	public void runTurn() {
		int iRoll = Dice.roll(2, 4);
		moveTo(iRoll + getPlayerPosition());
		landOn(getPlayerPosition());
	}
	
	private void landOn(int iSquare) {
		switch (iSquare) {
			case SQ_CC1:
			case SQ_CC2:
			case SQ_CC3:
				pickCC(iSquare);
				break;
			case SQ_CH1:
			case SQ_CH2:
			case SQ_CH3:
				pickChance(iSquare);
				break;
			case SQ_G2J:
				moveTo(SQ_JAIL);
				break;
			default:
				moveTo(iSquare);
		}
	}
	
	private void pickCC(int iSquare) {
		_iCCIndex = (_iCCIndex + 1) % CARD_COUNT;
		switch (_iCCIndex) {
			case 1:
				moveTo(SQ_GO);
				break;
			case 2:
				moveTo(SQ_JAIL);
				break;
			default:
				moveTo(iSquare);
		}
	}
	
	private void pickChance(int iSquare) {
		_iChanceIndex = (_iChanceIndex + 1) % CARD_COUNT;
		switch (_iChanceIndex) {
			case 1:
				moveTo(SQ_GO);
				break;
			case 2:
				moveTo(SQ_JAIL);
				break;
			case 3:
				moveTo(SQ_C1);
				break;
			case 4:
				moveTo(SQ_E3);
				break;
			case 5:
				moveTo(SQ_H2);
				break;
			case 6:
				moveTo(SQ_R1);
				break;
			case 7:
				moveTo(nextR(iSquare));
				break;
			case 8:
				moveTo(nextR(iSquare));
				break;
			case 9:
				moveTo(nextU(iSquare));
				break;
			case 10:
				moveTo(iSquare - 3);
				break;
			default:
				moveTo(iSquare);
		}
		
	}
	
	private int nextR(int iSquare) {
		if (iSquare < SQ_R1) {
			return SQ_R1;
		} else if (iSquare < SQ_R2) {
			return SQ_R2;
		} else if (iSquare < SQ_R3) {
			return SQ_R3;
		} else if (iSquare < SQ_R4) {
			return SQ_R4;
		} else {
			return SQ_R1;
		}
	}
	
	private int nextU(int iSquare) {
		if (iSquare < SQ_U1) {
			return SQ_U1;
		} else if (iSquare < SQ_U2) {
			return SQ_U2;
		} else {
			return SQ_U1;
		}
	}
	
	public int getPlayerPosition() {
		return _iPlayerPos;
	}
	
	private void moveTo(int iSquare) {
		_iPlayerPos = iSquare % SQUARE_COUNT;
	}
}
