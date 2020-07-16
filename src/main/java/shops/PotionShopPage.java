package shops;

import javax.mail.MessagingException;

public class PotionShopPage extends ShopPage {
	
	private boolean itemToBeConsidered(String itemName) {
		// Super Rare ones
		if (itemName.contains("Draik")) {
			return true;
		}
		else if (itemName.contains("Krawk")) {
			return true;
		}
		else if (itemName.contains("Lutari")) {
			return true;
		}
		else if (itemName.contains("Grarrl")) {
			return true;
		}
		else if (itemName.contains("Hissi")) {
			return true;
		}
		else if (itemName.contains("Darigan")) {
			return true;
		}
		else if (itemName.contains("Wraith")) {
			return true;
		}
		else if (itemName.contains("Plushie")) {
			return true;
		}
		else if (itemName.contains("Faerie")) {
			return true;
		}
		else if (itemName.contains("Maractite")) {
			return true;
		}
		else if (itemName.contains("Ogrin")) { // Pets to not be considered, these are overrated :)
			return false;
		}			
		else if (itemName.contains("Vandagyre")) {
			return false;
		}
		else if (itemName.contains("Strange Potion")) {
			return true;
		}
		else if (itemName.contains("Rainbow Swirly Potion")) {
			return true;
		}
		else if (itemName.contains("Morphing Potion")) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	protected void findItem() throws MessagingException {
		// TODO Auto-generated method stub
		
	}	

}
