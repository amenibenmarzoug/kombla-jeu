package ConnectorCorba;

/**
* ConnectorCorba/PlayerHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from komblacorba.idl
* Monday, December 7, 2020 10:53:30 AM CET
*/

public final class PlayerHolder implements org.omg.CORBA.portable.Streamable
{
  public ConnectorCorba.Player value = null;

  public PlayerHolder ()
  {
  }

  public PlayerHolder (ConnectorCorba.Player initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ConnectorCorba.PlayerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ConnectorCorba.PlayerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ConnectorCorba.PlayerHelper.type ();
  }

}
