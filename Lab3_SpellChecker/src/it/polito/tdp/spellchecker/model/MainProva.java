package it.polito.tdp.spellchecker.model;

import java.util.*;

public class MainProva {

	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		List<String> input = new ArrayList<String>();
		input.add("ciao");
		input.add("byuodab");
		input.add("melanina");
		
		d.loadDictionary("italian");
		
		for(RichWord r : d.spellCheckText(input)){
			if(r.isCorretta()==false){
				System.out.println(r.getParola());
			}
		}
		
	}

}
