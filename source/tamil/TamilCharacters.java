package tamil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
class TamilCharacters
{
  private static Map<String,TamilChar> map = new HashMap<String,TamilChar>();
  private static ArrayList<TamilChar> uyirEzhuthu = new ArrayList<TamilChar>();
  private static ArrayList<TamilChar> uyirmeiEzhuthu = new ArrayList<TamilChar>();
  private static ArrayList<TamilChar> sirappuKuriyeedu = new ArrayList<TamilChar>();
  private static ArrayList<TamilChar> kurilEzhuthukkal = new ArrayList<TamilChar>();
  private static ArrayList<TamilChar> nedilEzhuthukkal = new ArrayList<TamilChar>();
  private static ArrayList<TamilChar> otruEzhuthukkal = new ArrayList<TamilChar>();
  
  static
  {
    try 
    {
      initBasicChars();
      differenciateCharacters();
      setUyirmeiEhuthukkalInMap();
    } 
    catch (Exception e) 
    {
      e.printStackTrace();
    }  
  }
  

  public String[] detachProperChars(String userInput, StringBuffer sb) throws Exception
  {
	  if(!this.validateChars(userInput,sb))
	  {
		  System.out.println("Invalid user input");
		  return new String[]{};
	  }
	  
    ArrayList<String> al = new ArrayList<String>();
    for(int i=userInput.length()-1; i >= 0; i--)
    {
      char ch = userInput.charAt(i);
      if(this.isSirappuKuriyeedu(ch))
      {     
    	  int dump = i-1;
          al.add(userInput.charAt(dump)+""+userInput.charAt(i));
          i = dump;
      }
      else
      {
        al.add(userInput.charAt(i)+"");
      }
    }
    return this.toStringArray(al,sb);
  }
  
  public ArrayList<TamilChar> getKurilEzhuthukkal()
  {
	  return kurilEzhuthukkal;
  }
  
  public ArrayList<TamilChar> getNedilEzhuthukkal()
  {
	  return nedilEzhuthukkal;
  }

  public boolean isKuril(String ch)
  {
	  return this.equalTo(kurilEzhuthukkal,ch);
  }
  
  public boolean isNedil(String ch)
  {
	  return this.equalTo(nedilEzhuthukkal,ch);
  }  
  
  public boolean isOtru(String ch)
  {
	  return this.equalTo(otruEzhuthukkal,ch);
  }
  
  public ArrayList<TamilChar> getSirappuKuriyeedukkal()
  {
    return sirappuKuriyeedu;
  }

  public ArrayList<TamilChar> getUyirmeiEzhuthukkal()
  {
    return uyirmeiEzhuthu;
  }

  public ArrayList<TamilChar> getUyirEzhuthukkal()
  {
    return uyirEzhuthu;
  }
  
  public ArrayList<TamilChar> getOtruEzhuthukkal()
  {
	  return otruEzhuthukkal;
  }
  
  private boolean validateChars(String word, StringBuffer sb) throws Exception
  {
    ArrayList al = new ArrayList();
	  for(int i=0;i<word.length();i++)
	  {
	    boolean res = false;
		  Iterator<String> it = map.keySet().iterator();
		  String woCh = String.valueOf(word.charAt(i));
		  while(it.hasNext())
		  {
			  String ch = it.next();			  
			  if(ch.equals(woCh))
			  {
				  res = true;
			  }
		  }
      if(!res)
      {
        al.add(woCh);
      }
	  }
    if(!al.isEmpty())
    {
      throw new Exception("Invalid characters found " + al);
    }
    return al.isEmpty();
  }
  
  private boolean equalTo(ArrayList<TamilChar> al, String ch)
  {
	  for(int i=0;i<al.size();i++)
	  {
		  TamilChar tc = al.get(i);
		  if((tc.getUnicode()).equals(ch))
		  {
			  return true;
		  }
	  }
	  return false;
  }

  
  private String[] toStringArray(ArrayList<String> alist, StringBuffer sb)
  {
    sb.append("[");
    Collections.reverse(alist);
    String[] sarr = new String[alist.size()];
    for(int i=0;i<alist.size();i++)
    {
      sb.append("\""+alist.get(i)+"\",");
      sarr[i] = alist.get(i);
    }
    sb.append("]");
    return sarr;
  }

  private static String[] toStringArray(Object[] arr)
  {
	  String[] sarr = new String[arr.length];
	  for(int  i=0;i<arr.length;i++)
	  {
		  sarr[i] = (String)arr[i];
	  }
	  return sarr;
  }

  private static void setUyirmeiEhuthukkalInMap() throws Exception
  {//set all(216) tamil character
    int index = 100;
    for(int i=0;i<uyirmeiEzhuthu.size();i++)
    {
      for(int j=0;j<sirappuKuriyeedu.size();j++)
      {
        TamilChar uyir = (TamilChar)uyirmeiEzhuthu.get(i);
        TamilChar sirappu = (TamilChar)sirappuKuriyeedu.get(j);
        String key = uyir.getUnicode()+""+sirappu.getUnicode();
        map.put(key,new TamilChar(index,key,getProps(sirappu.getProps(), uyir.getProps())));
        index++;
      }
    }
  }
  
  private static String[] getProps(String[] arr1, String[] arr2)
  {
	  Set<String> set = new HashSet<String>();
	  for(int i=0;i<arr1.length;i++)
	  {
		  set.add(arr1[i]);
	  }
	  for(int i=0;i<arr2.length;i++)
	  {
		  set.add(arr2[i]);
	  }
	  return toStringArray(set.toArray());
  }

  private boolean isSirappuKuriyeedu(char ch)
  {
	  return this.equalTo(sirappuKuriyeedu, String.valueOf(ch));
  }
  
  private static void differenciateCharactersByProps() throws Exception
  {
	  setUyirmeiEhuthukkalInMap();
	  Set<String> s = map.keySet();
	  Iterator<String> it = s.iterator();
	  while(it.hasNext())
	  {
		  String key = it.next();
	      TamilChar tc = map.get(key);
	      if(tc.kuril)
	      {
	        kurilEzhuthukkal.add(tc);
	      }
	      if(tc.nedil)
	      {
	        nedilEzhuthukkal.add(tc);
	      }
	      if(tc.otru)
	      {
	    	  otruEzhuthukkal.add(tc);
	      }

	  }
  }

  private static void differenciateCharacters() throws Exception
  {
	Map<String,TamilChar> chars = map;
    if(chars.size() < 0) { return; }
    Set<String> s = chars.keySet();
    Iterator<String> it = s.iterator();
    while(it.hasNext())
    {
      String key = it.next();
      TamilChar tc = chars.get(key);
      if(tc.uyir)
      {
        uyirEzhuthu.add(tc);
      }
      if(tc.sirappu_kuriyeedu)
      {
        sirappuKuriyeedu.add(tc);
      }
      if(tc.uyirmei)
      {
        uyirmeiEzhuthu.add(tc);
      }
    }
    differenciateCharactersByProps();
  }
  


  private static void initBasicChars()
  {
    //Char map collected characters;
    map.put("\u0b85",new TamilChar(1,"\u0b85",new String[]{"uyir","kuril"}));
    map.put("\u0b86",new TamilChar(2,"\u0b86",new String[]{"uyir","nedil"}));
    map.put("\u0b87",new TamilChar(3,"\u0b87",new String[]{"uyir","kuril"}));
    map.put("\u0b88",new TamilChar(4,"\u0b88",new String[]{"uyir","nedil"}));
    map.put("\u0b89",new TamilChar(5,"\u0b89",new String[]{"uyir","kuril"}));
    map.put("\u0b8A",new TamilChar(6,"\u0b8A",new String[]{"uyir","nedil"}));
    map.put("\u0b8E",new TamilChar(7,"\u0b8E",new String[]{"uyir","kuril"}));
    map.put("\u0b8F",new TamilChar(8,"\u0b8F",new String[]{"uyir","nedil"}));
    map.put("\u0b90",new TamilChar(9,"\u0b90",new String[]{"uyir","kuril"}));
    map.put("\u0b92",new TamilChar(10,"\u0b92",new String[]{"uyir","kuril"}));
    map.put("\u0b93",new TamilChar(11,"\u0b93",new String[]{"uyir","nedil"}));
    map.put("\u0b94",new TamilChar(12,"\u0b94",new String[]{"uyir","kuril"}));
    //a-ow finished
    // Followed by "A" varisai
    map.put("\u0b95",new TamilChar(1,"\u0b95",new String[]{"uyirmei","vallinam"},"kha"));
    map.put("\u0b99",new TamilChar(2,"\u0b99",new String[]{"uyirmei","mellinam"},"nga"));
    map.put("\u0b9a",new TamilChar(3,"\u0b9a",new String[]{"uyirmei","vallinam"},"cha"));
    map.put("\u0b9e",new TamilChar(4,"\u0b9e",new String[]{"uyirmei","mellinam"},"nya"));
    map.put("\u0b9f",new TamilChar(5,"\u0b9f",new String[]{"uyirmei","vallinam"},"ta"));
    map.put("\u0ba3",new TamilChar(6,"\u0ba3",new String[]{"uyirmei","mellinam"},"na"));
    map.put("\u0ba4",new TamilChar(7,"\u0ba4",new String[]{"uyirmei","vallinam"},"tha"));
    map.put("\u0ba8",new TamilChar(8,"\u0ba8",new String[]{"uyirmei","mellinam"},"na"));
    map.put("\u0baa",new TamilChar(9,"\u0baa",new String[]{"uyirmei","vallinam"},"pa"));
    map.put("\u0bae",new TamilChar(10,"\u0bae",new String[]{"uyirmei","mellinam"},"ma"));
    map.put("\u0baf",new TamilChar(11,"\u0baf",new String[]{"uyirmei","idayinam"},"ya"));
    map.put("\u0bb0",new TamilChar(12,"\u0bb0",new String[]{"uyirmei","idayinam"},"ra"));
    map.put("\u0bb2",new TamilChar(13,"\u0bb2",new String[]{"uyirmei","idayinam"},"la"));
    map.put("\u0bb5",new TamilChar(14,"\u0bb5",new String[]{"uyirmei","idayinam"},"va"));
    map.put("\u0bb4",new TamilChar(15,"\u0bb4",new String[]{"uyirmei","idayinam"},"zha"));
    map.put("\u0bb3",new TamilChar(16,"\u0bb3",new String[]{"uyirmei","idayinam"},"la"));
    map.put("\u0bb1",new TamilChar(17,"\u0bb1",new String[]{"uyirmei","vallinam"},"ra"));
    map.put("\u0ba9",new TamilChar(18,"\u0ba9",new String[]{"uyirmei","mellinam"},"na"));

    //Others;

    map.put("\u0b82",new TamilChar(31,"\u0b82",new String[]{"anusvara"}));
    map.put("\u0b83",new TamilChar(32,"\u0b83",new String[]{"aytham"}));
    map.put("\u0b9c",new TamilChar(33,"\u0b9c",new String[]{"grantham"}));
    map.put("\u0bb7",new TamilChar(34,"\u0bb7",new String[]{"grantham"}));
    map.put("\u0bb8",new TamilChar(35,"\u0bb8",new String[]{"grantham"}));
    map.put("\u0bb9",new TamilChar(36,"\u0bb9",new String[]{"grantham"}));

    //Sirappu Kuriyeedukal
    map.put("",new TamilChar(1,"",new String[]{"sirappu_kuriyeedu","kuril"}));  //followed by A
    map.put("\u0bbe",new TamilChar(2,"\u0bbe",new String[]{"sirappu_kuriyeedu","nedil"}));  //followed by AA
    map.put("\u0bbf",new TamilChar(3,"\u0bbf",new String[]{"sirappu_kuriyeedu","kuril"}));  //followed by E
    map.put("\u0bc0",new TamilChar(4,"\u0bc0",new String[]{"sirappu_kuriyeedu","nedil"}));  //followed by EE
    map.put("\u0bc1",new TamilChar(5,"\u0bc1",new String[]{"sirappu_kuriyeedu","kuril"}));  //followed by U
    map.put("\u0bc2",new TamilChar(6,"\u0bc2",new String[]{"sirappu_kuriyeedu","nedil"}));  //followed by UU
    map.put("\u0bc6",new TamilChar(7,"\u0bc6",new String[]{"sirappu_kuriyeedu","kuril"}));  //followed by EA
    map.put("\u0bc7",new TamilChar(8,"\u0bc7",new String[]{"sirappu_kuriyeedu","nedil"}));  //followed by EAA
    map.put("\u0bc8",new TamilChar(9,"\u0bc8",new String[]{"sirappu_kuriyeedu","nedil"}));  //followed by I
    map.put("\u0bca",new TamilChar(10,"\u0bca",new String[]{"sirappu_kuriyeedu","kuril"}));  //followed by O
    map.put("\u0bcb",new TamilChar(11,"\u0bcb",new String[]{"sirappu_kuriyeedu","nedil"}));  //followed by Oo
    map.put("\u0bcc",new TamilChar(12,"\u0bcc",new String[]{"sirappu_kuriyeedu","nedil"}));  //followed by OW

    //map.put("0bd7",new TamilChar(46,"\u0bd7",new String[]{"sirappu_kuriyeedu",""})); // Pulli
    map.put("\u0bcd",new TamilChar(42,"\u0bcd",new String[]{"sirappu_kuriyeedu","otru"})); // i thing unused key
  }
}
