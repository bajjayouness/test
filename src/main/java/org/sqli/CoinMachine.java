package org.sqli;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoinMachine {

	
	private Map<String, Integer> wallet;
	
	
	public CoinMachine() {
		wallet = new HashMap<String, Integer>();
	}
	
	public void putMoneyInside(String newMony) {
		String[] specifique= newMony.split("x");
		if(specifique.length ==2) {
			Integer coin = wallet.get(specifique[1]);
			if(coin !=null) {
				coin += Integer.parseInt(specifique[0]);
				wallet.put(specifique[1], coin);
			} else {
				wallet.put(specifique[1], Integer.parseInt(specifique[0]));
			}
		}
	}
	
	public double totalMoney() {
		double totalValueInPocket = 0;
		if(wallet!=null) {
			for(String coin :wallet.keySet()) {
				totalValueInPocket+= wallet.get(coin) * Double.valueOf(coin);
			}
		}
		return totalValueInPocket;
	}
	
	public int check(int coin) {
		if(wallet!=null && wallet.get(String.valueOf(coin))!=null) {
			return wallet.get(String.valueOf(coin)).intValue();
		}
		return 0;
	}
	
	
	public void getMoney(int money) {
		if(wallet !=null) {
			List<Integer> list = wallet.keySet().stream().map(s -> Integer.parseInt(s))
														 .sorted(Comparator.reverseOrder())
														 .collect(Collectors.toList());
			int valueToRetrive = money;
			for(Integer coin : list) {
				if(valueToRetrive/coin >0 && wallet.get(coin.toString()) >= (valueToRetrive/coin)) {
					wallet.put(coin.toString(), wallet.get(coin.toString())- (valueToRetrive/coin));
					valueToRetrive=valueToRetrive%coin;
				}
			}
		}
	}

	public Map<String, Integer> getWallet() {
		return wallet;
	}

	public void setWallet(Map<String, Integer> wallet) {
		this.wallet = wallet;
	}
	

}
