package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dictionary { //e' il model
	private List<RichWord> dictionary;
	
	
	public Dictionary(){
		dictionary = new ArrayList<RichWord>();
		
	}
		public void loadDictionary(String language){
			dictionary.clear();
			FileReader f;
			try{
				if(language.toLowerCase().compareTo("italian")==0){
					f = new FileReader("rsc/Italian.txt");
				}else {
					f = new FileReader("rsc/English.txt");
				}
				BufferedReader b = new BufferedReader(f);
				String word;
				while((word = b.readLine())!=null){
					RichWord r = new RichWord(word.toLowerCase().trim(), true);
					dictionary.add(r);
						
					}
				f.close();
				b.close();
			}catch(IOException e){
				System.out.println("Errore nella lettura del file!");
			}
		}
		

	
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> l = new ArrayList<RichWord>();
		for(String s : inputTextList){
			RichWord r;
			if(dictionary.contains(new RichWord(s, false)))
				r = new RichWord(s, true);
			else
				r = new RichWord(s, false);
			l.add(r);
			//System.out.println(r.getParola()+" "+r.isCorretta());
				
		}
			
		return l;
		
	}
	
	public List<RichWord> spellCheckTextDicotomy(List<String> inputTextList){
		dictionary.get(dictionary.size()/2);
		return null;
	}
	
	

}
