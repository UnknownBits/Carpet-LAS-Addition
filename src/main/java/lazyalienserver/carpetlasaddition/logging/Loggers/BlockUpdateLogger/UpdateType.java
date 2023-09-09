package lazyalienserver.carpetlasaddition.logging.Loggers.BlockUpdateLogger;

public enum UpdateType {
    NC(0),
    PP(1),
    NC_And_PP(2);
    public final int x;
    UpdateType(int x){
        this.x=x;
    }

    public String toString(){
        return this.name();
    }
}
