package tamil;
import java.util.ArrayList;
import java.util.HashMap;

public class AlageetuVaipadu implements TamilWords
{
	/* Using  by get Asaigal method K => Kuril ; N => Nedil ; O => Otru*/
	private static final String K = "K";
	private static final String N = "N";
	private static final String O = "O";
	
	/* Kuril nedil combinations*/
	private static String[] TWO = {"KK","KN","KO","NO"};
	private static String[] ONE = {"K","N"};

	private static HashMap<String,String[]> ORASAI = new HashMap<String,String[]>()
	{
		{
			put("NER",new String[]{NAL});
			put("NIRAI",new String[]{MALAR});
			put("NERBU",new String[]{KASU});
			put("NIRAIBU",new String[]{PIRAPPU});
		}
	};
	
	private static HashMap<String,String[]> EERASAI = new HashMap<String,String[]>()
	{
		{
			put("NER_NER",new String[]{THEMA,"MACHEER"});
			put("NIRAI_NER",new String[]{PULIMA,"MACHEER"});
			put("NIRAI_NIRAI",new String[]{KARUVILAM,"VILACHEER"});
			put("NER_NIRAI",new String[]{KOOVILAM,"VILACHEER"});			
		}
	};
	
	private static HashMap<String,String[]> MOOVASAI = new HashMap<String,String[]>()
	{
		{
			put("NER_NER_NER",new String[]{THEMANKAI,"KAICHEER or VENSEER"});
			put("NIRAI_NER_NER",new String[]{PULIMANKAI,"KAICHEER or VENSEER"});
			put("NIRAI_NIRAI_NER",new String[]{KARUVILANKAI,"KAICHEER or VENSEER"});
			put("NER_NIRAI_NER",new String[]{KOOVILANKAI,"KAICHEER or VENSEER"});			
			put("NER_NER_NIRAI",new String[]{THEMANKANI,"KANICHEER or VANCHIYURICHEER"});	
			put("NIRAI_NER_NIRAI",new String[]{PULIMANKANI,"KANICHEER or VANCHIYURICHEER"});	
			put("NIRAI_NIRAI_NIRAI",new String[]{KARUVILANKANI,"KANICHEER or VANCHIYURICHEER"});
			put("NER_NIRAI_NIRAI",new String[]{KOOVILANKANI,"KANICHEER or VANCHIYURICHEER"});
		}
	};
	
	private static HashMap<String,String[]> NALASAI = new HashMap<String,String[]>()
	{
		{
			put("NER_NER_NER_NER",new String[]{THEMANTHANPOO,"POTHUCHEER"});
			put("NIRAI_NER_NER_NER",new String[]{PULIMANTHANPOO,"POTHUCHEER"});
			put("NIRAI_NIRAI_NER_NER",new String[]{KARUVILANTHANPOO,"POTHUCHEER"});
			put("NER_NIRAI_NER_NER",new String[]{KOOVILANTHANPOO,"POTHUCHEER"});			
			put("NER_NER_NIRAI_NER",new String[]{THEMANARUMPOO,"POTHUCHEER"});
			put("NIRAI_NER_NIRAI_NER",new String[]{PULIMANARUMPOO,"POTHUCHEER"});	
			put("NIRAI_NIRAI_NIRAI_NER",new String[]{KARUVILANARUMPOO,"POTHUCHEER"});
			put("NER_NIRAI_NIRAI_NER",new String[]{KOOVILANARUMPOO,"POTHUCHEER"});
		
			put("NER_NER_NER_NIRAI",new String[]{THEMANTHANNIZHAL,"POTHUCHEER"});
			put("NIRAI_NER_NER_NIRAI",new String[]{PULIMANTHANTHANNIZHAL,"POTHUCHEER"});
			put("NIRAI_NIRAI_NER_NIRAI",new String[]{KARUVILANTHANNIZHAL,"POTHUCHEER"});
			put("NER_NIRAI_NER_NIRAI",new String[]{KOOVILANTHANTHANNIZHAL,"POTHUCHEER"});			
			put("NER_NER_NIRAI_NIRAI",new String[]{THEMANARUNIZHAL,"POTHUCHEER"});
			put("NIRAI_NER_NIRAI_NIRAI",new String[]{PULIMANARUNIZHAL,"POTHUCHEER"});	
			put("NIRAI_NIRAI_NIRAI_NIRAI",new String[]{KARUVILANARUNIZHAL,"POTHUCHEER"});
			put("NER_NIRAI_NIRAI_NIRAI",new String[]{KOOVILANARUNIZHAL,"POTHUCHEER"});
		}
	};
	
	private static  boolean isC(String[] arr, String val)
	{
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i].equals(val))
			{
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<String> getAsaikal(String[] varr,String[] sarr, StringBuffer sb)
	{
		sb.append("[");
		ArrayList<String> al = new ArrayList<String>();
		for(int i=0;i<varr.length;i++)
		{
			String f="",s="",t="";
			f = (i < varr.length)? varr[i] : null;
			s = (i+1 < varr.length)? varr[i+1] : null;
			t = (i+2 < varr.length)? varr[i+2] : null;

			if(((i+2) < varr.length) && t.equals(O))
			{
				if((f.equals(K) && s.equals(K) && t.equals(O)) || (f.equals(K) && s.equals(N) && t.equals(O)))
				{
					al.add("NIRAI");
					sb.append(",\""+sarr[i]+" "+sarr[i+1]+" "+sarr[i+2] +"\", \""+NIRAI+"\" ");
				}
				i = i+2;
			}
			else if(((i+1) < varr.length) && (isC(TWO,f+s)))
			{
				if(((f==K) && (s == K)) || ((f == K) && (s == N)))
				{
					al.add("NIRAI");
					sb.append(",\""+sarr[i]+" "+sarr[i+1]+"\", \""+NIRAI+"\"");
				}
				else if(((f == K) && (s == O)) || ((f == N) && (s == O)))
				{
					al.add("NER");
					sb.append(",\""+sarr[i]+" "+sarr[i+1]+"\", \""+NER+"\"");
				}
				i = i+1;
			}
			else if((i < varr.length) && (isC(ONE,f)))
			{
				if((f == K) || (f == N))
				{
					al.add("NER");
					sb.append(",\""+sarr[i]+"\", \""+NER+"\"");
				}
			}	
		}
    sb.append("]");
		return al;
	}	
	
	public static String[] allocatePart(TamilCharacters tc, String[] sarr, StringBuffer sb)
	{
    sb.append("{");
		String[] vaipadu = new String[sarr.length];
		for(int i=0;i<sarr.length;i++)
		{
			String s = sarr[i];
			if(tc.isKuril(s))
			{
				vaipadu[i] = K;
				sb.append("\""+s+"\"  : \""+KURIL+"\" , ");
			}
			else if(tc.isNedil(s))
			{
				vaipadu[i] = N;
				sb.append("\""+s+"\"  : \""+NEDIL+"\" , ");
			}
			else if(tc.isOtru(s))
			{
				vaipadu[i] = O;
				sb.append("\""+s+"\"  : \""+OTRU+"\",  ");
			}
			else
			{
				vaipadu[i] = "";
				sb.append("\""+s+"\" : \"invalid char\",");
			}
		}
    sb.append("}");
		return vaipadu;
	}
	
	public static String getSeer(ArrayList<String> al)
	{
		if(al.isEmpty())
		{
			return "Invalid characters";
		}
		int size = al.size();
		String key = al.get(0);
		if(size > 4){return "Invalid word";	}
		for(int  i=1;i<size;i++)
		{
			key = key+"_"+al.get(i);
		}
		switch(size)
		{
			case 1:
				return (String)ORASAI.get(key)[0];
			case 2:
				return (String)EERASAI.get(key)[0];
			case 3:
				return (String)MOOVASAI.get(key)[0];
			case 4:
				return (String)NALASAI.get(key)[0];
			default:
				return "Invalid value";
		}
	}

  public static void getVaipadu(String input, StringBuffer sb) throws Exception
  {
    TamilCharacters tc = new TamilCharacters();
    sb.append("[ \"Success\" ,");
    String[] sarr = tc.detachProperChars(input,sb);   //Parse proper Characters
    sb.append(",");
    String[] arr = allocatePart(tc,sarr,sb);  // detach Character ... ie. kuril, nedil
    sb.append(",");
    ArrayList asaikal = getAsaikal(arr,sarr,sb);  //detaching asai
    String seer = getSeer(asaikal);   //get seer
    sb.append(",");
    sb.append("\""+seer+"\"");
    sb.append("]");
  }
}
