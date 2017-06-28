package pl.pracawregionie;

public enum Gender {
    FEMALE,MALE;

    @Override
    public String toString(){
        switch (this){
            case MALE: return "Pan";
            case FEMALE: return "Pani";
            default: return "b.d.";
        }
    }
    
}
