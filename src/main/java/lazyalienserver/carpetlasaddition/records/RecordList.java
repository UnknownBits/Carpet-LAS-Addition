package lazyalienserver.carpetlasaddition.records;

import lazyalienserver.carpetlasaddition.records.RecordsList.CommandBlockRecord;
import lazyalienserver.carpetlasaddition.records.RecordsList.CommandRecord;
import lazyalienserver.carpetlasaddition.utils.DateTimeUtils;
import lazyalienserver.carpetlasaddition.utils.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RecordList {
    public static final Map<String,Record> recordList=new ConcurrentHashMap<>();
    public static final Record commandRecord= RecordList.RegisterRecord(new CommandRecord());
    public static final Record commandBlockRecord=RecordList.RegisterRecord(new CommandBlockRecord());
    public static Record RegisterRecord(Record record){
        recordList.put(record.identifier,record);
        return record;
    }
    public static void saveAllRecord(){
        for (Map.Entry<String,Record> entry:recordList.entrySet()){
            FileUtils.writeFile(entry.getValue().getRecordsDir().resolve(DateTimeUtils.getNowDate()+".log"),entry.getValue().getRecord());
        }
    }
    public static void load(){}
}
