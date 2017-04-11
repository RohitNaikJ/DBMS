package simpledb.remote;

import java.rmi.RemoteException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * An adapter class that wraps RemoteResultSet.
 * Its methods do nothing except transform RemoteExceptions
 * into SQLExceptions.
 * @author Edward Sciore
 */
public class SimpleResultSet extends ResultSetAdapter {
   private RemoteResultSet rrs;
   
   public SimpleResultSet(RemoteResultSet s) {
      rrs = s;
   }
   
   public boolean next() throws SQLException {
      try {
         return rrs.next();
      }
      catch (Exception e) {
         throw new SQLException(e);
      }
   }
   
   public int getInt(String fldname) throws SQLException {
      try {
         return rrs.getInt(fldname);
      }
      catch (Exception e) {
         throw new SQLException(e);
      }
   }
   
   public String getString(String fldname) throws SQLException {
      try {
         return rrs.getString(fldname);
      }
      catch (Exception e) {
         throw new SQLException(e);
      }
   }

    public java.sql.Date getDate(String fldname) throws SQLException {
       java.util.Date utilDate = null;
       try {
           utilDate = rrs.getDate(fldname);
       } catch (RemoteException e) {
           System.out.println("EXCEPTION");
           e.printStackTrace();
       }
       Date date = new Date(utilDate.getTime());
       if(date==null)
          System.out.println("SimpleResultSet: NULL");
       return date;
   }

   public ResultSetMetaData getMetaData() throws SQLException {
      try {
         RemoteMetaData rmd = rrs.getMetaData();
         return new SimpleMetaData(rmd);
      }
      catch (Exception e) {
         throw new SQLException(e);
      }
   }
   
   public void close() throws SQLException {
      try {
         rrs.close();
      }
      catch (Exception e) {
         throw new SQLException(e);
      }
   }
}

