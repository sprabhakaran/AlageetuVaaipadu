package tamil;
public class TamilChar
{
  private int index;
  private String unicode;
  private String[] props;

  boolean kuril,nedil,mei,uyir,uyirmei,aytham,vallinam,mellinam,idayinam,sirappu_kuriyeedu,otru = false;

  public TamilChar(int index,String unicode,String[] props)
  {
    this(index,unicode,props,"");
  }

  public TamilChar(int index,String unicode,String[] props,String name)
  {
    this.index = index;
    this.unicode = unicode;
    this.props = props;
    updateProps();
  }

  public int getIndex()
  {
    return index;
  }

  public String getUnicode()
  {
    return unicode;
  }

  public String[] getProps()
  {
    return props;
  }

  public void updateProps()
  {
    if(this.getProps() == null)
    {
      return;
    }
    String[] props = this.getProps();
    for(String prop : props)
    {
      if(prop.equals("kuril")) { kuril = true;continue; }
      if(prop.equals("nedil")) { nedil = true;continue; }
      if(prop.equals("otru")) { otru = true;continue; }
      if(prop.equals("mei")) { mei = true;continue; }
      if(prop.equals("uyir")) { uyir = true;continue; }
      if(prop.equals("uyirmei")) { uyirmei = true;continue; }
      if(prop.equals("aytham")) { aytham = true;continue; }
      if(prop.equals("vallinam")) { vallinam = true;continue; }
      if(prop.equals("mellinam")) { mellinam = true;continue; }
      if(prop.equals("idayinam")) { idayinam = true;continue; }
      if(prop.equals("sirappu_kuriyeedu")) { sirappu_kuriyeedu = true;continue; }
    }
  }
  
  public String toString()
  {
    return unicode+"   [ kuril : "+kuril+" , nedil : " + nedil +" , mei : " + mei + " , uyir : " + uyir 
      + " , uyirmei : " + uyirmei + " , aytham : " + aytham + " , vallinam : " + vallinam 
      + " , mellinam : " + mellinam + " , idayinam : " + idayinam + " ]";
  }
}
