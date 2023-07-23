package com.deadlock.randomsample;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;

class Database {

  public Record[] records = new Record[Constants.NUM_RECORDS];

  public Database (){
    init();
  }

  private void init() {
    for (int i = 0; i < Constants.NUM_RECORDS; i++) {
      int age = (int) Math.random() % 20;
      int nameIndex = (int) (Math.random() % Constants.names.length);
      String name = Constants.names[nameIndex];
      DataRecord dataRecord = new DataRecord(i, name, age);
      records[i] = new Record(dataRecord, new Mutex());
    }
  }

}

class DataRecord {
  int id;
  String name;
  int age;

  public DataRecord(int id, String name, int age){
    this.id = id;
    this.name = name;
    this.age = age;
  }
}

class Record {

  DataRecord data;
  Mutex mutex;

  Record(DataRecord data , Mutex mutex){
    this.data = data;
    this.mutex = mutex;
  }
}

