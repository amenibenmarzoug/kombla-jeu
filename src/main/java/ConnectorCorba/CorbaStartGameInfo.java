package ConnectorCorba;


/**
* ConnectorCorba/CorbaStartGameInfo.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from komblacorba.idl
* Monday, December 7, 2020 10:53:30 AM CET
*/

public final class CorbaStartGameInfo implements org.omg.CORBA.portable.IDLEntity
{
  public int playerId = (int)0;
  public int maze[][] = null;

  public CorbaStartGameInfo ()
  {
  } // ctor

  public CorbaStartGameInfo (int _playerId, int[][] _maze)
  {
    playerId = _playerId;
    maze = _maze;
  } // ctor

} // class CorbaStartGameInfo
