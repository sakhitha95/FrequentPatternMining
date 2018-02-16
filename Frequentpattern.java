package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Frequentpattern {

				//public static String outfile= "C:/Users/sakhi/Desktop/Newfolder/out12.txt";
				public static String out;
				public static String input;
				public static BufferedWriter b2;
				public static int stop;
				public static String interm;
				HashMap<String,Integer> tid=new HashMap<String, Integer>();// review id's
				HashMap<Integer,String> tid1=new HashMap<Integer,String>();// review id's
				Map<Integer,ArrayList<Integer>> rid=new HashMap<Integer, ArrayList<Integer>>();//transactions
				Map<Integer,ArrayList<Integer>> itt=new HashMap<Integer, ArrayList<Integer>>();//item and the trasactions in which it is present
				Map<Integer,Integer> sup=new HashMap<Integer, Integer>();//support for each item
				Map<Integer,ArrayList<ArrayList<Integer>>> Freqpat=new HashMap<Integer, ArrayList<ArrayList<Integer>>>();//frequent item list
				int count=1,line=1;
				public void change()//initializing the items to integer numbers
				{
					try (BufferedReader br = new BufferedReader(new FileReader(input))) {
						//BufferedWriter bw = null; 
					//	bw = new BufferedWriter(new FileWriter(outfile));
						String sCurrentLine;

						while ((sCurrentLine = br.readLine()) != null) {
							//System.out.println(sCurrentLine);
							String l[]=sCurrentLine.split(" ");
							ArrayList<Integer> t1=new ArrayList<Integer>();				
							for(int i=0;i<l.length;i++)
							{
							if(!tid.containsKey(l[i]))
							{
								ArrayList<Integer> tran=new ArrayList<Integer>();
								tid.put(l[i], count);
								
								count++;
								String it=tid.get(l[i]).toString();
							//	bw.write(it+' ');
								t1.add(tid.get(l[i]));//array list for one transaction
								
								tran.add(line);
								itt.put(tid.get(l[i]),tran );
				//				System.out.println(tid.get(l[i]));//converting string review id's to integers
							}
							else{
								ArrayList<Integer> tran1=new ArrayList<Integer>();
								tran1=itt.get(tid.get(l[i]));
								tran1.add(line);
								itt.put(tid.get(l[i]), tran1);
							    t1.add(tid.get(l[i]));
							    
								String ti=tid.get(l[i]).toString();
								//bw.write(ti+' ');
							}
							}
							Collections.sort(t1);
							rid.put(line, t1);
							line++;
							//bw.newLine();
						}
						for (Map.Entry<Integer, ArrayList<Integer>> entry : rid.entrySet()) {
						    int key = entry.getKey();
						    
						    //System.out.println("key, " + key +"values"+entry.getValue());
						}
						for (Map.Entry<Integer, ArrayList<Integer>> entry : itt.entrySet()) {
						    int key = entry.getKey();
						    sup.put(key, entry.getValue().size());
					//	    System.out.println(entry.getValue().size());
						//    System.out.println(sup.get(key));
						  //  System.out.println("key, " + key +"values"+entry.getValue());
						}
						//bw.close();
						for (Map.Entry<String,Integer> entry : tid.entrySet()){
							tid1.put(entry.getValue(), entry.getKey());
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				public void single(int y,int sp)//generating single item set
				{
					try{
					if(Freqpat.isEmpty())
					{
					ArrayList<ArrayList<Integer>> tem1=new ArrayList<ArrayList<Integer>>();
					for (Map.Entry<Integer, Integer> entry : sup.entrySet()) {
						if(entry.getValue()>=sp)
						{
							ArrayList<Integer> tem=new ArrayList<Integer>();
							tem.add(entry.getKey());
							tem1.add(tem);
							//System.out.println("hgg"+entry.getKey());
							if(y==1){
								String d=tid1.get(entry.getKey());
	  							//System.out.println(d);
	  							b2.write(d+' ');
							b2.write('('+Integer.toString(entry.getValue())+')');
							b2.newLine();
								
							}
						}
					}
					Freqpat.put(1, tem1);
					//System.out.println(tem1);
					}
					}
					catch(Exception e){
						
					}
				}
				public void twocand(int supp)//generating candidate sets of 2 itemset
				{
					try{
					int s=0;
					int c=1;
					ArrayList<ArrayList<Integer>> twofr1=new ArrayList<ArrayList<Integer>>();
					ArrayList<Integer> twofr=new ArrayList<Integer>();
					twofr1=Freqpat.get(1);
					//System.out.println(twofr1);
					for(ArrayList<Integer> e:twofr1){
						twofr.addAll(e);
					}
					//System.out.println(twofr);
					BufferedWriter bw = null;
					bw = new BufferedWriter(new FileWriter(interm));
					loop:
						for(int zl=0;zl<twofr.size()-1;zl++)//for(int keyitem:twofr)
					{
						
						if(c==twofr.size()){
						//	System.out.println("break");
							break loop;
						}
						else{
							
							for(int r=0;r<twofr.size()-c;r++){
								//System.out.println(r);
					    //int key = entry.getKey();
					    //System.out.println(key+" "+entry.getValue());
					    //BitSet ittr=new BitSet(2);
					    //ittr[0]=new BitSet([rid.size());
					    //BitMatrix ittr=new BitMatrix(2,rid.size());
					    BitSet tbt[]=new BitSet[2];
					    tbt[0]=new BitSet(rid.size());
					    tbt[1]=new BitSet(rid.size());
					    for(int y:itt.get(twofr.get(zl))){
					    	
					    	tbt[0].set(y-1);
					    	//System.out.println((y-1)+" ");
					    }
					    /*for(int i=0;i<ittr.length;i++)
					    	for(int j=0;j<ittr[i].length;j++)
					    System.out.println(ittr[i][j]);*/
					    for(int z: itt.get(twofr.get(zl+r+1))){
					    	tbt[1].set(z-1);
					    	//System.out.println(ittr.get(z-1,1));
					    }
					    /*for(int i=0;i<ittr.length-1;i++){
					    	//System.out.println(i);
					    	s=s+ittr[i][0]*ittr[i][1];
					    }*/
					    //System.out.println(tbt[0]);
					    //System.out.println(tbt[1]);
					    tbt[0].and(tbt[1]);
					    
					    //System.out.println(tbt[0]);
					    
					    if(tbt[0].cardinality()>=supp){
					    	
					    	//System.out.println(twofr.get(zl)+" "+twofr.get(zl+1+r)+tbt[0].cardinality());
					    	/*for(int i=0;i<ittr.length;i++)
					    	System.out.println(ittr[i][0]);*/
					    	bw.write(twofr.get(zl)+" "+twofr.get(zl+1+r));
					    	bw.newLine();
					    }
					    s=0;
					}
							c++;
							//System.out.println("aaaa");
					}
					}
					bw.close();
					}
					catch(Exception e){
						
					}
				}
				public void support(int nitem,int sp)//generating candidate and frequent item set
				{
					

					if(!Freqpat.isEmpty())
					{
						//int k=Freqpat.size();
						int k=Freqpat.size();
						ArrayList<ArrayList<Integer>> prevfreq=new ArrayList<ArrayList<Integer>>();
						ArrayList<ArrayList<Integer>> candidate=new ArrayList<ArrayList<Integer>>();
						ArrayList<ArrayList<Integer>> fcs=new ArrayList<ArrayList<Integer>>();//frequent patterns from candidate set
						prevfreq=Freqpat.get(k);
						//System.out.println(prevfreq);
						//System.out.println(k);
						int l=prevfreq.size();
						try{
							if(k!=1){
							BufferedWriter bw = null;
							bw = new BufferedWriter(new FileWriter(interm));
							//FileOutputStream fos = new FileOutputStream("C:/Users/sakhi/Desktop/Newfolder/exp.txt");
					    //ObjectOutputStream oos = new ObjectOutputStream(fos);
						for(int i=0;i<l-1;i++)
						{
											
							for(int j=i+1;j<l;j++)
							{
								ArrayList<Integer> tem=new ArrayList<Integer>();
								//for(int h=0;h<l;h++)
									tem.addAll(prevfreq.get(i));
								
								
								//tem=prevfreq.get(i);				
							ArrayList<Integer> tem1=new ArrayList<Integer>();
							ArrayList<Integer> temCandidate=new ArrayList<Integer>();
							//System.out.println(temCandidate);
							//System.out.println(prevfreq);
							//System.out.println(prevfreq.get(i));
							tem1.addAll(prevfreq.get(j));
							//System.out.println("enter");
							//System.out.println(i+"loop");
							//System.out.println(tem);
							//System.out.println(tem1);
							int te1=tem.get(k-1);
							int te2=tem1.get(k-1);
							tem.remove(k-1);
							tem1.remove(k-1);
							//System.out.println("2"+tem);
							//System.out.println("2"+tem1);
							if(tem.equals(tem1))
							{
								//System.out.println(tem.equals(tem1));
					
								if(te1>te2){
									temCandidate=tem;
									temCandidate.add(te2);
									temCandidate.add(te1);
									//System.out.println("temcandidate"+temCandidate);
								}
								else
								{
									temCandidate=tem;
									temCandidate.add(te1);
									temCandidate.add(te2);
									//System.out.println("gffg"+temCandidate);
									//System.out.println(prevfreq);
								}
								loop:
								for(int m=0;m<k;m++)//pruning step
								{
									//ArrayList<Integer> temCandidate1=new ArrayList<Integer>();
									//temCandidate1=temCandidate;
									int rt=temCandidate.get(m);
									temCandidate.remove(m);
									//System.out.println(rt);
									if(!prevfreq.contains(temCandidate))
									{
										temCandidate=null;
										//System.out.println("temcand prun");
										break loop;
									}
									temCandidate.add(rt);
									Collections.sort(temCandidate);
									//System.out.println(temCandidate);

								}
								
								if(temCandidate!=null)
								{
									
									//candidate.add(temCandidate);
								//System.out.println(temCandidate);
								//BufferedWriter bwe = null; 
								//bwe = new BufferedWriter(new FileWriter("C:/Users/sakhi/Desktop/Newfolder/exp.txt"));
								//bwe.write(temCandidate);
		  //System.out.println("asdfadf");
							    //oos.writeObject(temCandidate);
		  						for(int c:temCandidate){
		  							
		  							bw.write(c+" ");
		  						}
							    
		  						bw.newLine();
									
								}
								
							}
							}
							
						}
						bw.close();
							}
						}
						catch(Exception e){
							
						}
						
						ArrayList<Integer> a=new ArrayList<>();
						
						try{
							
							BufferedReader br = new BufferedReader(new FileReader(interm));
						//ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("C:/Users/sakhi/Desktop/Newfolder/exp.txt"));

						String sCurrentLine;
						while ((sCurrentLine = br.readLine()) != null) {
							//System.out.println(sCurrentLine);
							String rl[]=sCurrentLine.split(" ");	
							ArrayList<Integer> e= new ArrayList<Integer>();
							for(int rlc=0;rlc<rl.length;rlc++)
							{
								e.add(Integer.parseInt(rl[rlc]));
							}
						//for(ArrayList<Integer> e:candidate)
						//{
							
								//System.out.println(e);							
								ArrayList<Integer> tcm=new ArrayList<Integer>();//final transaction to be considered for support
								//System.out.println("other");
							for(int p=0;p<e.size();p++){
								ArrayList<Integer> tc1=new ArrayList<Integer>();
								if(p==0)
								{
									tcm.addAll(itt.get(e.get(p)));
									//System.out.println(tcm);
								}
								else{
									tc1.addAll(itt.get(e.get(p)));
									tcm.retainAll(tc1);
								}
							}
							//System.out.println(tcm);
							
							if(tcm.size()>=sp)
							{

								fcs.add(e);
							//	System.out.println(e+"  "+tcm.size());
								a.add(tcm.size());
								if(nitem<=k+1){
									//System.out.println(nitem+"hh "+k);
								for(int c:e){
		  							String d=tid1.get(c);
		  							//System.out.println(d);
		  							b2.write(d+' ');
		  						}
								b2.write('('+Integer.toString(tcm.size())+')');
								b2.newLine();
								//b2.write("asdf");
								}
							}
							//b2.write("asdf");
							//e=(ArrayList<Integer>)objIn.readObject();
					
						}
						br.close();					
						
						//System.out.println(fcs);
						//System.out.println(a);
						Freqpat.put(k+1, fcs);
						if(fcs.isEmpty()){
							stop=1;
						}
						}
						catch(Exception e){
							
						}
						
					}
					try{
						BufferedWriter b3 = new BufferedWriter(new FileWriter("C:/Users/sakhi/Desktop/Newfolder/exp3.txt"));
					for (Map.Entry<Integer, ArrayList<ArrayList<Integer>>> entry : Freqpat.entrySet()){
						for(ArrayList<Integer> ent:entry.getValue()){
							//System.out.println(ent);
							for(int ep:ent){
								String ep1=Integer.toString(ep);
								b3.write(ep1+' ');
								//System.out.println(ep);
							}
						}
						b3.newLine();
					}
					b3.close();
					}
					catch(Exception e){
						
					}
				
				}
				public static void main(String args[])
				{
					try{
						//b2 = new BufferedWriter(new FileWriter("C:/Users/sakhi/Desktop/Newfolder/exp1.txt"));
					Frequentpattern p=new Frequentpattern();
					Scanner reader = new Scanner(System.in);  // Reading from System.in
					System.out.println("Enter support ");
					int k = reader.nextInt();
					System.out.println("Enter a item set number ");
					int y = reader.nextInt();
					//int k=Integer.parseInt(args[0]);//support
					//int y=Integer.parseInt(args[1]);//itemset size
					//int k=10;
					//int y=2;
					//String input=args[2];
					//String interm=args[3];
					//String out=args[4];
							//b2 = new BufferedWriter(new FileWriter(out));
					//input="C:/Users/sakhi/Desktop/transactionDB.txt";
					//interm="C:/Users/sakhi/Desktop/Newfolder/exp.txt";
					System.out.println("Enter input path ");
					input = reader.next();
					System.out.println("Enter path to store candidate set ");
					interm = reader.next();
					System.out.println("Enter path to store final frequent patterns ");
					out = reader.next();
					b2 = new BufferedWriter(new FileWriter(out));
					p.change();
					//System.out.println("hello");
					
					p.single(y,k);
					p.twocand(k);
					//System.out.println("asdfasdf");
					while(stop!=1)
					{	
						
					p.support(y,k);
					//System.out.println("qqqqqq");
					}
					b2.close();
					}
					catch(Exception e){
						
					}
					}
					
	}




	

