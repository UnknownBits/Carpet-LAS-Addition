package lazyalienserver.carpetlasaddition.records.RecordsList;

import lazyalienserver.carpetlasaddition.records.Record;

import java.nio.file.Path;

public class CommandBlockRecord extends Record {
    public CommandBlockRecord(){
        super("CommandBlockRecord");
    }
    @Override
    public Path getRecordsDir(){
        return super.getRecordsDir().resolve(this.identifier);
    }
}
