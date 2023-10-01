package lazyalienserver.carpetlasaddition.records.RecordsList;

import lazyalienserver.carpetlasaddition.records.Record;

import java.nio.file.Path;
public class CommandRecord extends Record {

    public CommandRecord(){
        super("CommandRecord");
    }
    @Override
    public Path getRecordsDir(){
        return super.getRecordsDir().resolve(this.identifier);
    }

}
