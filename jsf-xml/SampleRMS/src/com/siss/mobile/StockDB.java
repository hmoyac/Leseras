package com.siss.mobile;

/* License
 * 
 * Copyright 1994-2004 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *  
 *  * Redistribution of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer.
 * 
 *  * Redistribution in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *  
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *  
 * You acknowledge that this software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility. 
 */

import javax.microedition.rms.*;
import java.util.Enumeration;
import java.util.Vector;
import java.io.*;

public class StockDB {

   RecordStore recordStore = null;
       
   public StockDB() {}

   public StockDB(String fileName) {
      try {
        recordStore = RecordStore.openRecordStore(fileName, true);
      } catch(RecordStoreException rse) {
        rse.printStackTrace();
      }
   }

   public void close() throws RecordStoreNotOpenException,
                               RecordStoreException {
        if (recordStore.getNumRecords() == 0) {
            String fileName = recordStore.getName();
            recordStore.closeRecordStore();
            recordStore.deleteRecordStore(fileName);
        } else {
            recordStore.closeRecordStore();
        }
    }


   public synchronized void addNewStock(String record) {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	DataOutputStream outputStream = new DataOutputStream(baos);
	try {
	    outputStream.writeUTF(record);
	}
	catch (IOException ioe) {
	    System.out.println(ioe);
	    ioe.printStackTrace();
	}
	byte[] b = baos.toByteArray();
	try {
	    recordStore.addRecord(b, 0, b.length);
	}
	catch (RecordStoreException rse) {
	    System.out.println(rse);
	    rse.printStackTrace();
	}
    }

    public synchronized RecordEnumeration enumerate() throws RecordStoreNotOpenException {
       return recordStore.enumerateRecords(null, null, false);
    }


}
