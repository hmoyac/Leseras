/*
 * CDdb.java
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
 */



import javax.microedition.rms.*;

/**
 *  Manage an RMS of Compact Disc titles.
 *
 */
public class CDdb
{
    RecordStore _rs;
    
   /**
    *  Constructor for the CDdb, creator.
    *
    * @param name  Name of the RMS.
    * @exception RecordStoreException General record store exception.
    * @exception java.io.IOException IO error.
    */
    public CDdb(String name) throws RecordStoreException, java.io.IOException
    {
        _rs = RecordStore.openRecordStore(name, true, RecordStore.AUTHMODE_ANY, false);
    }
    
    
   /**
    *  Constructor for the CDdb, consumer.
    *
    * @param recordStoreName Name of the RMS.
    * @param vendorName MIDlet suite vendor.
    * @param suiteName MIDlet suite name.
    * @exception RecordStoreException General record store exception.
    * @exception java.io.IOException IO error.
    */
    public CDdb(String recordStoreName, String vendorName, String suiteName) throws RecordStoreException, java.io.IOException
    {
        _rs = RecordStore.openRecordStore( recordStoreName, vendorName, suiteName );
    }
    
    
   /**
    *  Add a CD to the RecordStore.
    *
    * @param Artist The name of the artist of the CD.
    * @param title The title of the CD.
    * @return RecordID of new CD.
    * @exception java.io.IOException IO error.
    * @exception RecordStoreNotOpenException Record store not open.
    * @exception RecordStoreException General record store exception.
    */
    public synchronized int add(String artist, String title) throws java.io.IOException, RecordStoreNotOpenException, RecordStoreException
    {
        CD cd = new CD(artist, title);
        byte[] data = cd.toByteArray();
                
        return _rs.addRecord(data, 0, data.length);
    }
            
   /**
    *  Edit a CD in the RecordStore
    *
    * @param artist The name of the artist of the CD.
    * @param title The title of the CD.
    * @return recordID of new CD.
    * @exception java.io.IOException IO error.
    * @exception RecordStoreNotOpenException Record store not open.
    * @exception RecordStoreException General record store exception.
    */
    public synchronized void edit(int index, String artist, String title) throws java.io.IOException, RecordStoreNotOpenException, RecordStoreException
    {
        CD cd = new CD(artist, title);
        byte[] data = cd.toByteArray();        
        _rs.setRecord(index, data, 0, data.length);
    }
    
    
   /**
    * Return the CD of a given recordID with the RMS.
    *
    * @param recordID RecordID of CD to retrieve.
    * @return The CD corresponding to the recordID.
    * @exception RecordStoreNotOpenException Record store not open
    * @exception InvalidRecordIDException RecordID is invalid
    * @exception RecordStoreException General record store exception
    * @exception java.io.IOException IO error
    */
    public CD getCD(int recordID) throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException, java.io.IOException 
    {
        byte[] data = _rs.getRecord(recordID);
        
        return new CD(data);
    }
    
    
   /**
    * Delete a CD from the RecordStore
    *
    * @param recordId RecordID within the RMS.
    * @exception RecordStoreNotOpenException Record store not open.
    * @exception InvalidRecordIDException Invalid record ID.
    * @exception RecordStoreException General record store exception.
    */
    public synchronized void delete(int recordId) throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException 
    {
        _rs.deleteRecord(recordId);
    }
    
   /**
    *  Generate an enumeration for the RMS
    *
    * @return An enumeration object for the RMS.
    * @exception RecordStoreNotOpenException  Record store not open.
    */
    public RecordEnumeration enumerate() throws RecordStoreNotOpenException
    {
        return _rs.enumerateRecords(null, null, true);
    }
}
