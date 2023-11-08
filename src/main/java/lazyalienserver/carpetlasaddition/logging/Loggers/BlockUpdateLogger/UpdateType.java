package lazyalienserver.carpetlasaddition.logging.Loggers.BlockUpdateLogger;

public enum UpdateType {
    NC(0),
    PP(1),
    NC_And_PP(2);
    public final int s;
    UpdateType(int s){
        this.s=s;
    }

    public String toString(){
        return this.name();
    }
}
