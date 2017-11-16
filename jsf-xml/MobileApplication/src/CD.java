/*
 * CD.java
 *
 * Copyright � 1998-2008 Research In Motion Ltd.
 * 
 * Note: For the sake of simplicity, this sample application may not leverage
 * resource bundles and resource strings.  However, it is STRONGLY recommended
 * that application developers make use of the localization features available
 * within the BlackBerry development platform to ensure a seamless application
 * experience across a variety of languages and geographies.  For more information
 * on localizing your application, please refer to the BlackBerry Java Development
 * Environment Development Guide associated with this release.
 */;

import java.io.*;

/**
 * The CD class.
 * The toByteArray() and fromByteArray() methods are required because the RMS that serves as the backend to the CDdb class
 * stores each record as a byte array. The converted byte array is in tag-length format to allow the scalability of this class while
 * still allowing the CDdb to store and read previous versions of the CD class.
 */
public class CD
{
    private String _artist;
    private String _title;
    private String _fullName;
    
    private static final short ARTIST = 0;
    private static final short TITLE = 1;
    
   /**
    * Constructor for the URL object, given byte array.
    *
    * @param data Byte array for the CD.
    * @exception java.io.IOException IO error.
    */
    public CD(byte[] data) throws java.io.IOException
    {
        fromByteArray(data);
    }
    
    
   /**
    *  Constructor for the URL object, given protocol and path.
    *
    * @param artist The name of the CD artist.
    * @param title The title of the CD.
    */
    public CD(String artist, String title)
    {
        _artist = artist;
        _title = title;
        _fullName = _artist + ": " + _title;
    }
    
    
   /**
    * Provide the CD in the form of a String.
    *
    * @return The CD as a properly formed String.
    */
    public String toString()
    {
        return _fullName;
    }    
    
   /**
    *  Gets the artist attribute of the CD object.
    *
    * @return The arist name of the CD.
    */
    public String getArtist()
    {
        return _artist;
    }
    
    
   /**
    * Gets the title attribute of the CD object.
    *
    * @return The title of the CD.
    */
    public String getTitle()
    {
        return _title;
    }
    
    
   /**
    * Convert a CD to a byte array.
    *
    * @return Byte encoded version of the CD.
    * @exception java.io.IOException IO error.
    */
    public byte[] toByteArray() throws java.io.IOException
    {
        byte[] data;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
    
        dos.writeShort(ARTIST);
        dos.writeUTF(_artist);
        dos.writeShort(TITLE);
        dos.writeUTF(_title);
    
        data = baos.toByteArray();
    
        return data;
    }
    
    
   /**
    *  Convert a byte array to a CD.
    *
    * @param array CD encoded as a byte array.
    * @exception java.io.IOException IO error.
    */
    private void fromByteArray(byte[] array) throws java.io.IOException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(array);
        DataInputStream dis = new DataInputStream(bais);
    
        short tag;
        int length;
            
        try
        {  
            while(true)
            {
                tag = dis.readShort();
                
                if (tag == ARTIST)
                {
                    _artist = dis.readUTF();
                }
                else if (tag == TITLE)
                {
                    _title = dis.readUTF();
                }
                else
                {
                    // Unrecognized tag, skip value.
                    dis.readUTF();
                }
            }     
        }
        catch(EOFException e)
        {
            _fullName = _artist + ": " + _title;
        }
    }
}

