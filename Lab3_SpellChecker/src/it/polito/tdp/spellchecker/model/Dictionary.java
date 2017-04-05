package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dictionary { //e' il model
	private List<String> dictionary;
	
	
	public Dictionary(){
		dictionary = new ArrayList<String>();
		
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
					//RichWord r = new RichWord(word.toLowerCase().trim(), true);
					dictionary.add(word.toLowerCase().trim());
						
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
			if(dictionary.contains(s))
				r = new RichWord(s, true);
			else
				r = new RichWord(s, false);
			l.add(r);	
		}
			
		return l;
		
	}
	
	
	
	public List<RichWord> spellCheckTextDicotomy(List<String> inputTextList){
		List<RichWord> l = new ArrayList<RichWord>();
		Collections.sort(dictionary);
		for(String s : inputTextList){
			RichWord r;
			boolean trovato = false;
			int posI = 0;
			int posF = dictionary.size()-1;
			int posC =(int) ((posF+posI)/2);
			while(trovato == false && posI <= posF){
				if(s.compareTo(dictionary.get(posC))==0){
					trovato = true;
				}
				else{ if(s.compareTo(dictionary.get(posC))>0){
					posI = posC+1;
					posC = (int) ((posF+posI)/2);
					
				}else{
					posF = posC-1;
					posC = (int)((posF+posI)/2);
					
				}
				}
			}
			
			if(trovato){
				r = new RichWord(s, true);
			}else
				r= new RichWord(s, false);
			l.add(r);	
			
		}
		
		return l;
	}
			
}
