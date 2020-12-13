package ConnectorCorba;


/**
* ConnectorCorba/CorbaStartGameInfoHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from komblacorba.idl
* Monday, December 7, 2020 10:53:30 AM CET
*/

abstract public class CorbaStartGameInfoHelper
{
  private static String  _id = "IDL:ConnectorCorba/CorbaStartGameInfo:1.0";

  public static void insert (org.omg.CORBA.Any a, ConnectorCorba.CorbaStartGameInfo that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static ConnectorCorba.CorbaStartGameInfo extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [2];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[0] = new org.omg.CORBA.StructMember (
            "playerId",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_array_tc (10, _tcOf_members0 );
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_array_tc (10, _tcOf_members0 );
          _members0[1] = new org.omg.CORBA.StructMember (
            "maze",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (ConnectorCorba.CorbaStartGameInfoHelper.id (), "CorbaStartGameInfo", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static ConnectorCorba.CorbaStartGameInfo read (org.omg.CORBA.portable.InputStream istream)
  {
    ConnectorCorba.CorbaStartGameInfo value = new ConnectorCorba.CorbaStartGameInfo ();
    value.playerId = istream.read_long ();
    value.maze = new int[10][];
    for (int _o0 = 0;_o0 < (10); ++_o0)
    {
      value.maze[_o0] = new int[10];
      for (int _o1 = 0;_o1 < (10); ++_o1)
      {
        value.maze[_o0][_o1] = istream.read_long ();
      }
    }
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, ConnectorCorba.CorbaStartGameInfo value)
  {
    ostream.write_long (value.playerId);
    if (value.maze.length != (10))
      throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    for (int _i0 = 0;_i0 < (10); ++_i0)
    {
      if (value.maze[_i0].length != (10))
        throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
      for (int _i1 = 0;_i1 < (10); ++_i1)
      {
        ostream.write_long (value.maze[_i0][_i1]);
      }
    }
  }

}