package game.map;

import java.util.ArrayList;
import java.util.List;

public enum MapCodes {
	VOID('#'),
	NEUTRAL('.'), 
	ACTIVE_OWNED('O'), 
	INACTIVE_OWNED('o'), 
	ACTIVE_OPPONENT('X'), 
	INACTIVE_OPPONENT('x');

	char code;

	MapCodes(final char code) {
		this.code = code;
	}
	
	char getCode() {
		return code;
	}
}
