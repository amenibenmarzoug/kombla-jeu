package ConnectorCorba;


/**
* ConnectorCorba/Player.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from komblacorba.idl
* Monday, December 7, 2020 10:53:30 AM CET
*/

public final class Player implements org.omg.CORBA.portable.IDLEntity
{
  public String name = null;
  public int id = (int)0;

  public Player ()
  {
  } // ctor

  public Player (String _name, int _id)
  {
    name = _name;
    id = _id;
  } // ctor

} // class Player