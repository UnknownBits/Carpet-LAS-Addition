package lazyalienserver.carpetlasaddition.records;

import lazyalienserver.carpetlasaddition.utils.DateTimeUtils;
import lazyalienserver.carpetlasaddition.utils.FileUtils;

import java.nio.file.Path;
import java.util.ArrayList;

public class Record {
    protected final ArrayList<String> Record=new ArrayList<>();
    public final String identifier;
    protected Record(String identifier){
        this.identifier=identifier;
    }
    public Path getRecordsDir(){
        return FileUtils.getMainPath().resolve("Record");
    }
    public void addRecord(String record){
        Record.add(record);
        if (Record.size()>=100)
        {
            FileUtils.writeFile(this.getRecordsDir().resolve(DateTimeUtils.getNowDate()+".log"),this.Record);
            this.Record.clear();
        }

    }
    public static void registerRecords(){
        RecordList.load();
    }
    public ArrayList<String> getRecord(){
        return this.Record;
    }
}
